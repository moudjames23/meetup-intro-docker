package com.moudjames23.springcollob.services;

import com.moudjames23.springcollob.dtos.StudentDto;
import com.moudjames23.springcollob.entities.Student;
import com.moudjames23.springcollob.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        this.studentService = new StudentService(this.studentRepository, modelMapper);
    }

    private Student jordan = Student.builder()
            .id(1L)
            .name("Michael Jordan")
            .age(52)
            .email("jordan@gmail.com")
            .build();

    private Student lebron = Student.builder()
            .id(2L)
            .name("LeBron James")
            .age(38)
            .email("jordan@gmail.com")
            .build();

    private Student kobe = Student.builder()
            .id(3L)
            .name("Kobe Bryant")
            .age(47)
            .email("kobe@gmail.com")
            .build();

    @Test
    @DisplayName("Should list of students")
    void itShouldListAllStudents() {
        // Given
        // When
        // Then
        this.studentService.getAllStudents();

        verify(this.studentRepository).findAll();
    }

    @Test
    @DisplayName("Should save new student")
    void itShouldCreateNewStudent() {
        // Given
        // When
        this.studentRepository.save(jordan);

        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);

        // Then
        verify(this.studentRepository).save(argumentCaptor.capture());

        Student  excepted = argumentCaptor.getValue();

        assertThat(excepted).isEqualToComparingFieldByField(jordan);
    }


}
