package com.jspiders.springmvc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springmvc.pojo.StudentPOJO;

@Repository
public class StudentRepository {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpql;

	public static void openConnection() {
		factory = Persistence.createEntityManagerFactory("springmvc");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
	}

	public static void closeConnection() {
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public StudentPOJO addStudent(String name, String email, long contact, String address) {
		openConnection();
		transaction.begin();
		StudentPOJO pojo = new StudentPOJO();
		pojo.setName(name);
		pojo.setEmail(email);
		pojo.setContact(contact);
		pojo.setAddress(address);

		manager.persist(pojo);
		transaction.commit();
		closeConnection();
		return pojo;
	}

//	public StudentPOJO searchStudent(String name) {
//		openConnection();
//		transaction.begin();
//		StudentPOJO pojo = manager.find(StudentPOJO.class, name);
//		if (pojo != null) {
//			transaction.commit();
//			closeConnection();
//			return pojo;
//		}
//
//		transaction.commit();
//		closeConnection();
//
//		return null;
//	}
	
	public List<StudentPOJO> searchStudent(String name) {
		openConnection();
		transaction.begin();
		StudentPOJO pojo = new StudentPOJO();
		jpql = "from StudentPOJO p where (p.name like '%esh')";
		query = manager.createQuery(jpql);
		List<StudentPOJO> students = query.getResultList();
		transaction.commit();
		closeConnection();

		return students;
	}

	public StudentPOJO deleteStudent(int id) {
		openConnection();
		transaction.begin();
		StudentPOJO pojo = manager.find(StudentPOJO.class, id);
		
		if (pojo != null) {
			manager.remove(pojo);
			transaction.commit();
			closeConnection();
			return pojo;
		}

		transaction.commit();
		closeConnection();
		return null;
	}

	public List<StudentPOJO> allStudent() {
		openConnection();
		transaction.begin();
		jpql = "from StudentPOJO";
		query = manager.createQuery(jpql);
		List<StudentPOJO> students = query.getResultList();
		transaction.commit();
		closeConnection();
		return students;

	}

	public StudentPOJO updateStudent(int id, String name, String email, long contact, String address) {
		openConnection();
		transaction.begin();
		StudentPOJO pojo = manager.find(StudentPOJO.class, id);
		pojo.setName(name);
		pojo.setEmail(email);
		pojo.setContact(contact);
		pojo.setAddress(address);
		manager.persist(pojo);
		transaction.commit();
		closeConnection();
		return pojo;

	}

}
