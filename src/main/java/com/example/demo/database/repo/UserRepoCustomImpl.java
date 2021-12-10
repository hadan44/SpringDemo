package com.example.demo.database.repo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.database.entity.User;

public class UserRepoCustomImpl implements UserRepoCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByName(String username) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u FROM User u");
		sb.append(" WHERE u.username = :username");
		Query qr = em.createQuery(sb.toString());
		qr.setParameter("username", username);

		User user = null;
		try {
			user = (User) qr.getSingleResult();
		}catch(NoResultException u) {
			System.out.println(u);
		}
		return user;
	}

	@Override
	public User findById(int userId) {
		System.out.println(userId);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u FROM User u");
		sb.append(" WHERE u.id = :id");
		Query qr = em.createQuery(sb.toString());
		
		qr.setParameter("id", userId);
		System.out.println(qr);
		User user = null;
		try {
			user = (User) qr.getSingleResult();
		}catch(NoResultException u) {
			System.out.println(u);
		}
		return user ;
	}
}
