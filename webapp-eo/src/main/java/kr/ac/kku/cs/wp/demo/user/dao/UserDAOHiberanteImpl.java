package kr.ac.kku.cs.wp.demo.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kr.ac.kku.cs.wp.demo.support.sql.HibernateUtil;
import kr.ac.kku.cs.wp.demo.tools.message.MessageException;

import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.entity.UserRole;

public class UserDAOHiberanteImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger(UserDAOHiberanteImpl.class);
	// UserDAOmethod구현

	public User getUserById(String userId) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = session.get(User.class, userId);
		} catch (HibernateException e) {
			// TODOAuto-generatedcatchblock
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUser(User user) {
		Transaction tx = null;
		Session session = null;
		if (user != null) {
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				user = session.get(User.class, user.getId());
				tx.commit();
			} catch (HibernateException e) {
				// TODOAuto-generatedcatchblock
				e.printStackTrace();
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				throw new MessageException(e.getMessage(), e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			User exitingUser = session.get(User.class, user.getId());
			user = session.merge(user);
		} catch (HibernateException e) {
			// TODOAuto-generatedcatchblock
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new MessageException(e.getMessage(), e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public void deleteUser(User user) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.remove(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new MessageException(e.getMessage(), e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public User createUser(User user) {
		User rtn = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String newId = generateNewId(session);
			user.setId(newId);
			user.setPassword(newId);
			session.persist(user);
			tx.commit();
			rtn = session.get(User.class, user.getId());
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw new MessageException(e.getMessage(), e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return rtn;
	}

	/**
	 * uniqueid생성
	 ** 
	 * @paramsession
	 ** @return
	 */

	public String generateNewId(Session session) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.orderBy(cb.desc(root.get("id")));

		Query<User> query = session.createQuery(cq);
		User user = query.setMaxResults(1).uniqueResult();
		String prefix = "kku_";
		String lastId = user.getId();

		int lastNumber = Integer.parseInt(lastId.split("_")[1]);
		int newNuumber = lastNumber + 1;
		return prefix + newNuumber;
	}

	@Override
	public List<User> getUsers(User user) {
		List<User> users = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = cb.createQuery(User.class);
			
			Root<User> root = criteria.from(User.class);
			Join<User, UserRole> userRolesJoin = root.join("userRoles", JoinType.LEFT);
			criteria.select(root);
			
			List<Predicate> cds = new ArrayList<Predicate>();
			if (user != null) {
				if (user.getId() != null && !user.getId().isEmpty())
					cds.add(cb.like(cb.lower(root.get("id")), "%" + user.getId().toLowerCase() + "%"));
				if (user.getName() != null && !user.getName().isEmpty())
					cds.add(cb.like(cb.lower(root.get("name")), "%" + user.getName().toLowerCase() + "%"));
				if (user.getEmail() != null && !user.getEmail().isEmpty())
					cds.add(cb.like(cb.lower(root.get("email")), "%" + user.getEmail().toLowerCase() + "%"));
				if (user.getStatus() != null && !user.getStatus().isEmpty())
					cds.add(cb.like(cb.lower(root.get("status")), "%" + user.getStatus().toLowerCase() + "%"));
				if (user.getUserRoles() != null && user.getUserRoles().size() > 0)
					cds.add(cb.like(userRolesJoin.get("roleName"),
							"%" + user.getUserRoles().get(0).getRoleName() + "%"));
				Predicate[] pArr = new Predicate[cds.size()];
				criteria.select(root).where(cb.or((Predicate[]) cds.toArray(pArr)));
			}
			users = session.createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			// TODOAuto-generatedcatchblock
			e.printStackTrace();
		}
		return users;
	}

}
