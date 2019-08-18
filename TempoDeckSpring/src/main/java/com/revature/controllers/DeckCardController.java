package com.revature.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.dtos.CardDeckId;
import com.revature.dtos.DeckConvertedWithCards;
import com.revature.dtos.UserConverted;
import com.revature.models.Deck;
import com.revature.models.DeckCard;
import com.revature.models.User;
import com.revature.services.DeckCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deck/card")
public class DeckCardController {

    @Autowired
    private DeckCardService deckCardService;

    @GetMapping
    public List<DeckConvertedWithCards> findAll() {
        List<DeckCard> dbDeckCard = deckCardService.findAll();
        List<Deck> dbDecks = new ArrayList<>();
        List<DeckConvertedWithCards> decks = new ArrayList<>();
        List<CardDeckId> mainboardCards = new LinkedList<>();
        List<CardDeckId> sideboardCards = new LinkedList<>();
        dbDeckCard.forEach(card -> {
            if (!dbDecks.contains(card.getDeck())) {
                dbDecks.add(card.getDeck());
            }
            if (card.getCard().startsWith("SB:")) {
                String cardString = card.getCardAmount() + "x " + card.getCard().substring(3);
                sideboardCards.add(new CardDeckId(card.getDeck().getId(), cardString));
            } else {
                String cardString = card.getCardAmount() + "x " + card.getCard();
                mainboardCards.add(new CardDeckId(card.getDeck().getId(), cardString));
            }
        });

        dbDecks.forEach(deck -> {
            List<String> mainboard = new ArrayList<>();
            List<String> sideboard = new ArrayList<>();
            for (int i = 0; i < mainboardCards.size(); i++) {
                if (mainboardCards.get(i).getDeckId() == deck.getId()) {
                    mainboard.add(mainboardCards.remove(i).getCardString());
                    i--;
                }
            }
            for (int i = 0; i < sideboardCards.size(); i++) {
                if (sideboardCards.get(i).getDeckId() == deck.getId()) {
                    sideboard.add(sideboardCards.remove(i).getCardString());
                    i--;
                }
            }
            User author = deck.getAuthor();
            decks.add(new DeckConvertedWithCards(deck.getId(),
                    new UserConverted(author.getUserId(), author.getUsername(), author.getFirstName(),
                            author.getLastName(), author.getEmail(), author.getRole().getName()),
                    deck.getName(), deck.getDescription(), deck.isPrivate(), deck.isPrototype(),
                    mainboard.toArray(new String[mainboard.size()]), sideboard.toArray(new String[sideboard.size()]),
                    deck.getFormat().getFormat(), deck.getFeaturedCard()));
        });

        return decks;
    }

    @GetMapping("/{id}")
    public DeckConvertedWithCards findByDeckID(@PathVariable("id") int deckID) {
        List<DeckCard> dbDeckCard = deckCardService.findByDeckID(deckID);
        Deck dbDeck = null;
        DeckConvertedWithCards deck = null;
        List<String> mainboard = new ArrayList<>();
        List<String> sideboard = new ArrayList<>();
        for (DeckCard card : dbDeckCard) {
            if (dbDeck == null) {
                dbDeck = card.getDeck();
            }
            if (card.getCard().startsWith("SB:")) {
                String cardString = card.getCardAmount() + "x " + card.getCard().substring(3);
                sideboard.add(cardString);
            } else {
                String cardString = card.getCardAmount() + "x " + card.getCard();
                mainboard.add(cardString);
            }
        }

        User author = dbDeck.getAuthor();
        deck = new DeckConvertedWithCards(dbDeck.getId(),
                new UserConverted(author.getUserId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                        author.getEmail(), author.getRole().getName()),
                dbDeck.getName(), dbDeck.getDescription(), dbDeck.isPrivate(), dbDeck.isPrototype(),
                mainboard.toArray(new String[mainboard.size()]), sideboard.toArray(new String[sideboard.size()]),
                dbDeck.getFormat().getFormat(), dbDeck.getFeaturedCard());

        return deck;
    }
}