
 CREATE TABLE BNB_OOREDOO_TRANSACTIONS (
    ID SERIAL NOT NULL PRIMARY KEY,
    ORIGINAL_TEXT TEXT,
    CREATED_DATE timestamp,
    LAST_UPDATE_DATE timestamp,
    msisdn 		TEXT,
 	serviceName	TEXT,
 	productId 	int,
 	pricepointId	int,
 	mccmnc				int,
  	optInType		TEXT,
    seviceIdCarrier int,
    serviceIdApiCore int,
    serviceIdPromos  int,
    billattemps      int,
    lastDateAttemp date,
    billed          boolean,
    codeResponseTIMWESubscription   TEXT,
    urlPromos   TEXT,
    urlPortal   TEXT,
    serviceId   int,
    newSubscriber      boolean
);
ALTER TABLE bnb_ooredoo_transactions ADD CONSTRAINT constraint_name UNIQUE (msisdn,serviceidapicore);
ALTER TABLE bnb_ooredoo_transactions ADD COLUMN correlatorId TEXT;
ALTER TABLE bnb_ooredoo_transactions ADD COLUMN attempId TEXT;
ALTER TABLE bnb_ooredoo_transactions ADD COLUMN message TEXT;
