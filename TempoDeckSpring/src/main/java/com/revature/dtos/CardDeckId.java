package com.revature.dtos;


public class CardDeckId {

    int deckId;
    String cardString;

    public CardDeckId() {
    }

    public CardDeckId(int deckId, String cardString) {
        this.deckId = deckId;
        this.cardString = cardString;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getCardString() {
        return cardString;
    }

    public void setCardString(String cardString) {
        this.cardString = cardString;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cardString == null) ? 0 : cardString.hashCode());
        result = prime * result + deckId;
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
        CardDeckId other = (CardDeckId) obj;
        if (cardString == null) {
            if (other.cardString != null)
                return false;
        } else if (!cardString.equals(other.cardString))
            return false;
        if (deckId != other.deckId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CardDeckId [cardString=" + cardString + ", deckId=" + deckId + "]";
    }
    
}