package qapitol.demo.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qapitol.demo.app.entity.Students;
import qapitol.demo.app.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class DemoController {
	@Autowired
	StudentRepository studentrepository;

	@GetMapping
	public ResponseEntity<List<Students>> getAllStudents() {
		List<Students> allStudents = studentrepository.findAll();
		return ResponseEntity.status(200).body(allStudents);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Students> getById(@PathVariable("id") Integer id) {
		if (studentrepository.exists(id)) {
			return ResponseEntity.status(200).body(studentrepository.findOne(id));
		} else {
			return ResponseEntity.status(404).build();
		}
	}

	@PostMapping()
	public ResponseEntity<Students> saveStudent(@RequestBody Students student) {
Students s =studentrepository.save(student);
		return ResponseEntity.status(201).body(s);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Students> editStudent(@RequestBody Students student,@Valid @PathVariable("id") Integer id) {

		return ResponseEntity.status(201).body(studentrepository.save(student));
	}

	@DeleteMapping("/{id}")
	public boolean deleteStudent(@PathVariable("id") Integer id) {
		boolean response = false;
		if (studentrepository.exists(id)) {
			studentrepository.delete(id);
			response=true;	
		} 
		return response;
	}

}
