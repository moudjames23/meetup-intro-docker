package com.moudjames23.springcollob.services;

import com.moudjames23.springcollob.dtos.StudentDto;
import com.moudjames23.springcollob.entities.Student;
import com.moudjames23.springcollob.exceptions.ResourceAlreadyExistException;
import com.moudjames23.springcollob.exceptions.ResourceNotFoundException;
import com.moudjames23.springcollob.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDto> getAllStudents()
    {
        List<Student> students = this.studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    public StudentDto create(StudentDto studentDto)
    {
        Optional<Student> optionStudent = this.studentRepository.findByEmail(studentDto.getEmail());

        if (optionStudent.isPresent())
            throw new ResourceAlreadyExistException("This mail "+studentDto.getEmail()+"  is already exists");


        Student student = this.studentRepository.save(modelMapper.map(studentDto, Student.class));

        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto show(long studentId)
    {
        Student student = this.getStudentById(studentId);
        return modelMapper.map(student, StudentDto.class);
    }

    public StudentDto update(long studentId, StudentDto studentDto)
    {
        Student student = this.getStudentById(studentId);

        studentDto.setId(studentId);

        Student updatedStudent = this.studentRepository.save(modelMapper.map(studentDto, Student.class));

        return this.modelMapper.map(updatedStudent, StudentDto.class);
    }

    public void delete(long studentId)
    {
        this.studentRepository.delete(this.getStudentById(studentId));
    }

    public Student getStudentById(long studentId)
    {
        return  this.studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with  this  ID " +studentId+ " doesn't exist" ));

    }
}
