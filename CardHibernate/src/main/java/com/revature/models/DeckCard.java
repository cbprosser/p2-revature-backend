package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    private Deck deck;

    @Column(name = "deck_card")
    private String card;

    @Column(name = "deck_card_amount")
    private int cardAmount;

    public DeckCard() {
    }

    public DeckCard(int id, Deck deck, String card, int cardAmount) {
        this.id = id;
        this.deck = deck;
        this.card = card;
        this.cardAmount = cardAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((card == null) ? 0 : card.hashCode());
        result = prime * result + cardAmount;
        result = prime * result + ((deck == null) ? 0 : deck.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeckCard other = (DeckCard) obj;
        if (card == null) {
            if (other.card != null)
                return false;
        } else if (!card.equals(other.card))
            return false;
        if (cardAmount != other.cardAmount)
            return false;
        if (deck == null) {
            if (other.deck != null)
                return false;
        } else if (!deck.equals(other.deck))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeckCard [card=" + card + ", cardAmount=" + cardAmount + ", deck=" + deck + ", id=" + id + "]";
    }

}