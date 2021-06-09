package com.esiz.week5.mission8;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:30
 * @Description:
 */
@Data
@Component("school2")
public class School {
    private final Klass class1;

    @Autowired
    public School(Klass class1) {
        this.class1 = class1;
    }

    public void ding() {
        System.out.println("java config ding =======>");
        System.out.println("School1.ding -> student info : id = " + class1.getStudent().getId() + " ;name =" + class1.getStudent().getName());
    }
}
