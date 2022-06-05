package snps.hack.service;

import snps.hack.model.User;

import java.util.Optional;

public interface UserService {
	Optional<User> findByUsername(String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByResetToken(String resetToken);
    void save(User user);
    Long authenticatedUser();
}
