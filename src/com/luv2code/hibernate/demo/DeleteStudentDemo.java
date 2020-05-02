package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        Student stu = null;
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            session.beginTransaction();

            Student studentToBeDeleted = session.get(Student.class, studentId);

            session.delete(studentToBeDeleted);

            session.getTransaction().commit();

            session.close();
        } catch (Exception e) {
            System.out.println("ERROOOOOOO ------->" + e);
        } finally {
            session.close();
            factory.close();
        }
    }
}
