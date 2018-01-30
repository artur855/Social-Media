package com.arthurzera.website.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<User> users = new HashSet<>();

	public Role() {
		
	}
	
	public Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
		user.getRoles().add(this);
	}

	public void removeUser(User user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}

}
