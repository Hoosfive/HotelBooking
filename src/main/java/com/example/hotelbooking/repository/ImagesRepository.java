package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends CrudRepository<Image, Long> {

}
