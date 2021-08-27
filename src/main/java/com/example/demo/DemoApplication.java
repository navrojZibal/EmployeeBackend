package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration("employeeList.EmployeeDataRepository")

public class DemoApplication {

//	@Autowired
//	EmployeeRepo repository;
//	
//	@Autowired
//	EmployeeDataRepository repo;
//	
//	@PostConstruct
//	public void initUsers() {
//		List<Employee> users = Stream.of(
//				new Employee(1,"navroj@zibal.com","navroj123","navroj","Navroj","Sandhu"),
//				new Employee(2,"mani@zibal.com","mani123","mani","Mani","Bansal")
//				).collect(Collectors.toList());
//		
//		repository.saveAll(users);
//	}
//	Date date = new Date();
//	
//	@PostConstruct
//	public void initEmployees() {
//		List<EmployeeData> users = Stream.of(
//				new EmployeeData(1,"Navroj"," Sandhu","Software Engineer","10000","Male",date,true),
//				new EmployeeData(2,"Mani"," Bansal","Software Engineer","30000","Male",date,true)
//				).collect(Collectors.toList());
//		
//		repo.saveAll(users);
//	}
//	
	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:4200/");
				
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
