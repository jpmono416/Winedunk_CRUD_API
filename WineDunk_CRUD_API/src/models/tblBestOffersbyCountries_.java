package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.582+0100")
@StaticMetamodel(tblBestOffersbyCountries.class)
public class tblBestOffersbyCountries_ {
	public static volatile SingularAttribute<tblBestOffersbyCountries, Integer> id;
	public static volatile SingularAttribute<tblBestOffersbyCountries, tblShops> countryId;
	public static volatile SingularAttribute<tblBestOffersbyCountries, tblWines> wineId;
	public static volatile SingularAttribute<tblBestOffersbyCountries, Integer> positionIndex;
}
