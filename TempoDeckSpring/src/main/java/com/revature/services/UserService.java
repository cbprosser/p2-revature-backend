package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findByDeckId(int user_id) {
		return userRepo.findById(user_id);
	}
	

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
