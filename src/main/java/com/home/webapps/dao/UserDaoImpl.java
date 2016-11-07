package com.home.webapps.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	PasswordEncoder passwordEncoder;

    public User findById(long id)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(User.class, id);
    }
 
    public User findByUserName(String username, String password)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

    	String hashedPassword = passwordEncoder.encodePassword(password, username);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(person).orderBy(cb.asc(person.get(Person_.name)));
         */

        //Query query = em.createNamedQuery("Person.findActivePersonByName");
        //query.setParameter("lastName", name);
        //return (Person) query.getSingleResult();
        //Person personUnique = (Person) query.getSingleResult();
        //if (personUnique != null) {
        //	System.out.println("findByName() - person: " + personUnique.getLastName());
        //}
        //Person personZero = (Person) query.getResultList().get(0);
        //Path<Object> lName = person.get("lastName");
        //CriteriaQuery<Person> selectCriteria = criteria.select(person);
        criteria.select(user).where(builder.equal(user.get("userName"), username),
        							builder.equal(user.get("password"), hashedPassword),
        							builder.equal(user.get("locked"), "N"),
        							builder.equal(user.get("active"), "Y"));
        try {
	        TypedQuery<User> tq = em.createQuery(criteria);
	        return tq.getSingleResult();
        }
        catch (NoResultException nre) {
        	return null;
        }
    }

    public List<User> findAllOrderedByName()
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(user).orderBy(cb.asc(user.get("userName")));
        return em.createQuery(criteria).getResultList();
    }

    public void save(User user)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
    	em.getTransaction().begin();
        em.persist(user);
		em.getTransaction().commit();
    }

    public void delete(User user)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        em.remove(user);
    }
}
