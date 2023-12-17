package com.spring.testing.service;

import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import com.spring.testing.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepo studentRepo;
    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo=studentRepo;
    }

    @Override
    public StudentDto createStudent(Student student) throws Exception{
        if (student.getId()>0) {
            throw new IllegalArgumentException("You musst not send id");
        }
        if (student.getId()<0){
            throw new RuntimeException(" Invalid id (id<0) and You musst not send id");
        }
        return studentDto(studentRepo.save(student));
    }

    @Override
    public List<StudentDto> getAllStudent() {
        return studentListDto(studentRepo.findAll());
    }

    @Override
    public void deleteStudent(Long id) {
      studentRepo.deleteById(id);
    }

    @Override
    public StudentDto editStudent(Student student) {
        return studentDto(studentRepo.save(student));
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return  studentDto(studentRepo.findById(id).get());
    }


    private   StudentDto studentDto(Student student){
        StudentDto studentDto= new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setId(student.getId());
        studentDto.setAge(student.getAge());
        studentDto.setPhone(student.getPhone());
        studentDto.setActive(student.isActive());

        return studentDto;
    }
    private  List<StudentDto> studentListDto(List<Student> students){
        List<StudentDto> studentDtoListtDto=new ArrayList<>();
        for (Student student: students){
        StudentDto studentDto= new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setId(student.getId());
        studentDto.setAge(student.getAge());
        studentDto.setPhone(student.getPhone());
        studentDto.setActive(student.isActive());
        studentDtoListtDto.add(studentDto);

        }
        return studentDtoListtDto;
    }
}
