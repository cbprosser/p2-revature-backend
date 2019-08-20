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
import com.revature.services.DeckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deck/card")
public class DeckCardController {

    @Autowired
    private DeckCardService deckCardService;

    @Autowired
    private DeckService deckService;

    @GetMapping("/old")
    public List<DeckCard> findAllOld() {
        return deckCardService.findAll();
    }

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
            if(card.getCardAmount() != 0) {
                if (card.getCard().startsWith("SB:")) {
                    String cardString = card.getCardAmount() + "x " + card.getCard().substring(3);
                    sideboardCards.add(new CardDeckId(card.getDeck().getId(), cardString));
                } else {
                    String cardString = card.getCardAmount() + "x " + card.getCard();
                    mainboardCards.add(new CardDeckId(card.getDeck().getId(), cardString));
                }
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
                    new UserConverted(author.getId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                            author.getEmail(), author.getRole()),
                    deck.getName(), deck.getDescription(), deck.getIsPrivate(), deck.getIsPrototype(),
                    mainboard.toArray(new String[mainboard.size()]), sideboard.toArray(new String[sideboard.size()]),
                    deck.getFormat(), deck.getFeaturedCard()));
        });

        return decks;
    }

    @GetMapping("/old/{id}")
    public List<DeckCard> findByDeckIDOld(@PathVariable("id") int deckID) {
        return deckCardService.findByDeckID(deckID);
    }

    @GetMapping("/{id}")
    public DeckConvertedWithCards findByDeckID(@PathVariable("id") int deckID) {
        List<DeckCard> dbDeckCard = deckCardService.findByDeckID(deckID);
        return convertToDTO(dbDeckCard);
    }

    @PostMapping("/old")
    public List<DeckCard> createDeck(@RequestBody List<DeckCard> reqDeck) {
        List<DeckCard> deck = new ArrayList<>();
        deck.addAll(reqDeck);
        if (deck.size() > 0) {
            Deck newDeck = deckService.save(deck.get(0).getDeck());
            deck.forEach(card -> {
                card.setDeck(newDeck);
            });
            return deckCardService.createDeck(deck);
        }
        return null;
    }

    @PostMapping
    public DeckConvertedWithCards createDeck(@RequestBody DeckConvertedWithCards newDeck) {
        Deck newDBDeck = new Deck(newDeck.getId(),
                new User(newDeck.getAuthor().getId(), newDeck.getAuthor().getUsername(), "",
                        newDeck.getAuthor().getFirstName(), newDeck.getAuthor().getLastName(),
                        newDeck.getAuthor().getEmail(), newDeck.getAuthor().getRole()),
                newDeck.getDeckName(), newDeck.getDeckDescription(), newDeck.getIsPrivate(), newDeck.getIsPrototype(), null,
                null, newDeck.getFormat(), newDeck.getFeaturedCard());

        List<DeckCard> newDBDeckCard = new ArrayList<>();
        for (String cardString : newDeck.getMainboard()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = cardString.substring(cardString.indexOf("x ") + 2);
                newDBDeckCard.add(new DeckCard(0, newDBDeck, card, cardAmount));
            }
        }

        for (String cardString : newDeck.getSideboard()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = "SB:" + cardString.substring(cardString.indexOf("x ") + 2);
                newDBDeckCard.add(new DeckCard(0, newDBDeck, card, cardAmount));
            }
        }

        List<DeckCard> createdDBDeck = createDeck(newDBDeckCard);

        return convertToDTO(createdDBDeck);
    }

    @PutMapping("/old")
    public List<DeckCard> updateDeck(@RequestBody List<DeckCard> reqDeck) {
        List<DeckCard> deck = new ArrayList<>();
        deck.addAll(reqDeck);
        if (deck.size() > 0) {
            Deck updateDeck = deckService.save(deck.get(0).getDeck());
            deck.forEach(card -> {
                card.setDeck(updateDeck);
            });
            return deckCardService.updateDeck(deck, deck.get(0).getDeck().getId());
        }
        return null;
    }

    @PutMapping
    public DeckConvertedWithCards updateDeck(@RequestBody DeckConvertedWithCards reqDeck) {
        Deck dbDeckToUpdate = new Deck(
            reqDeck.getId(),
            new User(
                reqDeck.getAuthor().getId(), 
                reqDeck.getAuthor().getUsername(), 
                null,
                reqDeck.getAuthor().getFirstName(), 
                reqDeck.getAuthor().getLastName(),
                reqDeck.getAuthor().getEmail(), 
                reqDeck.getAuthor().getRole()),
            reqDeck.getDeckName(), 
            reqDeck.getDeckDescription(), 
            reqDeck.getIsPrivate(), 
            reqDeck.getIsPrototype(), 
            null,    
            null, 
            reqDeck.getFormat(), 
            reqDeck.getFeaturedCard());

        List<DeckCard> dbDeckCardUpdated = new ArrayList<>();
        for (String cardString : reqDeck.getMainboard()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = cardString.substring(cardString.indexOf("x ") + 2);
                dbDeckCardUpdated.add(new DeckCard(0, dbDeckToUpdate, card, cardAmount));
            }
        }

        for (String cardString : reqDeck.getSideboard()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = "SB:" + cardString.substring(cardString.indexOf("x ") + 2);
                dbDeckCardUpdated.add(new DeckCard(0, dbDeckToUpdate, card, cardAmount));
            }
        }

        System.out.println(dbDeckCardUpdated);

        List<DeckCard> createdDBDeck = updateDeck(dbDeckCardUpdated);

        System.out.println(createdDBDeck);

        return convertToDTO(createdDBDeck);
    }


    private DeckConvertedWithCards convertToDTO(List<DeckCard> dbDeckCard) {
        Deck dbDeck = null;
        DeckConvertedWithCards deck = null;
        List<String> mainboard = new ArrayList<>();
        List<String> sideboard = new ArrayList<>();
        for (DeckCard card : dbDeckCard) {
            if (dbDeck == null) {
                dbDeck = card.getDeck();
            }
            if(card.getCardAmount() == 0) {
                continue;
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
        if(author != null) {
            deck = new DeckConvertedWithCards(dbDeck.getId(),
                new UserConverted(author.getId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                        author.getEmail(), author.getRole()),
                dbDeck.getName(), dbDeck.getDescription(), dbDeck.getIsPrivate(), dbDeck.getIsPrototype(),
                mainboard.toArray(new String[mainboard.size()]), sideboard.toArray(new String[sideboard.size()]),
                dbDeck.getFormat(), dbDeck.getFeaturedCard());
        } else {
            deck = new DeckConvertedWithCards(dbDeck.getId(),
                null,
                dbDeck.getName(), dbDeck.getDescription(), dbDeck.getIsPrivate(), dbDeck.getIsPrototype(),
                mainboard.toArray(new String[mainboard.size()]), sideboard.toArray(new String[sideboard.size()]),
                dbDeck.getFormat(), dbDeck.getFeaturedCard());
        }
        
        return deck;
    }
}