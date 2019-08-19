package com.revature.services;

import java.util.List;

import com.revature.models.Collection;
import com.revature.repos.CollectionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepo collectionRepo;

	public List<Collection> findAll() {
		return collectionRepo.findAll();
	}

	public Collection findByCollectionId(int collectionID) {
		return collectionRepo.findById(collectionID);
	}

	public List<Collection> findByAuthorId(int authorID) {
		return collectionRepo.findAllByAuthorId(authorID);
	}

	public List<Collection> findByFeaturedCard(String featuredCard) {
		return collectionRepo.findAllByFeaturedCard(featuredCard);
	}

	public Collection save(Collection collection) {
		return collectionRepo.saveAndFlush(collection);
	}
}