package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.564+0100")
@StaticMetamodel(tblWineries.class)
public class tblWineries_ {
	public static volatile SingularAttribute<tblWineries, Integer> id;
	public static volatile SingularAttribute<tblWineries, String> name;
	public static volatile SingularAttribute<tblWineries, tblCountries> tblCountry;
	public static volatile SingularAttribute<tblWineries, Integer> regionId;
	public static volatile SingularAttribute<tblWineries, Integer> appellationId;
	public static volatile SingularAttribute<tblWineries, Boolean> deleted;
	public static volatile ListAttribute<tblWineries, tblWines> wines;
}
