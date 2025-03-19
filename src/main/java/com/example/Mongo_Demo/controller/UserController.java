package com.example.Mongo_Demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mongo_Demo.models.User;
import com.example.Mongo_Demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/users") // if we are using this annotation , then we need to import the above line
public class UserController {
    @Autowired
    private UserService userService;


    //http://localhost:8080/
    @GetMapping("/")
    public String getPage(HttpServletRequest request){
        return "Welcome TO my first restAPI built using MongoDB"+request.getSession().getId();
    }
    
    //http://localhost:8080/search/123
    @GetMapping("/search/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if(user != null){
            System.out.println("Hello, User Please Check your Details");
            System.out.println("Name:   " + user.getName());
            System.out.println("MailID: " + user.getEmail());
            System.out.println("Password:    " + user.getPassword()+"\n\n\n");
            
            return ResponseEntity.ok(user);
        }
       // return ResponseEntity.status(404).build().toString();
        
        return ResponseEntity.notFound().build(); 
        
        
    }
    

    //http://localhost:8080/error
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        System.out.println(" HELLO GUYS THIS IS AN ERROR PAGE");
        return ResponseEntity.status(500).body("Error , OOPS something went wrong....");
    }


    //http://localhost:8080/email?email=emilia_clarke@gameofthron.es
    //get user by email
    @GetMapping("/search/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email)
    {
        User user = userService.getUserByMail(email);
        if(user != null){
            System.out.println("Hello, User Please Check your Details");
            System.out.println("Name:   " + user.getName());
            System.out.println("MailID: " + user.getEmail());
            System.out.println("Password:    " + user.getPassword()+"\n\n\n");
            
            return ResponseEntity.ok(user);
        }
       // return ResponseEntity.status(404).build().toString();
        
        return ResponseEntity.notFound().build();   
        

    }
    
    @GetMapping("/search/name")
public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
    List<User> users = userService.getUserByName(name); // Expecting a List<User>
    
    if (!users.isEmpty()) {
        System.out.println("Users Found with Name: " + name);
        for (User user : users) {
            System.out.println("Name:   " + user.getName());
            System.out.println("MailID: " + user.getEmail());
            System.out.println("Password:    " + user.getPassword() + "\n");
        }

        return ResponseEntity.ok(users);
    }

    return ResponseEntity.notFound().build();
}
    //create a new user
    @PostMapping("/create")
    // http://localhost:8080/create
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User createdUser = userService.createUser(user);
        System.out.println("Hey the creation of this new user is successfull!!!..Congrats");
        return ResponseEntity.ok(createdUser);
    }

    //update an existing user by ID
    //http://localhost:8080/update/67c36b824f6fff6680ae379e

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user)
    { 
        
        User updatedUser = userService.updateUser(id, user);
        System.out.println("Hey the updation of this user is successfull!!!..Congrats");
        if(updatedUser != null){
            System.out.println("Hello, User Please Check your updated user Details");
            System.out.println("Name:   " + updatedUser.getName());
            System.out.println("MailID: " + updatedUser.getEmail());
            System.out.println("Password:   "+ updatedUser.getPassword()+"\n\n\n");
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }


   
    //delete a user by name
    //http://localhost:8080/delete/Sai%20Teja
    @DeleteMapping("/delete/{name}")
    public void deleteUser(@PathVariable String name){
        userService.deleteUser(name);
    }
    


}
