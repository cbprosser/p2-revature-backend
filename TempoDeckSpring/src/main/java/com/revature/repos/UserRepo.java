package com.revature.repos;

import java.util.List;

import com.revature.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

     List<User> findAll();

     User findById(int user_id);

     @Query("FROM User WHERE username = :username AND password = crypt(:password, (SELECT password FROM User WHERE username = :username))")
     User findByUsernameAndPassword(String username, String password);

}