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

	public List<Deck> findAllDecksByFeaturedCard(String deckFeaturedCard) {
		return deckRepo.findAllDecksByFeaturedCard(deckFeaturedCard);
	}

	public List<Deck> findAllDecksByFormat(String format) {
		return deckRepo.findAllByFormat_Format(format);
	}

	public Deck save(Deck deck) {
		return deckRepo.saveAndFlush(deck);
	}
	

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
