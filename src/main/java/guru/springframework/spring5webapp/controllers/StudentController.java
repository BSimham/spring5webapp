package guru.springframework.spring5webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import guru.springframework.spring5webapp.domain.Student;
import guru.springframework.spring5webapp.repositories.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
    private StudentRepository studentRepo;
       
    @GetMapping("/students")
    public String listAll(Model model) {
        List<Student> listStudents = studentRepo.findAll();
        model.addAttribute("listStudents", listStudents);
           
        return "students";
    }
}
