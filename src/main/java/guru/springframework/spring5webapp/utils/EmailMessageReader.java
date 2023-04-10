package guru.springframework.spring5webapp.utils;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Email;
import guru.springframework.spring5webapp.repositories.EmailRepository;


@Component
public class EmailMessageReader implements ItemReader<List<Email>> {
    
    @Autowired
    private EmailRepository emailRepository;

    private int currentPage = 0;
    private int pageSize = 100;

    @Override
    public List<Email> read() throws Exception {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Email> page = emailRepository.findAllFalse(pageable);
        System.out.println("in Reader"+currentPage+"  "+pageSize+page.getContent());
        return page.hasContent() ? page.getContent() : null;
    }
    
}


