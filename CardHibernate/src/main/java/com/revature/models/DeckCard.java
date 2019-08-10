package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "td_deck_cards")
public class DeckCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_cards_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private int deckID;

    @Column(name = "deck_card")
    private String card;

    @Column(name = "deck_card_amount")
    private int cardAmount;
}