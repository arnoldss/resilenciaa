package com.app.resiliencia.util;

import java.util.HashMap;

public class Constants {

	public static final String  SUCCESS	=	"200";
	public static final Integer DELIVERY_SUCCESS	=	1;
	public static final Integer DELIVERY_FAILED	=	2;
	public static final Integer DAILY	=	1;
	public static final Integer WEEKLY	=	2;
	public static final String SUBSCRIPTION_SUCCESS="1";
	public static final Long 	GST =	10800000L;
	public static final Integer RESPONSE_API_CORE_SUCCESS	=	201;
	public static final Integer IN_PROCESS_CODE_API_CORE	=	101;
	public static final String 	IN_PROCESS_MESSAGE_API_CORE	=	"in_process";
	public static final String WAP	=	"WAP";
	public static final String WEB	=	"WEB";
	public static final String SMS	=	"SMS";
	public static final String URL	=	"url";

	public static final Integer TIMWE_RESPONSE_NOTIFY_SUCCESS	=	1;
	public static final Integer TIMWE_RESPONSE_NOTIFY_GENERAL_ERROR	=	0;
	public static final Integer TIMWE_RESPONSE_NOTIFY_INVALID_MSISDN	=	-2;
	public static final Integer TIMWE_RESPONSE_NOTIFY_INVALID_CREDENTIAL	=	-3;
	public static final Integer TIMWE_RESPONSE_NOTIFY_VALIDATION_ERROR	=	-4;
	public static final Integer TIMWE_RESPONSE_NOTIFY_INTERNAL_ERROR	=	-5;
	public static final Integer TIMWE_RESPONSE_NOTIFY_MAXIMUM_PIN_RETRIES	=	-6;
	public static final Integer TIMWE_RESPONSE_NOTIFY_DUPLICATE_CORRELATOR_ID	=	-10;
	public static final Integer TIMWE_RESPONSE_NOTIFY_BLACK_LISTED_DESTINATION	=	-12;
	public static final Integer TIMWE_RESPONSE_NOTIFY_SUBSCRIPTION_ALREADY_EXISTS	=	-72;
	public static final String BITPLAY	=	"BITPLAY";
	public static final String ENTERFACTORY =	"ENTERFACTORY" ;
	public static final Integer PRICEPOINT_BITPLAY_DAILY =	841615 ;
	public static final Integer PRICEPOINT_BITPLAY_WEEKLY =	841625 ;
	public static final Integer PRICEPOINT_ENTERFACTORY_DAILY = 841635;
	public static final Integer PRICEPOINT_ENTERFACTORY_WEEKLY = 841645;

	public static HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	public static Integer getPeriodicity(Integer pricepointId){
		Integer resp	=	0;
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		hmap.put(841615,Constants.DAILY);
		hmap.put(841625,Constants.WEEKLY);
		hmap.put(841635,Constants.DAILY);
		hmap.put(841645,Constants.WEEKLY);
		resp = hmap.get(pricepointId);
		return resp;
	}
}
