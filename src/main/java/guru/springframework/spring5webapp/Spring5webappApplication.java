package guru.springframework.spring5webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//implements CommandLineRunner
@EnableScheduling
public class Spring5webappApplication {
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Spring5webappApplication.class, args);
	}
	
}
