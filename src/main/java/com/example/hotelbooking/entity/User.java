package com.example.hotelbooking.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "image_id")
	private Image image;
	
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Transient
	private Set<Integer> rolesIds;
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public String getRolesAsString() {
		return roles.isEmpty() ? "*отсутствуют*" : roles.stream().map(Role::getName).collect(Collectors.joining(", "));
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setRolesIds(Set<Integer> rolesIds) {
		this.rolesIds = rolesIds;
	}
	
	public void syncRolesIdsWithRoles() {
		if (!rolesIds.isEmpty()) {
			Set<Role> tempRoles = new HashSet<>();
			for (Role role : this.roles) {
				if (rolesIds.contains(role.getId())) {
					tempRoles.add(role);
				}
			}
			setRoles(tempRoles);
		}
	}
	
	public Set<Integer> getRolesIds() {
		return rolesIds;
	}
	
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}