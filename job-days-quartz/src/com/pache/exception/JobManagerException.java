package com.pache.exception;

public class JobManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	public JobManagerException() {
		super();
	}
	
	public JobManagerException(String mensage) {
		super(mensage);
	}
}
