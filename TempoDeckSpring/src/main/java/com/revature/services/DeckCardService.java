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
        return new Sort(Sort.Direction.ASC, "deck").and(new Sort(Sort.Direction.ASC, "card"));
    }

    public List<DeckCard> findAll() {
        Sort sortSpec = orderBy();
        return deckCardRepo.findAll(sortSpec);
    }

    public List<DeckCard> findByDeckID(int deckID) {
        return deckCardRepo.findByDeckIdOrderByCardAsc(deckID);
    }

    @Transactional
    public List<DeckCard> createDeck(List<DeckCard> deck) {
        return deckCardRepo.saveAll(deck);
    }

    @Transactional
    public List<DeckCard> updateDeck(List<DeckCard> deck, int deckID) {
        List<DeckCard> oldCards = deckCardRepo.findByDeckId(deckID);
        List<DeckCard> newCards = deck;
        for (int j = 0; j < oldCards.size(); j++) {
            boolean delete = true;
            for (int i = 0; i < newCards.size(); i++) {
                if (newCards.get(i).getCard().equals(oldCards.get(j).getCard())) {
                    delete = false;
                    oldCards.get(j).setCardAmount(newCards.get(i).getCardAmount());
                    newCards.remove(i);
                    i--;
                    break;
                }
            }
            if (delete) {
                oldCards.get(j).setCardAmount(0);
            }
        }
        deckCardRepo.saveAll(newCards);
        List<DeckCard> returnCards = deckCardRepo.findByDeckIdOrderByCardAsc(deckID);
        deckCardRepo.flush();
        return returnCards;
    }
}