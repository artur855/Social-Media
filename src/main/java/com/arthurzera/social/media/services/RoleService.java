package com.arthurzera.social.media.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arthurzera.social.media.models.Role;
import com.arthurzera.social.media.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role edit(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.delete(id);
	}
	
}
