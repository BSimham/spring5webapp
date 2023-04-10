package guru.springframework.spring5webapp.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import guru.springframework.spring5webapp.domain.Email;


import guru.springframework.spring5webapp.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

  @Query(
			value = "SELECT e FROM Email e WHERE e.flag= false"	,
			countQuery = "SELECT COUNT(e) FROM Email e WHERE e.flag= false"

		)
	  Page<Email> findAllFalse(Pageable pageable);
}
