package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.297+0100")
@StaticMetamodel(tblClicks.class)
public class tblClicks_ {
	public static volatile SingularAttribute<tblClicks, Integer> id;
	public static volatile SingularAttribute<tblClicks, tblUsers> userId;
	public static volatile SingularAttribute<tblClicks, tblWines> wineId;
	public static volatile SingularAttribute<tblClicks, tblShops> shopId;
	public static volatile SingularAttribute<tblClicks, tblPartners> partnerId;
	public static volatile SingularAttribute<tblClicks, Date> date;
	public static volatile SingularAttribute<tblClicks, Date> timestamp;
	public static volatile SingularAttribute<tblClicks, String> destinationURL;
	public static volatile SingularAttribute<tblClicks, Float> productPrice;
	public static volatile SingularAttribute<tblClicks, Boolean> deleted;
}
