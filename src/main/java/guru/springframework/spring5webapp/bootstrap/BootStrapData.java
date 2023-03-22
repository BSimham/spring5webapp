package guru.springframework.spring5webapp.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.EmailRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	private final EmailRepository emailRepository;
	
	@Value("${spring.mail.username}") 
	private String sender;
	@Autowired
	private JavaMailSender emailSender;
	

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository,EmailRepository emailRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
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
			
		// TODO Auto-generated method stub
		 	Publisher publisher = new Publisher();
	        publisher.setName("SFG Publishing");
	        publisher.setCity("St Petersburg");
	        publisher.setState("FL");
	        
	        Publisher publisher2 = new Publisher();
	        publisher2.setName("SFG Publishing");
	        publisher2.setCity("St Petersburg");
	        publisher2.setState("FL");

	        publisherRepository.save(publisher);
	        publisherRepository.save(publisher2);

	        System.out.println("Publisher Count: " + publisherRepository.count());

		Author legend=new Author("Legend","Simha");
		Book divine=new Book("legend's discovery","123123");
		legend.getBooks().add(divine);
		divine.getAuthor().add(legend);
		
		divine.setPublisher(publisher);
		publisher.getBooks().add(divine);
		
		authorRepository.save(legend);
		bookRepository.save(divine);
		publisherRepository.save(publisher);
		
		
		
		Author legend2=new Author("Legend2","Simha2");
		Book divine2=new Book("legend's discovery2","1231232");
		legend2.getBooks().add(divine2);
		divine2.getAuthor().add(legend2);
		
		divine2.setPublisher(publisher2);
		publisher2.getBooks().add(divine2);
		
		authorRepository.save(legend2);
		bookRepository.save(divine2);
		publisherRepository.save(publisher2);
		System.out.println("Started in BootStrap2");
		System.out.println("Started in BootStrap");
		System.out.println("No of Books : "+bookRepository.count());
		System.out.println("Publisher number of books : "+publisher.getBooks().size());
//		
//		System.out.println("Started mai Successfully..."+sender);
//		List<Email> emails=emailRepository.findAll();
//		for (Email email:emails) {
//			if(email.getFlag()) {
//				continue;
//			}
//			 try {
//				 System.out.println("Started mai Successfully..."+sender+email.getCust_email()+email.getSubject());
//			
//			SimpleMailMessage message=new SimpleMailMessage();
//			message.setTo(email.getCust_email());
//			message.setFrom(sender);
//			message.setSubject(email.getSubject());
//			message.setText(email.getIntropurpose()+"\n"+email.getBody()+"\n"+email.getClosing()+"\n");
//			System.out.println(message);
//			emailSender.send(message);
//			System.out.println(message);
////			return "Mail Sent Successfully...";
//		}
//			 catch (Exception e) {
////		            return "Error while Sending Mail";
//				 System.out.println("Errr Started mai Successfully..."+sender+email.getCust_email()+email.getSubject());
//				 e.printStackTrace();
//		        }
//		}
		
		
	}	

}
