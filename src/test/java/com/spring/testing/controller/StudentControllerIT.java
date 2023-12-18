package com.spring.testing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.testing.StudentApplicationIT;
import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import com.spring.testing.repo.StudentRepo;
import com.spring.testing.util.RequestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class StudentControllerIT extends StudentApplicationIT {

    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentRepo studentRepo;
    @Test
    public void createStudent_thenValidate() throws JsonProcessingException {
        Student student=new Student();
        student.setName("Eslam");
        student.setAge(22);
        student.setPhone("01125589989");
        student.setActive(true);
        ResponseEntity<String> responseEntity=
                requestUtil.post("api/create/",student,null, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        StudentDto studentDto=objectMapper.readValue(responseEntity.getBody(), StudentDto.class);
        Assertions.assertEquals("Eslam",studentDto.getName());
        Assertions.assertEquals(22,studentDto.getAge());
        Assertions.assertEquals("01125589989",studentDto.getPhone());
        Assertions.assertEquals(true,studentDto.isActive());
        System.out.println(studentDto.getId());
        studentRepo.deleteById(studentDto.getId());

    }
    @Test
    public void getAllStudent_thenValidate(){
        ResponseEntity<Object> responseEntity=
                requestUtil.get("api/getAll/",null,null, Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void deleteStudent_thenValidate(){
        ResponseEntity<Object> responseEntity=
                requestUtil.delete("api/delete/4",null,null, Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void editStudent_thenValidate(){
        Student student=new Student();
        student.setId(1);
        student.setName("Karim");
        student.setAge(21);
        student.setPhone("01125589989");
        student.setActive(false);
        ResponseEntity<Object> responseEntity=
                requestUtil.put("api/edit/",student,null, Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void getStudentById_thenValidate(){
        ResponseEntity<Object> responseEntity=
                requestUtil.get("api/get/1",null,null, Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
