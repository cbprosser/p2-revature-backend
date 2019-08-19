package com.revature.dtos;


public class CardCollectionId {

    int collectionId;
    String cardString;

    public CardCollectionId() {
    }

    public CardCollectionId(int collectionId, String cardString) {
        this.collectionId = collectionId;
        this.cardString = cardString;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
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
        result = prime * result + collectionId;
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
        CardCollectionId other = (CardCollectionId) obj;
        if (cardString == null) {
            if (other.cardString != null)
                return false;
        } else if (!cardString.equals(other.cardString))
            return false;
        if (collectionId != other.collectionId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CardCollectionId [cardString=" + cardString + ", collectionId=" + collectionId + "]";
    }
}