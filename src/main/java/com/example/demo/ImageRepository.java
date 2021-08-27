package com.example.demo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends MongoRepository<ImageModel, String> {
	
	Optional<ImageModel> findById(String id);
}
