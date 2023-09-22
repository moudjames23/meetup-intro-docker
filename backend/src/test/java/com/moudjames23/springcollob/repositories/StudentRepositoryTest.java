package com.moudjames23.springcollob.repositories;

import com.moudjames23.springcollob.entities.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Should not find student by phone")
    void itShouldNotByPhoneWhenStudentDoesnotExists() {
        // Given
        String email = "moud@gmail.com";

        // When
        Optional<Student> optionalStudent = this.studentRepository.findByEmail(email);

        // Then
        assertThat(optionalStudent).isEmpty();

    }

    @Test
    @DisplayName("Should find student by phone")
    void itShouldFindStudentByPhone() {
        // Given
        Student student = Student.builder()
                .name("Diallo Mamoudou")
                .email("moud23diallo@gmail.com")
                .age(23)
                .build();

        this.studentRepository.save(student);

        // When
        Optional<Student> optionalStudent = this.studentRepository.findByEmail(student.getEmail());

        // Then
        assertThat(optionalStudent).isPresent();
        assertThat(optionalStudent.get().getEmail()).isEqualTo(student.getEmail());
        assertThat(optionalStudent.get().getName()).isEqualTo(student.getName());
        assertThat(optionalStudent.get().getAge()).isEqualTo(student.getAge());
    }


}
