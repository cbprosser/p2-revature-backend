package com.revature.driver;

import java.util.List;

import com.revature.dao.DeckDAO;
import com.revature.models.DeckCard;

public class Driver {
	private static DeckDAO deckDAO = new DeckDAO();

	public static void main(String[] args) {
		List<DeckCard> cards = deckDAO.findById(1);

		System.out.println(cards);
	}
}
