package com.revature.repos;

import org.springframework.stereotype.Service;

import java.util.List;

import com.revature.models.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

@Service
public interface CollectionRepo extends JpaRepository<Collection, Integer> {

    Collection findById(int collectionId);

	List<Collection> findAllByAuthorId(int authorID);

	List<Collection> findAllByFeaturedCard(String featuredCard);
}