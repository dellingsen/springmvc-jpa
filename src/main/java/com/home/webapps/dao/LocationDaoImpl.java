package com.home.webapps.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.home.webapps.domain.Location;

@Repository
public class LocationDaoImpl implements LocationDao 
{
 
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	//@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Transactional(propagation = Propagation.MANDATORY)
	public void saveLocation(Location location) throws Exception {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(location);
		em.getTransaction().commit();
    }

	//@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Transactional(propagation = Propagation.MANDATORY)
    public void removeLocation(Location location) throws Exception {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		location = em.find(Location.class, location.getId());
		em.remove(location);
		em.getTransaction().commit();
    }

    public Location getLocation(Location location) throws Exception {
		EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Location.class, location.getId());
    }

    public Location getLocationByName(Location location) throws Exception {
		EntityManager em = entityManagerFactory.createEntityManager();
   	    Query query = em.createQuery("select L from Location L where L.name like '%" + location.getName() + "%'");
   	    
   	    //Will blow up if more than 1 (shouldn't be)
   	    //return (Location) query.getSingleResult();
   	    return (Location) query.getResultList().get(query.getFirstResult());
    }

    public List<Location> getLocations() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
   	    Query query = em.createQuery("select L from Location L");
	    //System.out.println("LocationDAO - query: " + query.getResultList());
        return query.getResultList();
    }  
      
}
