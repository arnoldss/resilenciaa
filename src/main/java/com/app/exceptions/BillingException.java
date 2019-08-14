package com.app.exceptions;

public class BillingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String EXCEPTION_MESSAGE	=	"It is not possible bill this user. ";
	static final String SPACE	=	" ";

	public BillingException(String message) {
		
		super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
	}
}
