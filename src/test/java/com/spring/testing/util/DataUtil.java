package com.spring.testing.util;

import com.spring.testing.model.Student;
import com.spring.testing.model.StudentDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DataUtil {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void delete(Object... entities){
        for (Object entity:entities){
         if (entity instanceof List){
             delete((List<?>) entity);
         }
         else {
             delete(entity);
         }
        }
    }
    @Transactional
    private void delete(List<?> entityList){
        for (Object entity:entityList){
            delete(entity);
        }
    }
    @Transactional
    private void delete(Object entity){
                try {
                    if (entity instanceof StudentDto){
                        Student student= entityManager.find(Student.class,((StudentDto) entity).getId());
                        entityManager.remove(student);
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
}
