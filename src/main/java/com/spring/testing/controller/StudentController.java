package com.spring.testing.controller;

import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import com.spring.testing.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student) throws Exception{
    return     ResponseEntity.ok(studentService.createStudent(student));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentDto>>  getAllStudent(){
        return    ResponseEntity.ok(studentService.getAllStudent());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity  deleteStudent(@PathVariable("id")  Long id){
        studentService.deleteStudent(id);
       return ResponseEntity.ok().build();
    }
    @PutMapping("/edit")
    public ResponseEntity<StudentDto> editStudent(@RequestBody Student student){
        return    ResponseEntity.ok(studentService.editStudent(student));
    }
    @GetMapping("/get/{id}")
    //@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id")  Long id){
        return      ResponseEntity.ok(studentService.getStudentById(id));
    }

}
