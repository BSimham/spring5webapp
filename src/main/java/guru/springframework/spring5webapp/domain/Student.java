package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
 
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    private String name;
    private String email;
 
    // getters and setters...
}
