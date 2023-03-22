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
public class Spring5webappApplication implements CommandLineRunner{
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Spring5webappApplication.class, args);
	}
	 @Override
	    public void run(String... args) throws Exception {
	        String sql = "INSERT INTO students (id,name, email) VALUES (1,"
	                + "'Nam Ha Minh', 'nam@codejava.net')";
	         
	        int rows = jdbcTemplate.update(sql);
	        if (rows > 0) {
	            System.out.println("A new row has been inserted.");
	        }
	    }
}
