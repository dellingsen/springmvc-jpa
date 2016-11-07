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

import com.home.webapps.domain.Role;
import com.home.webapps.domain.User;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao
{
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	public Role findRole(int id)  
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Role.class, id);
    }
 
	public List<Role> findAllRoles()
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = cb.createQuery(Role.class);
        Root<Role> role = criteria.from(Role.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(role).orderBy(cb.asc(role.get("userName")));
        return em.createQuery(criteria).getResultList();
    }

    public void save(Role role)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
    	em.getTransaction().begin();
        em.persist(role);
		em.getTransaction().commit();
    }

    public void delete(Role role)
    {
		EntityManager em = entityManagerFactory.createEntityManager();
        em.remove(role);
    }

}
