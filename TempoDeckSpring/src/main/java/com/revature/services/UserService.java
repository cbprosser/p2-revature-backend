package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.revature.models.User;
import com.revature.repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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

	public User findById(int uId) {
		return userRepo.findById(uId);
	}

	public User findByUsernameAndPassword(String username, String password) {

		User u = userRepo.findByUsernameAndPassword(username, password);

		if (u != null) {
			HttpServletRequest req = ((ServletRequestAttributes)
			RequestContextHolder.getRequestAttributes())
			.getRequest();
			req.getSession().setAttribute("user", u);
		}
		return u;
	}

	@Transactional
	public User createUser(User user) {
		userRepo.customSave(
			user.getUsername(),
			user.getPassword(),
			user.getEmail(),
			user.getFirstName(),
			user.getLastName(),
			user.getRole().getId()
		);

		return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
