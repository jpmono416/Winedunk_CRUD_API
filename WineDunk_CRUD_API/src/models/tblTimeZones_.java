package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.472+0100")
@StaticMetamodel(tblTimeZones.class)
public class tblTimeZones_ {
	public static volatile SingularAttribute<tblTimeZones, Integer> id;
	public static volatile SingularAttribute<tblTimeZones, String> name;
	public static volatile SingularAttribute<tblTimeZones, String> shortName;
	public static volatile SingularAttribute<tblTimeZones, Float> utcOffset;
	public static volatile SingularAttribute<tblTimeZones, Boolean> deleted;
	public static volatile ListAttribute<tblTimeZones, tblCountries> countries;
	public static volatile ListAttribute<tblTimeZones, tblUsers> users;
}
