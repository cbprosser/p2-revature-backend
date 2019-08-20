package com.revature.repos;

import java.util.List;

import com.revature.models.Deck;
import com.revature.models.DeckCard;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DeckCardRepo extends JpaRepository<DeckCard, Integer> {

     List<DeckCard> findAll(Sort sort);

	List<DeckCard> findByDeckId(int Id);

	DeckCard getDeckCardByDeckAndCard(Deck deck, String card);

	List<DeckCard> findByDeckIdOrderByCardDesc(int deckID);

	List<DeckCard> findByDeckIdOrderByCardAsc(int deckID);
	
}