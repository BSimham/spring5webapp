package guru.springframework.spring5webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import guru.springframework.spring5webapp.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
