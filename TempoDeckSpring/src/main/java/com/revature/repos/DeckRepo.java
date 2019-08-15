package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Deck;

public interface DeckRepo extends JpaRepository<Deck, Integer> {

	
     List<Deck> findAll();

     Deck findById(Number deck_id);
    
    

}