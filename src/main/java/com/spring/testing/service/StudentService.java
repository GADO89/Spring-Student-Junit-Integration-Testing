package com.spring.testing.service;

import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {



    public StudentDto createStudent(Student student) throws Exception;
    public List<StudentDto> getAllStudent();
    public void deleteStudent(Long id);
    public StudentDto editStudent(Student student);
    public StudentDto getStudentById(Long id);

}
