package com.jspiders.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc.pojo.StudentPOJO;
import com.jspiders.springmvc.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/home")
	public String home() {
		return "Home";
	}
	@GetMapping("/add")
	public String addPage() {
		return "AddStudent";
	} 
	
	@PostMapping("/add")
	public String addStudent(@RequestParam String name,@RequestParam String email,
			@RequestParam long contact,@RequestParam String address, ModelMap map) {
		
		StudentPOJO pojo  = service.addStudent(name,email,contact,address);
		if (pojo != null) {
			map.addAttribute("msg","data inserted successfully..!");
			return "AddStudent";
		}
		map.addAttribute("msg","data not inserted..!");
		return "AddStudent";
		
	}

	@GetMapping("/search")
	public String searchPage() {
		return "SearchStudent";
	}
//	@PostMapping("/search")
//	public String searchStudent(@RequestParam String name, ModelMap map)
//	{
//		StudentPOJO pojo = service.searchStudent(name);
//		if (pojo != null) {
//			map.addAttribute("student",pojo);
//			return "SearchStudent";
//		}
//		map.addAttribute("msg","student data does not exist..!!");
//		return "SearchStudent";
//	}
//	
	
	
	@PostMapping("/search")
	public String searchStudent(@RequestParam String name, ModelMap map)
	{
		List<StudentPOJO> pojo = service.searchStudent(name);
		if (pojo != null) {
			map.addAttribute("students",pojo);
			return "SearchStudent";
		}
		map.addAttribute("msg","student data does not exist..!!");
		return "SearchStudent";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(ModelMap map) {
		
		List<StudentPOJO> students = service.allStudent(); 
		map.addAttribute("students", students);
		
		return "DeleteStudent";
	}
	
	@PostMapping("/delete")
	public String deleteStudent(@RequestParam int id, ModelMap map) {
		StudentPOJO pojo = service.deleteStudent(id);
		if (pojo != null) {
			List<StudentPOJO> students = service.allStudent(); 
			map.addAttribute("students", students);
			map.addAttribute("msg", "Student Data removed Successfully..!");
			return "DeleteStudent";
		}
		List<StudentPOJO> students = service.allStudent(); 
		map.addAttribute("students", students);
		map.addAttribute("msg","Data doesnot exists..!!");
		return "DeleteStudent";
	}

	@GetMapping("/update")
	public String updatePage(ModelMap map) {
		List<StudentPOJO> students = service.allStudent(); 
		map.addAttribute("students", students);
		return "UpdateStudent";
		
	}
//	@PostMapping("/update")
//	public String updateForm(@RequestParam String name, ModelMap map) {
//		StudentPOJO pojo = service.searchStudent(name);
//		if (pojo != null) {
//			map.addAttribute("student", pojo);
//			return "UpdateStudent";
//		}
//		map.addAttribute("msg", "student data does not exists.");
//		List<StudentPOJO> students = service.allStudent(); 
//		map.addAttribute("students", students);
//		return "UpdateStudent";
//		
//	}
	
	@PostMapping("/updateData")
	public String updateData(@RequestParam int id, @RequestParam String name,
			@RequestParam String email,@RequestParam long contact, @RequestParam String address, ModelMap map ) {
		StudentPOJO pojo = service.updateStudent(id,name,email,contact,address);
		if (pojo != null) {
			map.addAttribute("msg","student data updated..!");
			List<StudentPOJO> students = service.allStudent(); 
			map.addAttribute("students", students);
			return "UpdateStudent";
		}
		map.addAttribute("msg","student data not updated..!");
		List<StudentPOJO> students = service.allStudent(); 
		map.addAttribute("students", students);
		return "UpdateStudent";
	}
	
	
	
	
	

}
