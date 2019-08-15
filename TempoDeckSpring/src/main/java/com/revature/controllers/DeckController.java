package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Deck;
import com.revature.services.DeckService;

@RestController
@RequestMapping("deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

     @GetMapping
     public List<Deck> findAll(){
         return deckService.findAll();
     }

   

     @GetMapping("/decks/{deck_id}")
     public Deck findByDeckId(@PathVariable Number deck_id){
         return deckService.findByDeckId(deck_id);
     }

    // /**
    //  * 
    //  * @param deckId
    //  */
    // @DeleteMapping("/{deckId}")
    // public ResponseEntity<Deck> delete(@PathVariable int deckId){
    //     Deck updateDeck = deckService.removeDeck(deckId);
    //     ResponseEntity<Deck> resp = new ResponseEntity<Deck>(updateDeck, HttpStatus.ACCEPTED);
    //     return resp;
    // }
}