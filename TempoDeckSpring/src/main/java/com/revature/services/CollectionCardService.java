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
        return new Sort(Sort.Direction.ASC, "collection")
                    .and(new Sort(Sort.Direction.ASC, "card"));
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
}