package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.190+0100")
@StaticMetamodel(tblShops.class)
public class tblShops_ {
	public static volatile SingularAttribute<tblShops, Integer> id;
	public static volatile SingularAttribute<tblShops, String> name;
	public static volatile SingularAttribute<tblShops, String> logo;
	public static volatile SingularAttribute<tblShops, String> homePage;
	public static volatile SingularAttribute<tblShops, String> genericProductPage;
	public static volatile SingularAttribute<tblShops, Boolean> deleted;
	public static volatile ListAttribute<tblShops, tblClicks> clicks;
	public static volatile ListAttribute<tblShops, tblWines> wines;
	public static volatile ListAttribute<tblShops, tblWinesbyMerchants> winesByMerchant;
	public static volatile ListAttribute<tblShops, Tblpfmerchanthtmlparsing> parsingByMerchant;
}
