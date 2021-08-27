package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {
	
	
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private EmployeeDataRepository repository;
	
	public List<EmployeeData> getEmployees() {
		try {
			List<EmployeeData> emp = repository.findAll();
			if(emp.isEmpty()) {
				throw new BuisnessException(httpStatusCode.no_content);
			}else {
				return emp;
			}
			
		}catch (BuisnessException e) {
			throw new BuisnessException(httpStatusCode.internal_server_error);
		}
	}
	
	public Optional<Employee> loginEmployee(String id){
		return repo.findById(id);
	}
	public Optional<EmployeeData> getEmployee(String id) {
		try {
			return repository.findById(id);
		}catch(IllegalArgumentException e) {
			throw new BuisnessException(httpStatusCode.not_found);
		}catch(NoSuchElementException e) {
			throw new BuisnessException(httpStatusCode.forbidden);
		}
		
	}
	
	public void addEmployee(Employee emp) {
		 repo.save(emp);
		
	}
	
	public EmployeeData addEmployeeData(EmployeeData emp) {
		try {
			if(emp.getFirstname().isEmpty() || emp.getFirstname().trim().length()==0) {
				throw new BuisnessException(httpStatusCode.bad_request);
			}else {
				EmployeeData empo = repository.save(emp);
				return empo;
			}
			
		}catch(IllegalArgumentException e) {
			throw new BuisnessException(httpStatusCode.forbidden);
		}
		
	}
	
	public void updateEmployee(Employee emp) {
		repo.save(emp);
	}
	
	public EmployeeData updateEmployeeData(EmployeeData emp) {
		return repository.save(emp);
	}
	
	
	public void deleteEmployee(String id) {
		try {
			repository.deleteById(id);	
		}catch(BuisnessException e) {
			throw new BuisnessException(httpStatusCode.bad_request);
			
		}catch(IllegalArgumentException e) {
			throw new BuisnessException(httpStatusCode.no_content);
		}
		
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee emp = repo.findByUserName(username);
		return new User(emp.getUsername(),emp.getPassword(),new ArrayList<>());
		
	}
}
