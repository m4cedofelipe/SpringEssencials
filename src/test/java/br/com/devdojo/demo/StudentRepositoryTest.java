package br.com.devdojo.demo;

import br.com.devdojo.demo.model.Student;
import br.com.devdojo.demo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/* Configuração para realizar teste utilizando o banco real */
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        Student student = new Student("felipe", "felipe@macedo.com.br");

        this.studentRepository.save(student);

        Assertions.assertThat(student.getId()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo("felipe");
        Assertions.assertThat(student.getEmail()).isEqualTo("felipe@macedo.com.br");
    }

    @Test
    public void deleteShouldRemoveData() {
        Student student = new Student("felipe", "felipe@macedo.com.br");

        this.studentRepository.save(student);
        studentRepository.delete(student);

        Assertions.assertThat(this.studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    public void updateShouldAndPersist() {
        Student student = new Student("felipe", "felipe@macedo.com.br");
        this.studentRepository.save(student);

        student.setName("felipeMacedo");
        student.setEmail("felipe1@macedo.com.br");
        this.studentRepository.save(student);
        student = studentRepository.getOne(student.getId());

        Assertions.assertThat(student.getName()).isEqualTo("felipeMacedo");
        Assertions.assertThat(student.getEmail()).isEqualTo("felipe1@macedo.com.br");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
        Student student = new Student("felipe", "felipe@macedo.com.br");
        Student student2 = new Student("Felipe", "felipe@macedo.com.br");

        studentRepository.save(student);
        studentRepository.save(student2);

        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("felipe");

        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void createWhenNameIsNullShouldThrowContraintViolationExecption() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("O campo nome do estudante é obrigatório");

        this.studentRepository.save(new Student());
        entityManager.flush();
    }

    @Test
    public void createWhenEmailIsNullShouldThrowContraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("não pode estar em branco");

        Student student = new Student();
        student.setName("felipe");

        this.studentRepository.save(student);
        entityManager.flush();
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowContraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um e-mail válido");

        Student student = new Student();
        student.setName("felipe");
        student.setEmail("felipe");

        this.studentRepository.save(student);
        entityManager.flush();
    }

}
