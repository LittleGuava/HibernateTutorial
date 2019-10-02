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
			
			//criando os objetos
			System.out.println("Criando os Objetos...");
			Student stu = new Student("Paul3", "Walker3", "aaa@bbb.com2");
			Student stu2 = new Student("Paul4", "Walker4", "aaa@bbb.com2");
			Student stu3 = new Student("Paul5", "Walker5", "aaa@bbb.com2");
			
			//abrindo a transação com banco
			session.beginTransaction();
			System.out.println("Salvando ...");
			
			session.save(stu);
			session.save(stu2);
			session.save(stu3);
			
			System.out.println("Commitando as mudanças");
			
			//commitando
			session.getTransaction().commit();
			
			System.out.println("Salvou com Sucesso");
			
			//possivelmente desnecessário, serve pra garantir que a conexão fechou
			session.close();
		
		} finally {
			factory.close();
		}
	}

}
