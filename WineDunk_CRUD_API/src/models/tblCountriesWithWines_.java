package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.721+0100")
@StaticMetamodel(tblCountriesWithWines.class)
public class tblCountriesWithWines_ {
	public static volatile SingularAttribute<tblCountriesWithWines, Integer> id;
	public static volatile SingularAttribute<tblCountriesWithWines, tblCountries> countryId;
	public static volatile SingularAttribute<tblCountriesWithWines, Integer> totalWines;
}
