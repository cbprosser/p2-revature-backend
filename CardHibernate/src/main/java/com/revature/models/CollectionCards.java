package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollectionCards {

    @Column(name = "collection_cards_id")
    private String collectionCardsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection _id")
    private int collectionId;
    
    @Column(name = "collection_card")
    private String collectionCard;

    @Column(name = "amount")
    private int amount;

    public CollectionCards(String collectionCardsId, int collectionId, String collectionCard, int amount) {
        this.collectionCardsId = collectionCardsId;
        this.collectionId = collectionId;
        this.collectionCard = collectionCard;
        this.amount = amount;
    }

    public String getCollectionCardsId() {
        return collectionCardsId;
    }

    public void setCollectionCardsId(String collectionCardsId) {
        this.collectionCardsId = collectionCardsId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionCard() {
        return collectionCard;
    }

    public void setCollectionCard(String collectionCard) {
        this.collectionCard = collectionCard;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((collectionCard == null) ? 0 : collectionCard.hashCode());
        result = prime * result + ((collectionCardsId == null) ? 0 : collectionCardsId.hashCode());
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
        CollectionCards other = (CollectionCards) obj;
        if (amount != other.amount)
            return false;
        if (collectionCard == null) {
            if (other.collectionCard != null)
                return false;
        } else if (!collectionCard.equals(other.collectionCard))
            return false;
        if (collectionCardsId == null) {
            if (other.collectionCardsId != null)
                return false;
        } else if (!collectionCardsId.equals(other.collectionCardsId))
            return false;
        if (collectionId != other.collectionId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CollectionCards [amount=" + amount + ", collectionCard=" + collectionCard + ", collectionCardsId="
                + collectionCardsId + ", collectionId=" + collectionId + "]";
    }

}