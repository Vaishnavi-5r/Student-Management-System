package com.jspiders.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springmvc.pojo.StudentPOJO;
import com.jspiders.springmvc.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;
	public StudentPOJO addStudent(String name, String email, long contact, String address) {
		StudentPOJO pojo = repository.addStudent(name,email,contact,address);
		return pojo;
		
	}
//	public StudentPOJO searchStudent(String name) {
//		 StudentPOJO pojo = repository.searchStudent(name);
//		 return pojo;
//	}
	public List<StudentPOJO> searchStudent(String name) {
		 List<StudentPOJO> pojo = repository.searchStudent(name);
		 return pojo;
	}
	
	
	public StudentPOJO deleteStudent(int id) {
		StudentPOJO pojo = repository.deleteStudent(id);
		return pojo;
		
	}
	public List<StudentPOJO> allStudent() {
		List<StudentPOJO> pojo = repository.allStudent();
		return pojo;
	}
	public StudentPOJO updateStudent(int id, String name, String email, long contact, String address) {
		StudentPOJO pojo = repository.updateStudent(id,name,email,contact,address);
		return pojo;
		
	}
	
	

	

}
	