package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.500+0100")
@StaticMetamodel(tblUserPriceAlerts.class)
public class tblUserPriceAlerts_ {
	public static volatile SingularAttribute<tblUserPriceAlerts, Integer> id;
	public static volatile SingularAttribute<tblUserPriceAlerts, String> name;
	public static volatile SingularAttribute<tblUserPriceAlerts, tblUsers> userId;
	public static volatile SingularAttribute<tblUserPriceAlerts, tblWines> wineId;
	public static volatile SingularAttribute<tblUserPriceAlerts, Float> price;
}
