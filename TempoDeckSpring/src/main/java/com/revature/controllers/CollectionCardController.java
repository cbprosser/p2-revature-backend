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
import org.springframework.web.bind.annotation.PutMapping;
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
            if (card.getAmount() != 0) {
                String cardString = card.getAmount() + "x " + card.getCard();
                mainboardCards.add(new CardCollectionId(card.getCollection().getId(), cardString));
            }
        });

        dbCollections.forEach(collection -> {
            List<String> mainboard = new ArrayList<>();
            List<String> sideboard = new ArrayList<>();
            for (int i = 0; i < mainboardCards.size(); i++) {
                if (mainboardCards.get(i).getCollectionId() == collection.getId()) {
                    mainboard.add(mainboardCards.remove(i).getCardString());
                    i--;
                }
            }
            for (int i = 0; i < sideboardCards.size(); i++) {
                if (sideboardCards.get(i).getCollectionId() == collection.getId()) {
                    sideboard.add(sideboardCards.remove(i).getCardString());
                    i--;
                }
            }
            User author = collection.getAuthor();
            collections.add(new CollectionConvertedWithCards(collection.getId(),
                    new UserConverted(author.getId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                            author.getEmail(), author.getRole()),
                    collection.getName(), collection.getDescription(), collection.getIsPrivate(), collection.getIsPrototype(),
                    mainboard.toArray(new String[mainboard.size()]), collection.getFeaturedCard()));
        });

        return collections;
    }

    @GetMapping("/old/{id}")
    public List<CollectionCard> findByCollectionIDOld(@PathVariable("id") int collectionID) {
        return collectionCardService.findByCollectionID(collectionID);
    }

    @GetMapping("/{id}")
    public CollectionConvertedWithCards findByCollectionID(@PathVariable("id") int collectionID) {
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
            if (card.getAmount() == 0) {
                continue;
            }
            String cardString = card.getAmount() + "x " + card.getCard();
            cards.add(cardString);

        }

        User author = dbCollection.getAuthor();
        collection = new CollectionConvertedWithCards(dbCollection.getId(),
                new UserConverted(author.getId(), author.getUsername(), author.getFirstName(), author.getLastName(),
                        author.getEmail(), author.getRole()),
                dbCollection.getName(), dbCollection.getDescription(), dbCollection.getIsPrivate(),
                dbCollection.getIsPrototype(), cards.toArray(new String[cards.size()]), dbCollection.getFeaturedCard());

        return collection;
    }

    @PostMapping("/old")
    public List<CollectionCard> createCollection(@RequestBody List<CollectionCard> collection) {
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
    public CollectionConvertedWithCards createCollection(@RequestBody CollectionConvertedWithCards newCollection) {
        Collection newDBCollection = new Collection(newCollection.getId(),
                new User(newCollection.getAuthor().getId(), newCollection.getAuthor().getUsername(), "",
                        newCollection.getAuthor().getFirstName(), newCollection.getAuthor().getLastName(),
                        newCollection.getAuthor().getEmail(), newCollection.getAuthor().getRole()),
                newCollection.getIsPrivate(), newCollection.getIsPrototype(), null, null, newCollection.getCollectionName(),
                newCollection.getCollectionDescription(), newCollection.getFeaturedCard());

        List<CollectionCard> newDBCollectionCard = new ArrayList<>();
        for (String cardString : newCollection.getCards()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = cardString.substring(cardString.indexOf("x ") + 2);
                newDBCollectionCard.add(new CollectionCard(0, newDBCollection, card, cardAmount));
            }
        }

        List<CollectionCard> createdDBCollection = createCollection(newDBCollectionCard);

        return convertToDTO(createdDBCollection);
    }

    @PutMapping("/old")
    public List<CollectionCard> updateCollection(@RequestBody List<CollectionCard> collection) {
        if (collection.size() > 0) {
            Collection newCollection = collectionService.save(collection.get(0).getCollection());
            collection.forEach(card -> {
                card.setCollection(newCollection);
            });
            return collectionCardService.updateCollection(collection, collection.get(0).getCollection().getId());
        }
        return null;
    }

    @PutMapping
    public CollectionConvertedWithCards updateCollection(@RequestBody CollectionConvertedWithCards reqCollection) {
        Collection dbCollectionToUpdate = new Collection(reqCollection.getId(),
                new User(reqCollection.getAuthor().getId(), reqCollection.getAuthor().getUsername(), null,
                        reqCollection.getAuthor().getFirstName(), reqCollection.getAuthor().getLastName(),
                        reqCollection.getAuthor().getEmail(), reqCollection.getAuthor().getRole()),
                reqCollection.getIsPrivate(), reqCollection.getIsPrototype(), null, null, reqCollection.getCollectionName(),
                reqCollection.getCollectionDescription(), reqCollection.getFeaturedCard());

        List<CollectionCard> dbCollectionCardUpdated = new ArrayList<>();
        for (String cardString : reqCollection.getCards()) {
            if (cardString.contains("x ")) {
                int cardAmount = Integer.parseInt(cardString.substring(0, cardString.indexOf("x ")));
                String card = cardString.substring(cardString.indexOf("x ") + 2);
                dbCollectionCardUpdated.add(new CollectionCard(0, dbCollectionToUpdate, card, cardAmount));
            }
        }

        System.out.println(dbCollectionCardUpdated);

        List<CollectionCard> updatedDBCollection = updateCollection(dbCollectionCardUpdated);

        System.out.println(updatedDBCollection);

        return convertToDTO(updatedDBCollection);
    }
}