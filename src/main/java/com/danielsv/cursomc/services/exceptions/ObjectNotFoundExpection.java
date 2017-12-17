package com.danielsv.cursomc.services.exceptions;

public class ObjectNotFoundExpection extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExpection(String msg) {
		super(msg);
	}

	public ObjectNotFoundExpection(String msg, Throwable cause) {
		super(msg, cause);
	}
}
