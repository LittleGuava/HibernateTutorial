package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;
import org.hibernate.criterion.Restrictions;


public class ReadStudentDemo {

	public static void main(String[] args) {
		Student stu = null;
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		
		//Bloco do Criteria
		
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();

			if (stu == null) {
				stu = (Student)session.createQuery("from Student where id = 1").getSingleResult();
			}

			Student getStudent = session.get(Student.class, stu.getId()  );

			System.out.println(getStudent.toString());

			List<Student> allStu = session.createQuery("from Student where first_name like 'Paul%'").getResultList();

			for(Student a: allStu) {
				System.out.println(a.getId() + " " +
						a.getEmail() + " " +
						a.getFirstName() + " " +
						a.getLastName() + " ");
			}

		} catch (Exception e) {
			System.out.println("ERROOOOOOO ------->" + e);
		} finally {
			session.close();
			factory.close();
		}
	}
}
