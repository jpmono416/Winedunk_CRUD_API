package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.516+0100")
@StaticMetamodel(viewWineries.class)
public class viewWineries_ {
	public static volatile SingularAttribute<viewWineries, Integer> id;
	public static volatile SingularAttribute<viewWineries, Integer> countryId;
	public static volatile SingularAttribute<viewWineries, String> countryName;
	public static volatile SingularAttribute<viewWineries, Integer> regionId;
	public static volatile SingularAttribute<viewWineries, String> regionName;
	public static volatile SingularAttribute<viewWineries, Integer> appellationId;
	public static volatile SingularAttribute<viewWineries, String> appellationName;
	public static volatile SingularAttribute<viewWineries, String> name;
	public static volatile SingularAttribute<viewWineries, Boolean> deleted;
}
