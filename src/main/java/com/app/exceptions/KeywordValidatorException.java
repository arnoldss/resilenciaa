package com.app.exceptions;

public class KeywordValidatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String EXCEPTION_MESSAGE	=	"There is not information to validate";
	static final String SPACE	=	" ";

	public KeywordValidatorException(String message) {
		
		super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
	}
}
