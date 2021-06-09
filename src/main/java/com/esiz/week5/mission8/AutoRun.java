package com.esiz.week5.mission8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config注解实现bean
 *
 * @Author: hxt
 * @Date: 2021-06-04 18:06
 * @Description:
 */
@Configuration
@ComponentScan("com.esiz.week5.mission8")
public class AutoRun {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoRun.class);
        Klass class2 = context.getBean("class2", Klass.class);
        class2.dong();
        School school = context.getBean("school2", School.class);
        school.ding();
    }
}
