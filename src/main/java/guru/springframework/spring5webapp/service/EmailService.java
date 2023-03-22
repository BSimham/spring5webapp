package guru.springframework.spring5webapp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	public void saveEmailData(MultipartFile file) {
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
	}

}
