package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

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
        return deckRepo.findByDeckId(deckId);
	}


	public Deck removeDeck(int deckId) {
		return null;
	}
}
	