package com.home.webapps.dao;

import java.util.List;

import com.home.webapps.domain.Role;

public interface RoleDao
{
    public Role findRole(int id);

    public List<Role> findAllRoles();

    public void save(Role user);

    public void delete(Role user);
}
