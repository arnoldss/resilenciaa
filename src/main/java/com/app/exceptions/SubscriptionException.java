package com.app.exceptions;

public class SubscriptionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String EXCEPTION_MESSAGE	=	"It is not possible subscribe this user. ";
	static final String SPACE	=	" ";

	public SubscriptionException(String message) {
		
		super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
	}
}
