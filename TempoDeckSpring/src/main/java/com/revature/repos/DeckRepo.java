package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.revature.models.Deck;

@Service
public interface DeckRepo extends JpaRepository<Deck, Integer> {

    List<Deck> findAll();

	Deck findByDeckId(Number deckId);
    
    

}