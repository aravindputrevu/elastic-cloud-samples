package aci.apm.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import aci.apm.Students;
import aci.apm.StudentRepository;

import org.springframework.ui.Model;


@RestController
public class WebController {

private final StudentRepository studentRepository;

public WebController(StudentRepository studentRepository) {
      this.studentRepository = studentRepository;
}

@GetMapping("/")
public Iterable<Students> getAllStudents() {
  return studentRepository.findAll();
}

@PostMapping("/students")
@ResponseStatus(HttpStatus.CREATED)
public Students createStudent(@RequestBody Students student) {
    return studentRepository.save(student);
}

}