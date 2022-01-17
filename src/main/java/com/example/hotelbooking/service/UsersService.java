package com.example.hotelbooking.service;


import com.example.hotelbooking.ObjectNotFoundException;
import com.example.hotelbooking.entity.Role;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.repository.ImagesRepository;
import com.example.hotelbooking.repository.RolesRepository;
import com.example.hotelbooking.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
	
	private final UsersRepository usersRepository;
	
	private final RolesRepository rolesRepository;
	
	
	public UsersService(UsersRepository usersRepository, RolesRepository rolesRepository,
						ImagesRepository imagesRepository) {
		this.usersRepository = usersRepository;
		this.rolesRepository = rolesRepository;
	}
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public User get(Long id) {
		return usersRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(User.class, id));
	}
	
	public User getByName(String name) {
		return usersRepository.findByName(name);
	}
	
	public List<User> getAll() {
		return (List<User>) usersRepository.findAll();
	}
	
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public void update(User user) {
		usersRepository.save(user);
	}
	
	public void remove(Long id) {
		usersRepository.deleteById(id);
	}
	
	public List<Role> getAllRoles() {
		return (List<Role>) rolesRepository.findAll();
	}
	
}
