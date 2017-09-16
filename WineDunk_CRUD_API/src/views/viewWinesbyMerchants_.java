package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.528+0100")
@StaticMetamodel(viewWinesbyMerchants.class)
public class viewWinesbyMerchants_ {
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> id;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> merchantId;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineId;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> positionIndex;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> merchantName;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> merchantImage;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> merchantHomePage;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineCountryId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineCountryName;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineRegionId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineRegionName;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineWineryId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineWineryName;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineAppellationId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineAppellationName;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineColourId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineColourName;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineVintage;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineName;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineShortDescription;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineDefaultDescription;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineBottleSize;
	public static volatile SingularAttribute<viewWinesbyMerchants, Float> wineAbv;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineImageURL;
	public static volatile SingularAttribute<viewWinesbyMerchants, Integer> wineClosureId;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineClosureName;
	public static volatile SingularAttribute<viewWinesbyMerchants, String> wineGtin;
	public static volatile SingularAttribute<viewWinesbyMerchants, Float> wineMinimumPrice;
	public static volatile SingularAttribute<viewWinesbyMerchants, Boolean> wineDeleted;
}
