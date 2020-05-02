package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		//Bloco do Criteria
		
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			List<Student> allStu = session.createQuery("from Student where email LIKE '%bbb%'").getResultList();

			// Opção 1 de Update: pegar todo mundo, fazer select, mudar os valores e retornar
			for(Student a: allStu) {
				System.out.println(a.getId() + " " +
						a.getEmail() + " " +
						a.getFirstName() + " " +
						a.getLastName() + " ");
				a.setEmail(a.getEmail().replaceAll("bbb.com2", "gmail.com"));

			}
			
			//Opção dois, fazer a queri e já executar
//			session.createQuery("Update Student set email = 'aaa@gmail.com' where id = 15").executeUpdate();


			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

}
