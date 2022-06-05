package snps.hack.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	 void sendEmail(SimpleMailMessage email);
}
