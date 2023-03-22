package guru.springframework.spring5webapp.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import guru.springframework.spring5webapp.batches.EmailBatchConfig;
import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;
import guru.springframework.spring5webapp.service.EmailService;
import guru.springframework.spring5webapp.service.SendEmails;

@Controller
public class EmailController {

	private EmailRepository emailRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SendEmails sendEmails;
	
	@Autowired
	private EmailBatchConfig batchConfig;
	
	@Autowired
	private JobLauncher joblauncher; 
	

	public EmailController(EmailRepository emailRepository) {
		super();
		this.emailRepository = emailRepository;
	}
	@PostMapping("/importEmailData")
    public String importDataFromCsv(@RequestParam("file") MultipartFile file) {
//       emailService.saveEmailData(file);
		try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
			CsvToBean<Email> csvToBean =new CsvToBeanBuilder<Email>(reader)
					.withType(Email.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
			List<Email> emailDataList=csvToBean.parse();
			
			emailRepository.saveAll(emailDataList);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	return "emailsdata/list";
    }
	
	@RequestMapping("/emails")
	public String getBooks(Model model) {
		System.out.println("Started mai Successfully...");
		model.addAttribute("emails",emailRepository.findAll());
		
		return "emailsdata/list";
	}
	
	@PostMapping("/sendemails")
	@Scheduled(cron="0 */2 * * * *")
	public String sendEmails() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		System.out.println("Started mai Successfully...");
		joblauncher.run(batchConfig.emailJob(),new JobParametersBuilder().addDate("date", new Date()).toJobParameters());
		return "emailsdata/list";
		
	}
}
