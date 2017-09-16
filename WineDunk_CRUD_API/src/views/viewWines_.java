package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.521+0100")
@StaticMetamodel(viewWines.class)
public class viewWines_ {
	public static volatile SingularAttribute<viewWines, Integer> wineId;
	public static volatile SingularAttribute<viewWines, Integer> wineCountryId;
	public static volatile SingularAttribute<viewWines, String> wineCountryName;
	public static volatile SingularAttribute<viewWines, Integer> wineRegionId;
	public static volatile SingularAttribute<viewWines, String> wineRegionName;
	public static volatile SingularAttribute<viewWines, Integer> wineWineryId;
	public static volatile SingularAttribute<viewWines, String> wineWineryName;
	public static volatile SingularAttribute<viewWines, Integer> wineAppellationId;
	public static volatile SingularAttribute<viewWines, String> wineAppellationName;
	public static volatile SingularAttribute<viewWines, Integer> wineColourId;
	public static volatile SingularAttribute<viewWines, String> wineColourName;
	public static volatile SingularAttribute<viewWines, Integer> wineVintage;
	public static volatile SingularAttribute<viewWines, String> wineName;
	public static volatile SingularAttribute<viewWines, String> wineShortDescription;
	public static volatile SingularAttribute<viewWines, String> wineDefaultDescription;
	public static volatile SingularAttribute<viewWines, Integer> wineBottleSize;
	public static volatile SingularAttribute<viewWines, Float> wineAbv;
	public static volatile SingularAttribute<viewWines, String> wineImageURL;
	public static volatile SingularAttribute<viewWines, Integer> wineClosureId;
	public static volatile SingularAttribute<viewWines, String> wineClosureName;
	public static volatile SingularAttribute<viewWines, String> wineGtin;
	public static volatile SingularAttribute<viewWines, Float> wineMinimumPrice;
	public static volatile SingularAttribute<viewWines, Boolean> wineDeleted;
	public static volatile SingularAttribute<viewWines, Float> avgRating;
}
