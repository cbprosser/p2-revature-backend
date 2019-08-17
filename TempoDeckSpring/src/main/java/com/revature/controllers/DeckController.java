package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

   

     @GetMapping("/{deck_id}")
     public Deck findByDeckId(@PathVariable("deck_id") int deckId){
         return deckService.findByDeckId(deckId);
     }

     @GetMapping("/author/{deck_author}")
     public List<Deck> findAllDecksByAuthor(@PathVariable("deck_author") int deckAuthor) {
         return deckService.findAllDecksByAuthor(deckAuthor);
     }
     
     @GetMapping("/featuredCard/{deck_featured_card}")
     public List<Deck> findAllDecksByFeaturedCard(@PathVariable("deck_featured_card") String featuredCard) {
         return deckService.findAllDecksByFeaturedCard(featuredCard);
     }

     @GetMapping("/format/{deck_format}")
     public List<Deck> findAllDecksByFormat(@PathVariable("deck_format") String format) {
         return deckService.findAllDecksByFormat(format);
     }

    @PostMapping
	@ResponseBody
	public Deck save(@RequestBody Deck deck) {
		return deckService.save(deck);
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