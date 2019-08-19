package com.revature.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.dtos.CardCollectionId;
import com.revature.dtos.CollectionConvertedWithCards;
import com.revature.dtos.UserConverted;
import com.revature.models.Collection;
import com.revature.models.CollectionCard;
import com.revature.models.User;
import com.revature.services.CollectionCardService;
import com.revature.services.CollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collection/card")
public class CollectionCardController {

    @Autowired
    private CollectionCardService collectionCardService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/old")
    public List<CollectionCard> findAll() {
        return collectionCardService.findAll();
    }

    @GetMapping
    public List<CollectionConvertedWithCards> findAllConverted() {
        List<CollectionCard> dbCollectionCard = collectionCardService.findAll();
        List<Collection> dbCollections = new ArrayList<>();
        List<CollectionConvertedWithCards> collections = new ArrayList<>();
        List<CardCollectionId> mainboardCards = new LinkedList<>();
        List<CardCollectionId> sideboardCards = new LinkedList<>();
        dbCollectionCard.forEach(card -> {
            if (!dbCollections.contains(card.getCollection())) {
                dbCollections.add(card.getCollection());
            }
            String cardString = card.getAmount() + "x " + card.getCard();
            mainboardCards.add(new CardCollectionId(card.getCollection().getId(), cardString));
        });

        dbCollections.forEach(deck -> {
            List<String> mainboard = new ArrayList<>();
            List<String> sideboard = new ArrayList<>();
            for (int i = 0; i < mainboardCards.size(); i++) {
                if (mainboardCards.get(i).getCollectionId() == deck.getId()) {
                    mainboard.add(mainboardCards.remove(i).getCardString());
                    i--;
                }
            }
            for (int i = 0; i < sideboardCards.size(); i++) {
                if (sideboardCards.get(i).getCollectionId() == deck.getId()) {
                    sideboard.add(sideboardCards.remove(i).getCardString());
                    i--;
                }
            }
            User author = deck.getAuthor();
            collections.add(new CollectionConvertedWithCards(deck.getId(),
                    new UserConverted(author.getId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                            author.getEmail(), author.getRole()),
                    deck.getName(), deck.getDescription(), deck.isPrivate(), deck.isPrototype(),
                    mainboard.toArray(new String[mainboard.size()]), deck.getFeaturedCard()));
        });

        return collections;
    }

    @GetMapping("/old/{id}")
    public List<CollectionCard> findByDeckIDOld(@PathVariable("id") int collectionID) {
        return collectionCardService.findByCollectionID(collectionID);
    }

    @GetMapping("/{id}")
    public CollectionConvertedWithCards findByDeckID(@PathVariable("id") int collectionID) {
        List<CollectionCard> dbCards = collectionCardService.findByCollectionID(collectionID);
        return convertToDTO(dbCards);
    }

    private CollectionConvertedWithCards convertToDTO(List<CollectionCard> dbCollectionCard) {
        Collection dbCollection = null;
        CollectionConvertedWithCards collection = null;
        List<String> cards = new ArrayList<>();
        for (CollectionCard card : dbCollectionCard) {
            if (dbCollection == null) {
                dbCollection = card.getCollection();
            }
             String cardString = card.getAmount() + "x " + card.getCard();
                cards.add(cardString);
            
        }

        User author = dbCollection.getAuthor();
        collection = new CollectionConvertedWithCards(
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
                dbCollection.isPrivate(), 
                dbCollection.isPrototype(),
                cards.toArray(new String[cards.size()]),
                dbCollection.getFeaturedCard());

        return collection;
    }

    @PostMapping("/old")
    public List<CollectionCard> createDeck(@RequestBody List<CollectionCard> collection) {
        if (collection.size() > 0) {
            Collection newCollection = collectionService.save(collection.get(0).getCollection());
            collection.forEach(card -> {
                card.setCollection(newCollection);
            });
            return collectionCardService.createCollection(collection);
        }
        return null;
    }

    @PostMapping
    public CollectionConvertedWithCards createDeck(@RequestBody CollectionConvertedWithCards newCollection) {
        Collection newDBCollection = new Collection(
            newCollection.getId(), 
            new User(
                newCollection.getAuthor().getId(), 
                newCollection.getAuthor().getUsername(), 
                "", 
                newCollection.getAuthor().getFirstName(), 
                newCollection.getAuthor().getLastName(), 
                newCollection.getAuthor().getEmail(), 
                newCollection.getAuthor().getRole()), 
            newCollection.isPrivate(), 
            newCollection.isPrototype(), 
            null, 
            null, 
            newCollection.getCollectionName(), 
            newCollection.getCollectionDescription(), 
            newCollection.getFeaturedCard());

        List<CollectionCard> newDBCollectionCard = new ArrayList<>();
        for (String cardString : newCollection.getCards()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = cardString.substring(cardString.indexOf("x ") + 2);
                newDBCollectionCard.add(new CollectionCard(0, newDBCollection, card, cardAmount));
            }
        }

        List<CollectionCard> createdDBDeck = createDeck(newDBCollectionCard);

        return convertToDTO(createdDBDeck);
    }


}