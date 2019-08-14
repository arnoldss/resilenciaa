package com.app.exceptions;

public class RegisterTransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String EXCEPTION_MESSAGE	=	"It is not possible register the transaction";
	static final String SPACE	=	" ";

	public RegisterTransactionException(String message) {
		
		super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
	}
}
