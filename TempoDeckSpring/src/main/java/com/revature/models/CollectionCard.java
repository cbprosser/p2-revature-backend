package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "td_collection_cards")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CollectionCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_cards_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Column(name = "collection_card")
    private String card;

    @Column(name = "collection_card_amount")
    private int amount;

    public CollectionCard() {
    }

    public CollectionCard(int id, Collection collection, String card, int amount) {
        this.id = id;
        this.collection = collection;
        this.card = card;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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
        result = prime * result + ((card == null) ? 0 : card.hashCode());
        result = prime * result + ((collection == null) ? 0 : collection.hashCode());
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
        CollectionCard other = (CollectionCard) obj;
        if (amount != other.amount)
            return false;
        if (card == null) {
            if (other.card != null)
                return false;
        } else if (!card.equals(other.card))
            return false;
        if (collection == null) {
            if (other.collection != null)
                return false;
        } else if (!collection.equals(other.collection))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CollectionCard [amount=" + amount + ", card=" + card + ", collection=" + collection + ", id=" + id
                + "]";
    }

}