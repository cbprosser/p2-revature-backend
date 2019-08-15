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

	public Deck findByDeckId(Number deck_id) {
		return deckRepo.findById(deck_id);
	}
	

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
