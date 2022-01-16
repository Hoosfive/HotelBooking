package com.example.hotelbooking.service;


import com.example.hotelbooking.ObjectNotFoundException;
import com.example.hotelbooking.entity.Role;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.repository.ImagesRepository;
import com.example.hotelbooking.repository.RolesRepository;
import com.example.hotelbooking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    public UsersService(UsersRepository usersRepository, RolesRepository rolesRepository, ImagesRepository imagesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.imagesRepository = imagesRepository;
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User get(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(User.class, id));
    }

    public List<User> getAll() {
        return (List<User>) usersRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(rolesRepository.findById(1L).orElseThrow(() -> new ObjectNotFoundException(Role.class, 1L)));
        user.setRoles(roles);
        user.setImage(imagesRepository.findFirstByIdIsNear(1L));
        return usersRepository.save(user);
    }

    public User update(User user) {
        return usersRepository.save(user);
    }

    public void remove(Long id) {
        usersRepository.deleteById(id);
    }


}
