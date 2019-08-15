package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Deck;
import com.revature.repos.DeckRepo;

@Service
public class DeckService {

	@Autowired
	private DeckRepo deckRepo;

	public List<Deck> findAll() {
		return deckRepo.findAll();
	}

	public Deck findByDeckId(int deckId) {
		return deckRepo.findById(deckId);
	}

	public List<Deck> findAllDecksByAuthor(int deckAuthor) {
		return deckRepo.findAllByAuthorUserId(deckAuthor);
	}
	

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
