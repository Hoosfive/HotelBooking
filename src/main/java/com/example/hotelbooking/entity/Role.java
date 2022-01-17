package com.example.hotelbooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles", indexes = {
        @Index(name = "role_name_uindex", columnList = "name", unique = true)
})
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 20)
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}