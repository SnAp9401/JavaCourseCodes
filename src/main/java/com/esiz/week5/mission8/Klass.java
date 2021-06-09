package com.esiz.week5.mission8;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:32
 * @Description:
 */
@Data
@Component("class2")
public class Klass {
    private Student student;

    @Autowired
    public Klass(Student student) {
        this.student = student;
    }

    @Autowired
    public void dong() {
        System.out.println("auto config dong =======>");
        System.out.println("Klass1.dong -> student info:id =" + student.getId() + " ;name =" + student.getName());
    }
}
