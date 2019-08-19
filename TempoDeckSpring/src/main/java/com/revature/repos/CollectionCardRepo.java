package com.revature.repos;

import java.util.List;

import com.revature.models.CollectionCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CollectionCardRepo extends JpaRepository<CollectionCard, Integer> {

	List<CollectionCard> findByCollectionIdOrderByCard(int collectionID);

    
}