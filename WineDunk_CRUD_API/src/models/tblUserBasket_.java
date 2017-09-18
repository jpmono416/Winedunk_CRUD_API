package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.477+0100")
@StaticMetamodel(tblUserBasket.class)
public class tblUserBasket_ {
	public static volatile SingularAttribute<tblUserBasket, Integer> id;
	public static volatile SingularAttribute<tblUserBasket, tblUsers> userId;
	public static volatile SingularAttribute<tblUserBasket, tblWines> wineId;
	public static volatile SingularAttribute<tblUserBasket, tblShops> shopId;
	public static volatile SingularAttribute<tblUserBasket, tblPartners> partnerId;
	public static volatile SingularAttribute<tblUserBasket, Date> date;
	public static volatile SingularAttribute<tblUserBasket, Date> timestamp;
	public static volatile SingularAttribute<tblUserBasket, String> destinationURL;
	public static volatile SingularAttribute<tblUserBasket, Float> productPrice;
	public static volatile SingularAttribute<tblUserBasket, String> partnerProductId;
}
