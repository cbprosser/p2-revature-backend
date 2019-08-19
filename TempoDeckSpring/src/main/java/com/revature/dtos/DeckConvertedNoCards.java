package com.revature.dtos;

import com.revature.models.Format;

public class DeckConvertedNoCards {

    int id;
    UserConverted author;
    String deckName;
    String deckDescription;
    boolean isPrivate;
    boolean isPrototype;
    Format format;
    String featuredCard;

    public DeckConvertedNoCards() {
    }

    public DeckConvertedNoCards(int id, UserConverted author, String deckName, String deckDescription,
            boolean isPrivate, boolean isPrototype, Format format, String featuredCard) {
        this.id = id;
        this.author = author;
        this.deckName = deckName;
        this.deckDescription = deckDescription;
        this.isPrivate = isPrivate;
        this.isPrototype = isPrototype;
        this.format = format;
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

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getDeckDescription() {
        return deckDescription;
    }

    public void setDeckDescription(String deckDescription) {
        this.deckDescription = deckDescription;
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

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
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
        result = prime * result + ((deckDescription == null) ? 0 : deckDescription.hashCode());
        result = prime * result + ((deckName == null) ? 0 : deckName.hashCode());
        result = prime * result + ((featuredCard == null) ? 0 : featuredCard.hashCode());
        result = prime * result + ((format == null) ? 0 : format.hashCode());
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
        DeckConvertedNoCards other = (DeckConvertedNoCards) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (deckDescription == null) {
            if (other.deckDescription != null)
                return false;
        } else if (!deckDescription.equals(other.deckDescription))
            return false;
        if (deckName == null) {
            if (other.deckName != null)
                return false;
        } else if (!deckName.equals(other.deckName))
            return false;
        if (featuredCard == null) {
            if (other.featuredCard != null)
                return false;
        } else if (!featuredCard.equals(other.featuredCard))
            return false;
        if (format == null) {
            if (other.format != null)
                return false;
        } else if (!format.equals(other.format))
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
        return "DeckConvertedNoCards [author=" + author + ", deckDescription=" + deckDescription + ", deckName="
                + deckName + ", featuredCard=" + featuredCard + ", format=" + format + ", id=" + id + ", isPrivate="
                + isPrivate + ", isPrototype=" + isPrototype + "]";
    }
}