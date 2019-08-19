package com.revature.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "td_collection")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "collection_author")
    private User author;

    @Column(name = "collection_private")
    private boolean isPrivate;

    @Column(name = "collection_prototype")
    private boolean isPrototype;

    @Transient
    @Column(name = "collection_creation_date")
    private LocalDate creationDate;

    @Transient
    @Column(name = "collection_last_updated")
    private LocalDate lastUpdatedDate;

    @Column(name = "collection_name")
    private String name;

    @Column(name = "collection_description")
    private String description;

    @Column(name = "collection_featured_card")
    private String featuredCard;

    public Collection() {
    }

    public Collection(int id, User author, boolean isPrivate, boolean isPrototype, LocalDate creationDate,
            LocalDate lastUpdatedDate, String name, String description, String featuredCard) {
        this.id = id;
        this.author = author;
        this.isPrivate = isPrivate;
        this.isPrototype = isPrototype;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((featuredCard == null) ? 0 : featuredCard.hashCode());
        result = prime * result + id;
        result = prime * result + (isPrivate ? 1231 : 1237);
        result = prime * result + (isPrototype ? 1231 : 1237);
        result = prime * result + ((lastUpdatedDate == null) ? 0 : lastUpdatedDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
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
        if (isPrivate != other.isPrivate)
            return false;
        if (isPrototype != other.isPrototype)
            return false;
        if (lastUpdatedDate == null) {
            if (other.lastUpdatedDate != null)
                return false;
        } else if (!lastUpdatedDate.equals(other.lastUpdatedDate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Collection [author=" + author + ", creationDate=" + creationDate + ", description=" + description
                + ", featuredCard=" + featuredCard + ", id=" + id + ", isPrivate=" + isPrivate + ", isPrototype="
                + isPrototype + ", lastUpdatedDate=" + lastUpdatedDate + ", name=" + name + "]";
    }

}