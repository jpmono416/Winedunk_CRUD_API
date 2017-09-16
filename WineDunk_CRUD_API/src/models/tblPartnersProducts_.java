package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.826+0100")
@StaticMetamodel(tblPartnersProducts.class)
public class tblPartnersProducts_ {
	public static volatile SingularAttribute<tblPartnersProducts, Integer> id;
	public static volatile SingularAttribute<tblPartnersProducts, tblPartners> partnerId;
	public static volatile SingularAttribute<tblPartnersProducts, tblWines> tblWines;
	public static volatile SingularAttribute<tblPartnersProducts, tblShops> shopId;
	public static volatile SingularAttribute<tblPartnersProducts, String> partnerProductId;
	public static volatile SingularAttribute<tblPartnersProducts, Float> partnerProductPrice;
	public static volatile SingularAttribute<tblPartnersProducts, String> partnerDestinationUrl;
	public static volatile SingularAttribute<tblPartnersProducts, String> partnerMerchantId;
	public static volatile SingularAttribute<tblPartnersProducts, String> partnerMerchantProductId;
	public static volatile SingularAttribute<tblPartnersProducts, Integer> partnerMerchantStock;
	public static volatile SingularAttribute<tblPartnersProducts, Float> partnerMerchantDeliveringCost;
	public static volatile SingularAttribute<tblPartnersProducts, Date> lastUpdated;
	public static volatile SingularAttribute<tblPartnersProducts, String> lastMD5;
	public static volatile SingularAttribute<tblPartnersProducts, Boolean> deleted;
	public static volatile SingularAttribute<tblPartnersProducts, Date> deletedDate;
}
