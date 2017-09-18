package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:38.871+0100")
@StaticMetamodel(tblAppellations.class)
public class tblAppellations_ {
	public static volatile SingularAttribute<tblAppellations, Integer> id;
	public static volatile SingularAttribute<tblAppellations, String> name;
	public static volatile SingularAttribute<tblAppellations, Boolean> deleted;
	public static volatile ListAttribute<tblAppellations, tblWines> wines;
	public static volatile SingularAttribute<tblAppellations, tblCountries> countryId;
	public static volatile SingularAttribute<tblAppellations, tblRegions> regionId;
}
