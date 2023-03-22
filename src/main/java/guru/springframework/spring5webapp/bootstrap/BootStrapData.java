package guru.springframework.spring5webapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	
	private final EmailRepository emailRepository;
	
	@Value("${spring.mail.username}") 
	private String sender;
	@Autowired
	private JavaMailSender emailSender;
	

	public BootStrapData(EmailRepository emailRepository) {
		super();
	
		this.emailRepository = emailRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
			Email email1=new Email();
			email1.setCust_name("Bharath");
			email1.setCust_email("bsimhaphotos1@gmail.com");
			email1.setSubject("Loan Offer");
			email1.setIntropurpose("Greetings Bharath Simha Reddy");
			email1.setBody("You have an amazing offer to avail Loan amount of 15 lakhs only @6.5% interest");
			email1.setClosing("Meet you soon");
			email1.setFlag(false);
			
			emailRepository.save(email1);
			System.out.println("Email Count: " + emailRepository.count());
			
		
		
		
	}	

}
