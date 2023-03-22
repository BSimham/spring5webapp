package guru.springframework.spring5webapp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;

@Component
public class EmailMessageProcessor implements ItemProcessor<List<Email>, List<MimeMessage>> {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    private EmailRepository emailRepository;
	
	private static int count=0;

	@Override
	public List<MimeMessage> process(List<Email> emailMessages) throws Exception {
		List<MimeMessage> mimeMessages = new ArrayList<>();
		for (Email emailMessage : emailMessages) {
			
			if(emailMessage.getFlag()==null) {continue;}
			if(emailMessage.getFlag()==true) {continue;}
				
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setTo(emailMessage.getCust_email());
				if(emailMessage.getSubject()==null) {
					continue;
				}
				helper.setSubject(emailMessage.getSubject());
				helper.setText(emailMessage.getBody());
				mimeMessages.add(mimeMessage);
				count+=1;
				emailMessage.setFlag(true);
				emailRepository.save(emailMessage);
				System.out.println("In email processor");
				System.out.println("email is "+(emailRepository.findById(emailMessage.getId())).get().toString()+count);
			
			
		}
		
		
		
		return mimeMessages;
	}
	
	
	
	
}
