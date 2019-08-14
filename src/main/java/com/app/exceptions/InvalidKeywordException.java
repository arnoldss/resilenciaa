package com.app.exceptions;

public class InvalidKeywordException  extends Exception{
    private static final long serialVersionUID = 1L;
    static final String EXCEPTION_MESSAGE	=	"Invalid Keyword ";
    static final String SPACE	=	" ";

    public InvalidKeywordException(String message) {

        super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
    }
}
