package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.440+0100")
@StaticMetamodel(viewAppellations.class)
public class viewAppellations_ {
	public static volatile SingularAttribute<viewAppellations, Integer> id;
	public static volatile SingularAttribute<viewAppellations, String> name;
	public static volatile SingularAttribute<viewAppellations, Integer> countryId;
	public static volatile SingularAttribute<viewAppellations, String> countryName;
	public static volatile SingularAttribute<viewAppellations, Integer> regionId;
	public static volatile SingularAttribute<viewAppellations, String> regionName;
	public static volatile SingularAttribute<viewAppellations, Boolean> deleted;
}
