package com.revature.repos;

import java.util.List;

import com.revature.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

     List<User> findAll();

     User findById(int user_id);

     @Query("FROM User WHERE username = :username AND password = crypt(:password, (SELECT password FROM User WHERE username = :username))")
     User findByUsernameAndPassword(String username, String password);

     @Modifying
     @Query(value="INSERT INTO td_user (username, password, email, first_name, last_name, role_id)" + 
     "VALUES (:username, crypt(:password, gen_salt('bf', 7)), :email, :firstName, :lastName, :id)",
     nativeQuery=true)
	void customSave(String username, String password, String email, String firstName, String lastName, int id);

}