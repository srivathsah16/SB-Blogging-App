package com.srivath.blog.app.exceptions;

import lombok.Getter;
import lombok.Setter;

/*
 * Note : This is a Custom Exception extending RuntimeException Class.
 */
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Integer fieldId;

	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldId) {
		// this super(String message) method calls the constructor of the SuperClass of
		// this class i.e, RuntimeException by passing the custom error message
		// This helps to initialize this exception with this message.
		super(resourceName + " not found with " + fieldName + " : " + fieldId);

		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
}
