package com.app.resiliencia.util;

public class Status {


	public static final String SUBSCRIPTION_ALREADY_EXISTS ="-72" ;
	public static final String PASSWORD_LIMITED_EXCEEDED = "";
	public static final String SUBSCRIPTION_NOT_ACTIVATE = "";
	public static int INCOMING	=	1;
	public static int SUBSCRIBED=	2;
	public static int BILLED	=	3;
	public static int FAILED	=	4;
	public static int PENDING_RENEWAL=5;
	public static int PENDING_SUBSCRIBE=6;
	public static int PENDING_BILL=7;
	public static int PENDING_REGISTER_BILL=8;
	public static int PENDING_SEND_MT_WELCOME=9;
	public static int PENDING_SEND_MT_BILLING=10;
	public static int MT_IN_PROGRESS=11;
	public static int INSUFFICIENT_BALANCE=12;
	public static int MT_SENT=13;
	public static int TIMWE_API_SUB_RETURNS_NULL=14;
 	public static int PENDING_CANCEL=15;

	public static int TIMWE_API_UNSUB_RETURNS_NULL=14;

	public static Integer INVALID_DESTINATION	=	-1;
	public static Integer GENERAL_ERROR	=	0;
	public static Integer TIMWE_SUCCESS	=	1;
	public static Integer INVALID_OPERATOR	=	-2;
	public static Integer INVALID_CREDENTIALS	=	-3;
	public static Integer INVALID_PRICEPOINT	=	-4;
	public static Integer INVALID_PARTNER_PRODUCT	=	-6;
	public static Integer TRANSACTION_IN_PROCESS	=	-10;
	public static Integer BLACKLISTED_DESTINATION	=	-12;
	public static Integer INVALID_IP	=	-50;
	public static Integer INVALID_BUY_CHANNEL	=	-51;
	public static Integer INVALID_CLUB_COUNTRY_MATCH	=	-52;
	public static Integer INVALID_CLUB_CONFIGURATION	=	-53;
	public static Integer INVALID_COUNTRY_CONFIGURATION	=	-54;
	public static Integer INVALID_MSISDN	=	-55;
	public static Integer INVALID_SUBSCRIPTION = -56;
	public static Integer INVALID_KEYWORD	=	-57;
	public static Integer INVALID_SUB_STATUS	=	-59;
	public static Integer INVALID_Aoptin	=	-60;
	public static Integer INVALID_CONTENT_TYPE	=	-61;
	public static Integer INVALID_SERVICE_TYPE	=	-62;
	public static Integer INVALID_DEV_NAME		=	-63;
	public static Integer INVALID_EXT_FILE		=	-64;
	public static Integer INVALID_CONTENT_ID	=	-65;
	public static Integer INVALID_AUTHOR		=	-66;
	public static Integer INVALID_TEXT_VARIANT	=	-67;
	public static Integer INVALID_TRACKING_ID	=	-68;
	public static Integer MISSING_IP			=	-69;
	public static Integer MISSING_PARAMETERS	=	-70;
	public static Integer ACCESS_DENIED			=	-71;
	public static Integer SUB_ALREADY_EXISTS	=	-72;
	public static Integer SUB_CANCELED			=	-73;
	public static Integer SUB_CHARGED			=	-74;
	public static Integer SUB_NOT_PREACTIVE		=	-76;
	public static Integer SUB_NOT_ACTIVE		=	-77;
	public static Integer SUB_NOT_CHARGED		=	-78;
	public static Integer CUSTOMER_NOT_EXISTS	=	-80;
	public static Integer CUSTOMER_PASSWORDS_EXCEED	=	-81;
	public static Integer SUB_MT_NOT_SENT		=	-82;
	public static Integer ERROR_CREATING_SUBSCRIPTION	=	-83;
	public static Integer SUBSCRIPTION_STATUS_NOT_VALID	=	-84;
	public static Integer INVALID_PIN	=	-85;
	public static Integer INVALID_PARAMETERS	=	-86;

}
