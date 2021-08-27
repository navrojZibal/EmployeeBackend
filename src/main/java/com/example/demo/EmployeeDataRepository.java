package com.example.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDataRepository extends MongoRepository<EmployeeData, String> {

	Optional<EmployeeData> findById(String id);
}
