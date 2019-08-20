package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.dtos.CollectionConvertedNoCards;
import com.revature.dtos.UserConverted;
import com.revature.models.Collection;
import com.revature.models.User;
import com.revature.services.CollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/old")
    public List<Collection> findAll() {
        return collectionService.findAll();
    }

    @GetMapping
    public List<CollectionConvertedNoCards> findAllConverted() {
        List<Collection> dbCollections = collectionService.findAll();
        List<CollectionConvertedNoCards> decks = new ArrayList<>();
        dbCollections.forEach(collection -> {
            User author = collection.getAuthor();
            decks.add(new CollectionConvertedNoCards(
                collection.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                collection.getName(),
                collection.getDescription(),
                collection.getIsPrivate(),
                collection.getIsPrototype(),
                collection.getFeaturedCard()
            ));
        });
        return decks;
    }

    @GetMapping("/old/{collectionId}")
    public Collection findByCollectionId(@PathVariable("collectionId") int collectionID) {
        return collectionService.findByCollectionId(collectionID);
    }
    @GetMapping("/{collectionId}")
    public CollectionConvertedNoCards findByCollectionIdCOnverted(@PathVariable("collectionId") int collectionID) {
        Collection dbCollection = collectionService.findByCollectionId(collectionID);
        User author = dbCollection.getAuthor();
        return new CollectionConvertedNoCards(
            dbCollection.getId(), 
            new UserConverted(
                author.getId(), 
                author.getUsername(), 
                author.getFirstName(), 
                author.getLastName(), 
                author.getEmail(), 
                author.getRole()), 
            dbCollection.getName(), 
            dbCollection.getDescription(), 
            dbCollection.getIsPrivate(), 
            dbCollection.getIsPrototype(),
            dbCollection.getFeaturedCard());
    }

    @GetMapping("/old/author/{authorId}")
    public List<Collection> findAllByAuthor(@PathVariable("authorId") int authorID) {
        return collectionService.findByAuthorId(authorID);
    }

    @GetMapping("/author/{authorId}")
    public List<CollectionConvertedNoCards> findAllByAuthorConverted(@PathVariable("authorId") int authorID) {
        List<Collection> dbCollections = collectionService.findByAuthorId(authorID);
        List<CollectionConvertedNoCards> collections = new ArrayList<>();
        dbCollections.forEach(collection -> {
            User author = collection.getAuthor();
            collections.add(new CollectionConvertedNoCards(
                collection.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                collection.getName(),
                collection.getDescription(),
                collection.getIsPrivate(),
                collection.getIsPrototype(),
                collection.getFeaturedCard()
            ));
        });
        return collections;
    }

    @GetMapping("/old/featuredCard/{featuredCard}")
    public List<Collection> findAllByFeaturedCard(@PathVariable("featuredCard") String featuredCard) {
        return collectionService.findByFeaturedCard(featuredCard);
    }

    @GetMapping("/featuredCard/{featuredCard}")
    public List<CollectionConvertedNoCards> findAllByFeaturedCardConverted(@PathVariable("featuredCard") String featuredCard) {
        List<Collection> dbCollections = collectionService.findByFeaturedCard(featuredCard);
        List<CollectionConvertedNoCards> collections = new ArrayList<>();
        dbCollections.forEach(collection -> {
            User author = collection.getAuthor();
            collections.add(new CollectionConvertedNoCards(
                collection.getId(),
                new UserConverted(
                    author.getId(),
                    author.getUsername(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getEmail(),
                    author.getRole()
                ),
                collection.getName(),
                collection.getDescription(),
                collection.getIsPrivate(),
                collection.getIsPrototype(),
                collection.getFeaturedCard()
            ));
        });
        return collections;
    }
    
    @PutMapping
    public CollectionConvertedNoCards updateCollection(@RequestBody CollectionConvertedNoCards collection) {
        Collection dbCollection = collectionService.updateCollection(collection);
        User author = dbCollection.getAuthor();
        return new CollectionConvertedNoCards(
            dbCollection.getId(), new UserConverted(
                author.getId(), 
                author.getUsername(), 
                author.getFirstName(), 
                author.getLastName(), 
                author.getEmail(), 
                author.getRole()), 
                dbCollection.getName(), 
                dbCollection.getDescription(), 
                dbCollection.getIsPrivate(), 
                dbCollection.getIsPrototype(),
                dbCollection.getFeaturedCard());
    }

    @PutMapping("/old")
    public Collection updateDeckOld(@RequestBody Collection collection) {
        return collectionService.updateCollection(collection);
    }
}