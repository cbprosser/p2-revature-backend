package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import com.revature.models.DeckCard;
import com.revature.repos.DeckCardRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeckCardService {

    @Autowired
    private DeckCardRepo deckCardRepo;

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "deck")
                    .and(new Sort(Sort.Direction.ASC, "card"));
    }

    public List<DeckCard> findAll() {
        Sort sortSpec = orderBy();
        return deckCardRepo.findAll(sortSpec);
    }

	public List<DeckCard> findByDeckID(int deckID) {
		return deckCardRepo.findByDeckId(deckID);
	}

    @Transactional
	public List<DeckCard> createDeck(List<DeckCard> deck) {
		return deckCardRepo.saveAll(deck);
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