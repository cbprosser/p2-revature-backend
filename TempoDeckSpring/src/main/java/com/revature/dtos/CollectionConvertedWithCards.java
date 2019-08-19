package com.revature.dtos;

import java.util.Arrays;

public class CollectionConvertedWithCards {

    private int id;
    private UserConverted author;
    private String collectionName;
    private String collectionDescription;
    private boolean isPrivate;
    private boolean isPrototype;
    private String[] cards;
    private String featuredCard;

    public CollectionConvertedWithCards() {
    }

    public CollectionConvertedWithCards(int id, UserConverted author, String collectionName,
            String collectionDescription, boolean isPrivate, boolean isPrototype, String[] cards, String featuredCard) {
        this.id = id;
        this.author = author;
        this.collectionName = collectionName;
        this.collectionDescription = collectionDescription;
        this.isPrivate = isPrivate;
        this.isPrototype = isPrototype;
        this.cards = cards;
        this.featuredCard = featuredCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserConverted getAuthor() {
        return author;
    }

    public void setAuthor(UserConverted author) {
        this.author = author;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public boolean isPrototype() {
        return isPrototype;
    }

    public void setPrototype(boolean isPrototype) {
        this.isPrototype = isPrototype;
    }

    public String[] getCards() {
        return cards;
    }

    public void setCards(String[] cards) {
        this.cards = cards;
    }

    public String getFeaturedCard() {
        return featuredCard;
    }

    public void setFeaturedCard(String featuredCard) {
        this.featuredCard = featuredCard;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + Arrays.hashCode(cards);
        result = prime * result + ((collectionDescription == null) ? 0 : collectionDescription.hashCode());
        result = prime * result + ((collectionName == null) ? 0 : collectionName.hashCode());
        result = prime * result + ((featuredCard == null) ? 0 : featuredCard.hashCode());
        result = prime * result + id;
        result = prime * result + (isPrivate ? 1231 : 1237);
        result = prime * result + (isPrototype ? 1231 : 1237);
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
        CollectionConvertedWithCards other = (CollectionConvertedWithCards) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (!Arrays.equals(cards, other.cards))
            return false;
        if (collectionDescription == null) {
            if (other.collectionDescription != null)
                return false;
        } else if (!collectionDescription.equals(other.collectionDescription))
            return false;
        if (collectionName == null) {
            if (other.collectionName != null)
                return false;
        } else if (!collectionName.equals(other.collectionName))
            return false;
        if (featuredCard == null) {
            if (other.featuredCard != null)
                return false;
        } else if (!featuredCard.equals(other.featuredCard))
            return false;
        if (id != other.id)
            return false;
        if (isPrivate != other.isPrivate)
            return false;
        if (isPrototype != other.isPrototype)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CollectionConvertedWithCards [author=" + author + ", cards=" + Arrays.toString(cards)
                + ", collectionDescription=" + collectionDescription + ", collectionName=" + collectionName
                + ", featuredCard=" + featuredCard + ", id=" + id + ", isPrivate=" + isPrivate + ", isPrototype="
                + isPrototype + "]";
    }
}