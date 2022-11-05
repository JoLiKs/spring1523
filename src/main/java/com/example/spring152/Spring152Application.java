package com.example.spring152;

import com.example.spring152.models.PersonModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring152Application {
	public static void main(String[] args) {
		SpringApplication.run(Spring152Application.class, args);
		PersonModel personModel = new PersonModel(1, "JOJO", "Jotaro", 22);
        //add(personModel);
		//print(personModel);
	}
	public static void print(Object o) {
		System.out.println(o);
	}
	public static void add(PersonModel personModel) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(PersonModel.class);
		//session.saveOrUpdate(new PersonModel());
		session.getTransaction().commit();

		//session.save(personModel);

		tx.commit();
		session.close();
		HibernateUtil.close();
	}
}
