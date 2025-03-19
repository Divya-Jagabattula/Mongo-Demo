package com.example.Mongo_Demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Mongo_Demo.models.User;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository  extends MongoRepository<User,String>{ 
    // Custom query method to find a user by email
    User findByEmail(String email);
    // Custom query method to find a user by name
    List<User> findByName(String name);
    // Custom query method to delete the user by name
    void deleteByName(String name);
    // Custom query method to find a user by ID
    Optional<User> findById(String id);


   
    

    
}
    
