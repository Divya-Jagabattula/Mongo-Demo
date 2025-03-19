package com.example.Mongo_Demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Mongo_Demo.repo.UserRepository;
import com.example.Mongo_Demo.models.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    //Method to get a user by mail
    public User getUserByMail(String mail) {
        return userRepository.findByEmail(mail);
    }
    public List<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserById(String id){
        System.out.println("Yaaay!!!..We are able to find the user based on ID!!!..");

        Optional<User> u1 = userRepository.findById(id);
        if(u1.isPresent()){
            System.out.println("User found!!!");
            User user1=u1.get();
            return userRepository.save(user1);
        }
                return null;
    }

    //Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //Method to update an existing user
    public User updateUser(String id,User user) {
       
        if (id == null || user == null)  {
            
            throw new IllegalArgumentException("User ID and user data must not be null");
        }
        if(!(id .equals(user.getId())))
        {
            throw new IllegalArgumentException("User ID and user data ID  must match");
        }
        System.out.println("Updating user with ID: " + id);
        System.out.println("Updating user with exisintg ID as : " + user.getId()+"\n\n\n");
        System.out.println(id.getClass());
        System.out.println(user.getClass());
        
        if(user != null){
            System.out.println("Hello, User Please Check your Details Before Updating");
            System.out.println("User ID: " + user.getId());
            System.out.println("Name:   " + user.getName());
            System.out.println("MailID: " + user.getEmail());
            System.out.println("Password:    " + user.getPassword()+"\n\n\n");
        }
            
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            return userRepository.save(updatedUser);
        }
        return null; // Handle user not found
    }
   
    public void deleteUser(String name) {
        System.out.println("delete started");
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            System.out.println("No user found with the name: " + name);
            return;
        }
        for (User user : users) {
            System.out.println("Hello, User Please Check your Details Before deletion ");
            System.out.println("Name:   " + user.getName());
            System.out.println("MailID: " + user.getEmail());
            System.out.println("Password:    " + user.getPassword() + "\n\n");
        }
    
        userRepository.deleteByName(name); // Ensure deleteByName deletes all occurrences
        System.out.println("Delete done based on name");
    }
    
    
}
