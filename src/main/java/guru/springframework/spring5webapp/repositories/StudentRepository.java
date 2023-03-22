package guru.springframework.spring5webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.spring5webapp.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
 
}