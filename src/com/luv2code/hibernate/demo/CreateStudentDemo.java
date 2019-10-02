package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("Creating new student object...");
			Student stu = new Student("Paul3", "Walker3", "aaa@bbb.com2");
			Student stu2 = new Student("Paul4", "Walker4", "aaa@bbb.com2");
			Student stu3 = new Student("Paul5", "Walker5", "aaa@bbb.com2");
			
			session.beginTransaction();
			
			System.out.println("Salvando ...");
			
			session.save(stu);
			session.save(stu2);
			session.save(stu3);
			
			session.getTransaction().commit();
			
			session.close();
			
//			session.flush();
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}

}
