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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.domain.Person;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao
{
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

    public Person findById(long id)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Person.class, id);
    }

    public Person findByEmail(String email)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> person = criteria.from(Person.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(person).where(builder.equal(person.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public Person findByName(String name)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> person = criteria.from(Person.class);

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
        criteria.select(person).where(builder.equal(person.get("lastName"), name));
        try {
	        TypedQuery<Person> tq = em.createQuery(criteria);
	        return tq.getSingleResult();
        }
        catch (NoResultException nre) {
        	return null;
        }
    }

    public List<Person> findAllOrderedByName()
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
        Root<Person> member = criteria.from(Person.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public void save(Person person)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(person);
        return;
    }

    public void delete(Person person)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        em.remove(person);
    }
}
