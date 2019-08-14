package com.app.exceptions;

public class StopCCTException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static final String EXCEPTION_MESSAGE	=	"STOPCCT EXCEPTION";
	static final String SPACE	=	" ";

	public StopCCTException(String message) {
		
		super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
	}
}
