package com.revature.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private int id;

    @Column(name = "collection_author")
    private int author;

    @Column(name = "collection_private")
    private boolean collPrivate;

    @Column(name = "collection_prototype")
    private boolean prototype;

    @Column(name = "collection_creation_date")
    private LocalDate CreationDate;

    @Column(name = "collection_last_updated")
    private LocalDate lastUpdated;

    @Column(name = "collection_name")
    private String name;

    @Column(name = "collection_description")
    private String description;

    @Column(name = "collection_featured_card")
    private String featuredCard;

    public Collection() {
    }

    public Collection(int id, int author, boolean collPrivate, boolean prototype, LocalDate creationDate,
            LocalDate lastUpdated, String name, String description, String featuredCard) {
        this.id = id;
        this.author = author;
        this.collPrivate = collPrivate;
        this.prototype = prototype;
        CreationDate = creationDate;
        this.lastUpdated = lastUpdated;
        this.name = name;
        this.description = description;
        this.featuredCard = featuredCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public boolean isCollPrivate() {
        return collPrivate;
    }

    public void setCollPrivate(boolean collPrivate) {
        this.collPrivate = collPrivate;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public LocalDate getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        CreationDate = creationDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        result = prime * result + ((CreationDate == null) ? 0 : CreationDate.hashCode());
        result = prime * result + author;
        result = prime * result + (collPrivate ? 1231 : 1237);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((featuredCard == null) ? 0 : featuredCard.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (prototype ? 1231 : 1237);
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
        Collection other = (Collection) obj;
        if (CreationDate == null) {
            if (other.CreationDate != null)
                return false;
        } else if (!CreationDate.equals(other.CreationDate))
            return false;
        if (author != other.author)
            return false;
        if (collPrivate != other.collPrivate)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (featuredCard == null) {
            if (other.featuredCard != null)
                return false;
        } else if (!featuredCard.equals(other.featuredCard))
            return false;
        if (id != other.id)
            return false;
        if (lastUpdated == null) {
            if (other.lastUpdated != null)
                return false;
        } else if (!lastUpdated.equals(other.lastUpdated))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (prototype != other.prototype)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CollectionId [CreationDate=" + CreationDate + ", author=" + author + ", collPrivate=" + collPrivate
                + ", description=" + description + ", featuredCard=" + featuredCard + ", id=" + id + ", lastUpdated="
                + lastUpdated + ", name=" + name + ", prototype=" + prototype + "]";
    }

}