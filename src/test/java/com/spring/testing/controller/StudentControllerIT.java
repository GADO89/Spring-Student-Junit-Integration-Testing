package com.spring.testing.controller;

import com.spring.testing.StudentApplicationIT;
import com.spring.testing.util.RequestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class StudentControllerIT extends StudentApplicationIT {

    @Autowired
    private RequestUtil requestUtil;

   @Test
    public void getAllStudent_thenValidate(){
     ResponseEntity<Object> responseEntity=
             requestUtil.get("api/getAll/",null,null, Object.class);
       Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }
}
