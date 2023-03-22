package guru.springframework.spring5webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;

@Service
public class SendEmails {
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Value("${spring.mail.username}") 
	private String sender;
	
	public String sendEmail() {
		System.out.println("Started mai Successfully..."+sender);
		List<Email> emails=emailRepository.findAll();
		for (Email email:emails) {
			if(email.getFlag()) {
				continue;
			}
			 try {
				 System.out.println("Started mai Successfully..."+sender+email.getCust_email()+email.getSubject());
			
			SimpleMailMessage message=new SimpleMailMessage();
			message.setTo(email.getCust_email());
			message.setFrom(sender);
			message.setSubject(email.getSubject());
			message.setText(email.getIntropurpose()+"\n"+email.getBody()+"\n"+email.getClosing()+"\n");
			System.out.println(message);
			emailSender.send(message);
			System.out.println(message);
			return "Mail Sent Successfully...";
		}
			 catch (Exception e) {
		            return "Error while Sending Mail";
		        }
		}
		return "N Mail";
	}

}
