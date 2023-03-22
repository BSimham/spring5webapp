package guru.springframework.spring5webapp.utils;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailMessageSender implements ItemWriter<List<MimeMessage>> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void write(List<? extends List<MimeMessage>> mimeMessageChunks) throws Exception {
        for (List<MimeMessage> mimeMessages : mimeMessageChunks) {
            for (MimeMessage mimeMessage : mimeMessages) {
            	System.out.println("In Email Sender");
                mailSender.send(mimeMessage);
            }
        }
    }
}
