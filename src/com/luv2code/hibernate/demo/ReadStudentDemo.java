package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

import net.bytebuddy.asm.Advice.This;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			System.out.println("Creating new student object...");
			Student stu = new Student("Carlos", "Barbosa", "aaa@bbb.com2");

			session.beginTransaction();

			System.out.println("Salvando ...");

			session.save(stu);

			System.out.println("Id do Usuário: " + stu.getId());

			Student getStudent = session.get(Student.class, stu.getId());

			System.out.println(getStudent + "" + getStudent.getEmail() + getStudent.getFirstName());

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}

}
