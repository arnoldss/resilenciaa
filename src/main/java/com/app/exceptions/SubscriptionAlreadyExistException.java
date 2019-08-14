package com.app.exceptions;

public class SubscriptionAlreadyExistException extends Exception {

    private static final long serialVersionUID = 1L;
    static final String EXCEPTION_MESSAGE	=	"Subscription Already exist ";
    static final String SPACE	=	" ";

    public SubscriptionAlreadyExistException(String message) {

        super(EXCEPTION_MESSAGE.concat(SPACE).concat(message));
    }
}
