package com.example.hotelbooking.service;

import com.example.hotelbooking.exception.ObjectNotFoundException;
import com.example.hotelbooking.entity.Image;
import com.example.hotelbooking.repository.ImagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImagesService {
	private final ImagesRepository imagesRepository;
	
	public ImagesService(ImagesRepository imagesRepository) {
		this.imagesRepository = imagesRepository;
	}
	
	public Image get(Long id) {
		return imagesRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Image.class, id));
	}
	
	public List<Image> getAll() {
		return (List<Image>) imagesRepository.findAll();
	}
	
	public Image save(MultipartFile file) {
		try {
			Image image = new Image(file.getOriginalFilename(), file.getBytes());
			return imagesRepository.save(image);
		} catch (IOException ignored) {
			return new Image();
		}
	}
	
	public Image update(Image image, MultipartFile file) {
		try {
			image.setName(file.getOriginalFilename());
			image.setPictureBytes(file.getBytes());
			return imagesRepository.save(image);
		} catch (IOException ignored) {
			return new Image();
		}
	}
	
	public void remove(Long id) {
		imagesRepository.deleteById(id);
	}
}
