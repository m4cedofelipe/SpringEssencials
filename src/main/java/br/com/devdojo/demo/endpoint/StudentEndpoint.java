package br.com.devdojo.demo.endpoint;

import br.com.devdojo.demo.error.ResourceNotFoundException;
import br.com.devdojo.demo.model.Student;
import br.com.devdojo.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentEndpoint {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    public ResponseEntity listAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        checkIfStudentExists(id);
        Optional<Student> student = repository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        checkIfStudentExists(id);
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        checkIfStudentExists(student.getId());
        repository.save(student);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void checkIfStudentExists(Long id) {
        if (!repository.findById(id).isPresent())
            throw new ResourceNotFoundException("Student not found for ID: " + id);
    }
}
