package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import com.revature.dtos.DeckConvertedNoCards;
import com.revature.models.Deck;
import com.revature.repos.DeckRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return deckRepo.findAllByAuthorId(deckAuthor);
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

	@Transactional
	public Deck updateDeck(Deck deck) {
		Deck deckToUpdate = deckRepo.getOne(deck.getId());
		deckToUpdate.setAuthor(deck.getAuthor());
		deckToUpdate.setDescription(deck.getDescription());
		deckToUpdate.setFeaturedCard(deck.getFeaturedCard());
		deckToUpdate.setFormat(deck.getFormat());
		deckToUpdate.setName(deck.getName());
		deckToUpdate.setPrivate(deck.isPrivate());
		deckToUpdate.setPrototype(deck.isPrototype());
		return deckToUpdate;
	}

	@Transactional
	public Deck updateDeck(DeckConvertedNoCards deck) {
		Deck deckToUpdate = deckRepo.getOne(deck.getId());
		deckToUpdate.setDescription(deck.getDeckDescription());
		deckToUpdate.setFeaturedCard(deck.getFeaturedCard());
		deckToUpdate.setFormat(deck.getFormat());
		deckToUpdate.setName(deck.getDeckName());
		deckToUpdate.setPrivate(deck.isPrivate());
		deckToUpdate.setPrototype(deck.isPrototype());
		return deckToUpdate;
	}

	// public Deck removeDeck(int deckId) {
	// return null;
	// }
}
