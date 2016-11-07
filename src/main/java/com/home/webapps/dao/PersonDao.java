package com.home.webapps.dao;

import java.util.List;
import com.home.webapps.domain.Person;

public interface PersonDao
{
    public Person findById(long id);

    public Person findByName(String name);
    
    public Person findByEmail(String email);

    public List<Person> findAllOrderedByName();

    public void save(Person person);

    public void delete(Person person);
}
