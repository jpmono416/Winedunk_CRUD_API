package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.458+0100")
@StaticMetamodel(tblRegions.class)
public class tblRegions_ {
	public static volatile SingularAttribute<tblRegions, Integer> id;
	public static volatile SingularAttribute<tblRegions, String> name;
	public static volatile SingularAttribute<tblRegions, Boolean> deleted;
	public static volatile ListAttribute<tblRegions, tblWines> wines;
	public static volatile ListAttribute<tblRegions, tblAppellations> appellations;
	public static volatile SingularAttribute<tblRegions, tblCountries> tblCountries;
}
