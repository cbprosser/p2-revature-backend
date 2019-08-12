package com.revature.dao;

import java.util.List;

import com.revature.models.Deck;
import com.revature.models.DeckCard;
import com.revature.util.SessionUtil;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeckDAO {

	private SessionFactory sf = SessionUtil.getSessionFactory();

	public int save(Deck deck) {
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.save(deck);

		t.commit();
		s.close();
		return deck.getId();
	}

	public Deck update(Deck deck) {
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		s.update(deck);

		t.commit();
		s.close();
		return deck;
	}

	public List<DeckCard> findById(int id) {
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();

		String queryString = "SELECT c FROM DeckCard c JOIN c.deck d WHERE d.id = :id";
		// String queryString = "SELECT ic FROM IceCream ic JOIN ic.flavors f WHERE LOWER(f.name) = LOWER(:name)";

		Query q = s.createQuery(queryString);
		q.setParameter("id", id);

		List<DeckCard> cards = q.list();

		cards.forEach(card -> {
			Hibernate.initialize(card.getCard());
		});

		t.commit();
		s.close();
		
		return cards;
	}

// 	public void addToppingToIceCream(int iceCreamId, int toppingId) {
// 		Session s = sf.openSession();
// 		Transaction t = s.beginTransaction();

// 		// when we retreive the icecream it will be persistent until the
// 		// transaction closes, if we modify the object, hibernate will automatically
// 		// update the database as well
// 		// this is known as Automatic Dirty Checking
// 		IceCream ic = (IceCream) s.get(IceCream.class, iceCreamId);
// 		Topping topping = (Topping) s.get(Topping.class, toppingId);

// 		ic.getToppings().add(topping);

// 		// if
// //		ic.setName("Updated"); 

// 		t.commit();
// 		s.close();
// 	}

// 	public List<IceCream> findByFlavorName(String flavorName) {
// 		Session s = sf.openSession();
// 		Transaction t = s.beginTransaction();

// 		String queryString = "SELECT ic FROM IceCream ic JOIN ic.flavors f WHERE LOWER(f.name) = LOWER(:name)";

// 		Query q = s.createQuery(queryString);
// 		q.setString("name", flavorName);
// 		List<IceCream> iceCream = q.list();
// 		iceCream.forEach(ic -> {
// 			Hibernate.initialize(ic.getFlavors());
// 			Hibernate.initialize(ic.getToppings());
// 			Hibernate.initialize(ic.getBrand());
// 		});
// 		t.commit();
// 		s.close();
// 		return iceCream;
// 	}

// 	public List<IceCream> findByFlavorNameCriteria(String flavorName) {
// 		Session s = sf.openSession();
// 		Transaction t = s.beginTransaction();

// 		Criteria c = s.createCriteria(IceCream.class);
// 		c.createAlias("flavors", "f");
// 		c.add(Restrictions.ilike("f.name", flavorName));
// 		List<IceCream> iceCream = c.list();
// 		iceCream.forEach(ele -> System.out.println(ele));
// 		t.commit();
// 		s.close();
// 		return iceCream;
// 	}
}
