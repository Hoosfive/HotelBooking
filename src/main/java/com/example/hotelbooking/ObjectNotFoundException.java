package com.example.hotelbooking;

public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException(Class<?> object, Long id) {
		super(String.format("%s with id = %d not found", object.getSimpleName(), id));
	}
}