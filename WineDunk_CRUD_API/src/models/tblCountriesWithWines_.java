package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.337+0100")
@StaticMetamodel(tblCountriesWithWines.class)
public class tblCountriesWithWines_ {
	public static volatile SingularAttribute<tblCountriesWithWines, Integer> id;
	public static volatile SingularAttribute<tblCountriesWithWines, tblCountries> countryId;
	public static volatile SingularAttribute<tblCountriesWithWines, Integer> totalWines;
}
