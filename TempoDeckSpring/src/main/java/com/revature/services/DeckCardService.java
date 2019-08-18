package com.revature.services;

import java.util.List;

import com.revature.models.DeckCard;
import com.revature.repos.DeckCardRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckCardService {

    @Autowired
    private DeckCardRepo deckCardRepo;

    public List<DeckCard> findAll() {
        return deckCardRepo.findAll();
    }

	public List<DeckCard> findByDeckID(int deckID) {
		return deckCardRepo.findByDeckId(deckID);
	}

    // public Deck findByDeckId(int deckId) {
    // return null;
    // }

    // public Deck removeDeck(int deckId) {
    // return null;
    // }

    // Examples for reference
    // public Brand update(Brand br) {
    // return brandRepo.saveAndFlush(br);
    // }

    // public Brand addIceCreamToBrand(int brandId, IceCream iceCream) {
    // Brand br = brandRepo.getOne(brandId);
    // br.getIceCream().add(iceCream);
    // return br;
    // }
}