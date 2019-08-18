package com.revature.repos;

import java.util.List;

import com.revature.models.DeckCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DeckCardRepo extends JpaRepository<DeckCard, Integer> {

     List<DeckCard> findAll();

	List<DeckCard> findByDeckId(int Id);

}