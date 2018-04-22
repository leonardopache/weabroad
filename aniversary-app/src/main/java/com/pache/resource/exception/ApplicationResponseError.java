package com.pache.resource.exception;

public class ApplicationResponseError extends Exception {

	private static final long serialVersionUID = 7625249224228387237L;

	public ApplicationResponseError() {
		super();
	}

	public ApplicationResponseError(String message) {
		super(message);
	}
}
