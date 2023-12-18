package com.spring.testing.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.testing.StudentApplicationIT;
import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import com.spring.testing.repo.StudentRepo;
import com.spring.testing.util.DataUtil;
import com.spring.testing.util.RequestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


public class StudentControllerIT extends StudentApplicationIT {

    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private DataUtil dataUtil;

/*
    @Test
    public void createStudent_thenValidate() throws JsonProcessingException {
       // Given Student
        Student student = new Student("Eslam",22,"01113903660",true);

        // When Create Student
        ResponseEntity<String> responseEntity =
                requestUtil.post("/api/create",student,null,String.class);

        // Then Validate
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        StudentDto studentDto = objectMapper.readValue(responseEntity.getBody(),StudentDto.class);
        Assertions.assertEquals("Eslam",studentDto.getName());
        Assertions.assertEquals(22,studentDto.getAge());
        Assertions.assertEquals("01113903660",studentDto.getPhone());
        Assertions.assertTrue(studentDto.isActive());

        // Clean Data
        dataUtil.delete(studentDto);
    }*/

    @Test
    public void createStudent_thenValidate() throws JsonProcessingException {
       // Student student=createStudent("Eslam",22,"01125589989",true);
         Student student = new Student("Eslam",22,"01113903660",true);

        ResponseEntity<String> responseEntity=
                requestUtil.post("/api/create",student,null, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        StudentDto studentDto=objectMapper.readValue(responseEntity.getBody(),StudentDto.class);
        Assertions.assertEquals("Eslam",studentDto.getName());
        Assertions.assertEquals(22,studentDto.getAge());
        Assertions.assertEquals("01113903660",studentDto.getPhone());
        Assertions.assertEquals(true,studentDto.isActive());
       // studentRepo.deleteById(studentDto.getId());
        dataUtil.delete(studentDto);

    }

    @Test
    public void getAllStudent_thenValidate() throws IOException {  //getAllStudent IT Clean Data
       createStudent("Eslam",22,"00005589989",true);
       createStudent("Ahmed",22,"01345589989",false);

        ResponseEntity<String> responseEntity=
                requestUtil.get("/api/getAll",null,null, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

         StudentDto[] studentListDto = objectMapper.readValue(responseEntity.getBody(),StudentDto[].class);
        Assertions.assertEquals(2,studentListDto.length);

        Assertions.assertEquals("Ahmed",studentListDto[1].getName());
        Assertions.assertEquals(22,studentListDto[1].getAge());
        Assertions.assertEquals("01345589989",studentListDto[1].getPhone());
        Assertions.assertFalse(studentListDto[1].isActive());

        //studentRepo.deleteById(student1.getId());
        //studentRepo.deleteById(student2.getId());
        dataUtil.delete(studentListDto);

    }
    @Test
    public void deleteStudent_thenValidate() throws JsonProcessingException {
        Student student=createStudent("Eslam",22,"00005589989",true);

        studentRepo.save(student);
        ResponseEntity<String> responseEntity=
                requestUtil.delete(String.format("/api/delete/%s",student.getId()),null,null, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void editStudent_thenValidate() throws JsonProcessingException {
        Student student=createStudent("Karim",21,"01125589989",false);

        student= studentRepo.save(student);
        System.out.println("ID "+student.getId());
        student.setName("Ahmed");
        ResponseEntity<String> responseEntity=
                requestUtil.put("/api/edit",student,null, String.class);
        StudentDto studentDto = objectMapper.readValue(responseEntity.getBody(),StudentDto.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("Ahmed",studentDto.getName());
        //studentRepo.deleteById(student.getId());
        dataUtil.delete(studentDto);
    }
    @Test
    public void getStudentById_thenValidate() throws JsonProcessingException {
        Student student=createStudent("Karim",21,"01125589989",false);

        ResponseEntity<String> responseEntity=
                requestUtil.get(String.format("/api/get/%s",student.getId()),null,null, String.class);
        StudentDto studentDto=objectMapper.readValue(responseEntity.getBody(),StudentDto.class);
        Assertions.assertEquals("Karim",studentDto.getName());
        Assertions.assertEquals(21,studentDto.getAge());
        Assertions.assertEquals("01125589989",studentDto.getPhone());
        Assertions.assertEquals(false,studentDto.isActive());
      //  studentRepo.deleteById(student.getId());
        dataUtil.delete(studentDto);

    }
    public Student createStudent( String name, int age, String phone, boolean active){
        Student student=new Student(name,age,phone,active);
      return   studentRepo.save(student);
    }
}
