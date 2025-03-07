package guru.springframework.spring5webapp.batches;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import guru.springframework.spring5webapp.domain.Email;


@Configuration
@EnableBatchProcessing
public class EmailBatchConfig  {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemReader<List<Email>> emailMessageReader;

    @Autowired
    private ItemProcessor<List<Email>,List<MimeMessage>> emailMessageProcessor;

    @Autowired
    private ItemWriter<List<MimeMessage>> emailMessageSender;
    

    @Bean
    public Job emailJob() {
        return jobBuilderFactory.get("emailJob")
                .incrementer(new RunIdIncrementer())
                .start(emailStep())
                .build();
    }


    @Bean
    public Step emailStep() {
        return stepBuilderFactory.get("emailStep")
        		.<List<Email>,List<MimeMessage>>chunk(10)
                .reader(emailMessageReader)
                .processor(emailMessageProcessor)
                .writer(emailMessageSender)
                .build();
    }
    
}