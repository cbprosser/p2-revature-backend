package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Deck;

public interface DeckRepo extends JpaRepository<Deck, Integer> {

	List<Deck> findAll();

	Deck findById(int deckId);

	List<Deck> findAllByAuthorUserId(int deckAuthor);

	List<Deck> findAllDecksByFeaturedCard(String deckFeaturedCard);

	List<Deck> findAllByFormat_Format(String format);

	// Deck save(Deck deck); // Not needed?

}