package snps.hack.controllers;

import com.google.auth.oauth2.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import snps.hack.message.request.LoginForm;
import snps.hack.message.request.SignupRequest;
import snps.hack.message.response.JwtResponse;
import snps.hack.message.response.ResponseMessage;
import snps.hack.model.ERole;
import snps.hack.model.Employee;
import snps.hack.model.Role;
import snps.hack.model.User;
import snps.hack.repository.EmployeeRepo;
import snps.hack.repository.RoleRepository;
import snps.hack.repository.UserRepository;
import snps.hack.service.AuthService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPI {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRepo employeeRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated()){
            return ResponseEntity.badRequest().body(new ResponseMessage("Invalid Credentials"));
        }

        return authService.userAuth(authentication);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if(userRepo.existsByUsername(signupRequest.getName())) {
            return ResponseEntity.badRequest().body(
                    new ResponseMessage("Error: Username is already taken!")
            );
        }
        if(userRepo.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(
                    new ResponseMessage("Error: Email is already taken!")
            );
        }

        User user = new User(signupRequest.getName(), signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),signupRequest.getBg());

        userRepo.save(user);

        Long userId = userRepo.findByEmail(signupRequest.getEmail()).get().getId();

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            Employee playerSave = new Employee(userId);
            employeeRepo.save(playerSave);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role modRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);

                        Employee playerSave = new Employee(userId);
                        employeeRepo.save(playerSave);
                }
            });
        }

        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok(new ResponseMessage("User registered successfully!"));
    }
}
