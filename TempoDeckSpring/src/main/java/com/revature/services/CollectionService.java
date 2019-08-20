package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import com.revature.dtos.CollectionConvertedNoCards;
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

	@Transactional
	public Collection updateCollection(Collection collection) {
		Collection collectionToUpdate = collectionRepo.getOne(collection.getId());
		collectionToUpdate.setAuthor(collection.getAuthor());
		collectionToUpdate.setDescription(collection.getDescription());
		collectionToUpdate.setFeaturedCard(collection.getFeaturedCard());
		collectionToUpdate.setName(collection.getName());
		collectionToUpdate.setPrivate(collection.getIsPrivate());
		collectionToUpdate.setPrototype(collection.getIsPrototype());
		return collectionToUpdate;
	}

	@Transactional
	public Collection updateCollection(CollectionConvertedNoCards collection) {
		Collection collectionToUpdate = collectionRepo.getOne(collection.getId());
		collectionToUpdate.setDescription(collection.getCollectionDescription());
		collectionToUpdate.setFeaturedCard(collection.getFeaturedCard());
		collectionToUpdate.setName(collection.getCollectionName());
		collectionToUpdate.setPrivate(collection.getIsPrivate());
		collectionToUpdate.setPrototype(collection.getIsPrototype());
		return collectionToUpdate;
	}
}