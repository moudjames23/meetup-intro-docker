package com.moudjames23.springcollob.controllers;

import com.moudjames23.springcollob.config.MyHttpResponse;
import com.moudjames23.springcollob.dtos.StudentDto;
import com.moudjames23.springcollob.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.moudjames23.springcollob.config.MyHttpResponse.response;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> allStudents()
    {
        List<StudentDto> students = this.studentService.getAllStudents();

        return response(HttpStatus.OK, "List of students", students);
    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody StudentDto studentDto)
    {
        StudentDto student = this.studentService.create(studentDto);

        return response(HttpStatus.CREATED, "Student added", student);

    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> show(@PathVariable("studentId") long studentId)
    {
        StudentDto student = this.studentService.show(studentId);

        return response(HttpStatus.OK, "Student's info", student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> update(@PathVariable("studentId") long studendId, @Valid @RequestBody StudentDto studentDto)
    {
        StudentDto updatedStudent = this.studentService.update(studendId, studentDto);

        return response(HttpStatus.OK, "Student updated", updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> delete(long studentId)
    {
        this.studentService.delete(studentId);

        return response(HttpStatus.OK, "Student deleted", null);
    }
}
