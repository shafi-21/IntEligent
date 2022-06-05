package snps.hack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import snps.hack.model.User;
import snps.hack.repository.UserRepository;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional findByUsername(String email) {
		return userRepository.findByUsername(email);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public Optional findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}

	@Override
	public Long authenticatedUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long userId = userRepository.findByUsername(auth.getName()).get().getId();
		
		return userId;
	}

}


