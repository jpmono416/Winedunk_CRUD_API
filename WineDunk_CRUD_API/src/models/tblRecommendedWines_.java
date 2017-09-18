package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.452+0100")
@StaticMetamodel(tblRecommendedWines.class)
public class tblRecommendedWines_ {
	public static volatile SingularAttribute<tblRecommendedWines, Integer> id;
	public static volatile SingularAttribute<tblRecommendedWines, tblWines> wineId;
	public static volatile SingularAttribute<tblRecommendedWines, Date> dateFrom;
	public static volatile SingularAttribute<tblRecommendedWines, Date> dateTo;
	public static volatile SingularAttribute<tblRecommendedWines, String> comments;
}
