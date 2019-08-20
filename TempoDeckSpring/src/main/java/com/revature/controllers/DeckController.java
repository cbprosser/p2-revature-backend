package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.dtos.DeckConvertedNoCards;
import com.revature.dtos.UserConverted;
import com.revature.models.Deck;
import com.revature.models.User;
import com.revature.services.DeckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping("/old")
    public List<Deck> findAllOld() {
        return deckService.findAll();
    }

    @GetMapping
    public List<DeckConvertedNoCards> findAll() {
        List<Deck> dbDecks = deckService.findAll();
        List<DeckConvertedNoCards> decks = new ArrayList<>();
        dbDecks.forEach(deck -> {
            User author = deck.getAuthor();
            decks.add(new DeckConvertedNoCards(
                deck.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                deck.getName(),
                deck.getDescription(),
                deck.isPrivate(),
                deck.isPrototype(),
                deck.getFormat(),
                deck.getFeaturedCard()
            ));
        });
        return decks;
    }

    @GetMapping("/{deck_id}")
    public DeckConvertedNoCards findByDeckId(@PathVariable("deck_id") int deckId) {
        Deck dbDeck = deckService.findByDeckId(deckId);
        User author = dbDeck.getAuthor();
        return new DeckConvertedNoCards(
            dbDeck.getId(), new UserConverted(
                author.getId(), 
                author.getUsername(), 
                author.getFirstName(), 
                author.getLastName(), 
                author.getEmail(), 
                author.getRole()), 
                dbDeck.getName(), 
                dbDeck.getDescription(), 
                dbDeck.isPrivate(), 
                dbDeck.isPrototype(), 
                dbDeck.getFormat(), 
                dbDeck.getFeaturedCard());
    }

    @GetMapping("/author/{deck_author}")
    public List<DeckConvertedNoCards> findAllDecksByAuthor(@PathVariable("deck_author") int deckAuthor) {
        List<Deck> dbDecks = deckService.findAllDecksByAuthor(deckAuthor);
        List<DeckConvertedNoCards> decks = new ArrayList<>();
        dbDecks.forEach(deck -> {
            User author = deck.getAuthor();
            decks.add(new DeckConvertedNoCards(
                deck.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                deck.getName(),
                deck.getDescription(),
                deck.isPrivate(),
                deck.isPrototype(),
                deck.getFormat(),
                deck.getFeaturedCard()
            ));
        });
        return decks;
    }

    @GetMapping("/featuredCard/{deck_featured_card}")
    public List<DeckConvertedNoCards> findAllDecksByFeaturedCard(@PathVariable("deck_featured_card") String featuredCard) {
        List<Deck> dbDecks = deckService.findAllDecksByFeaturedCard(featuredCard);
        List<DeckConvertedNoCards> decks = new ArrayList<>();
        dbDecks.forEach(deck -> {
            User author = deck.getAuthor();
            decks.add(new DeckConvertedNoCards(
                deck.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                deck.getName(),
                deck.getDescription(),
                deck.isPrivate(),
                deck.isPrototype(),
                deck.getFormat(),
                deck.getFeaturedCard()
            ));
        });
        return decks;
    }

    @GetMapping("/format/{deck_format}")
    public List<DeckConvertedNoCards> findAllDecksByFormat(@PathVariable("deck_format") String format) {
        List<Deck> dbDecks = deckService.findAllDecksByFormat(format);
        List<DeckConvertedNoCards> decks = new ArrayList<>();
        dbDecks.forEach(deck -> {
            User author = deck.getAuthor();
            decks.add(new DeckConvertedNoCards(
                deck.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                deck.getName(),
                deck.getDescription(),
                deck.isPrivate(),
                deck.isPrototype(),
                deck.getFormat(),
                deck.getFeaturedCard()
            ));
        });
        return decks;
    }

    @PutMapping
    public DeckConvertedNoCards updateDeck(@RequestBody DeckConvertedNoCards deck) {
        Deck dbDeck = deckService.updateDeck(deck);
        User author = dbDeck.getAuthor();
        return new DeckConvertedNoCards(
            dbDeck.getId(), new UserConverted(
                author.getId(), 
                author.getUsername(), 
                author.getFirstName(), 
                author.getLastName(), 
                author.getEmail(), 
                author.getRole()), 
                dbDeck.getName(), 
                dbDeck.getDescription(), 
                dbDeck.isPrivate(), 
                dbDeck.isPrototype(), 
                dbDeck.getFormat(), 
                dbDeck.getFeaturedCard());
    }

    @PutMapping("/old")
    public Deck updateDeckOld(@RequestBody Deck deck) {
        return deckService.updateDeck(deck);
    }
}