package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.511+0100")
@StaticMetamodel(viewWinePriceComparison.class)
public class viewWinePriceComparison_ {
	public static volatile SingularAttribute<viewWinePriceComparison, Integer> id;
	public static volatile SingularAttribute<viewWinePriceComparison, Integer> wineId;
	public static volatile SingularAttribute<viewWinePriceComparison, Integer> shopId;
	public static volatile SingularAttribute<viewWinePriceComparison, String> shopName;
	public static volatile SingularAttribute<viewWinePriceComparison, String> shopImageURL;
	public static volatile SingularAttribute<viewWinePriceComparison, Integer> partnerId;
	public static volatile SingularAttribute<viewWinePriceComparison, String> partnerProductId;
	public static volatile SingularAttribute<viewWinePriceComparison, String> destinationURL;
	public static volatile SingularAttribute<viewWinePriceComparison, Float> productPrice;
	public static volatile SingularAttribute<viewWinePriceComparison, Float> deliveringCost;
	public static volatile SingularAttribute<viewWinePriceComparison, Integer> stock;
}
