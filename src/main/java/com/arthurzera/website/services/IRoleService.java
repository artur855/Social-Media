package com.arthurzera.website.services;

import java.util.List;

import com.arthurzera.website.models.Role;
 

public interface IRoleService {

    List<Role> findAll();
    Role findById(Long id);
    Role findByRole(String role);
    Role create(Role role);
    Role edit(Role role);
    void deleteById(Long id);
	
}
