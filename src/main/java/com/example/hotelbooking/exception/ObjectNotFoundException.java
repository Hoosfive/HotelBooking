package com.example.hotelbooking.exception;

public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException(Class<?> object, Long id) {
		super(String.format("%s with id = %d not found", object.getSimpleName(), id));
	}
}