package snps.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snps.hack.message.request.RegistrationForm;
import snps.hack.message.response.ResponseMessage;
import snps.hack.model.ERole;
import snps.hack.model.Role;
import snps.hack.model.User;
import snps.hack.repository.RoleRepository;
import snps.hack.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminRestAPI {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationForm registrationRequest) {

		if (userRepository.existsByEmail(registrationRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(registrationRequest.getName(), registrationRequest.getEmail(),
				encoder.encode(registrationRequest.getPassword()), registrationRequest.getBu());

		Set<Role> roles = new HashSet<>();
		Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(employeeRole);
		
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}
