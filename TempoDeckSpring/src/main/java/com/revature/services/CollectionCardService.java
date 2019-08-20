package com.revature.services;

import java.util.List;

import com.revature.models.CollectionCard;
import com.revature.repos.CollectionCardRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CollectionCardService {

	@Autowired
	private CollectionCardRepo collectionCardRepo;

	private Sort orderBy() {
		return new Sort(Sort.Direction.ASC, "collection").and(new Sort(Sort.Direction.ASC, "card"));
	}

	public List<CollectionCard> findAll() {
		Sort sortSpec = orderBy();
		return collectionCardRepo.findAll(sortSpec);
	}

	public List<CollectionCard> findByCollectionID(int collectionID) {
		return collectionCardRepo.findByCollectionIdOrderByCard(collectionID);
	}

	public List<CollectionCard> createCollection(List<CollectionCard> collection) {
		return collectionCardRepo.saveAll(collection);
	}

	public List<CollectionCard> updateCollection(List<CollectionCard> collection, int collectionID) {
		List<CollectionCard> oldCards = collectionCardRepo.findByCollectionIdOrderByCard(collectionID);
		List<CollectionCard> newCards = collection;
		for (int j = 0; j < oldCards.size(); j++) {
			boolean delete = true;
			for (int i = 0; i < newCards.size(); i++) {
				if (newCards.get(i).getCard().equals(oldCards.get(j).getCard())) {
					delete = false;
					oldCards.get(j).setAmount(newCards.get(i).getAmount());
					newCards.remove(i);
					i--;
					break;
				}
			}
			if (delete) {
				oldCards.get(j).setAmount(0);
			}
		}
		collectionCardRepo.saveAll(newCards);
		List<CollectionCard> returnCards = collectionCardRepo.findByCollectionIdOrderByCard(collectionID);
		collectionCardRepo.flush();
		return returnCards;
	}

}