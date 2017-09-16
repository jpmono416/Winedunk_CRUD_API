package views;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.506+0100")
@StaticMetamodel(viewUserWinesRatings.class)
public class viewUserWinesRatings_ {
	public static volatile SingularAttribute<viewUserWinesRatings, Integer> id;
	public static volatile SingularAttribute<viewUserWinesRatings, Integer> userId;
	public static volatile SingularAttribute<viewUserWinesRatings, Integer> wineId;
	public static volatile SingularAttribute<viewUserWinesRatings, String> wineName;
	public static volatile SingularAttribute<viewUserWinesRatings, String> wineImageURL;
	public static volatile SingularAttribute<viewUserWinesRatings, Date> addedDate;
	public static volatile SingularAttribute<viewUserWinesRatings, Date> addedTimestamp;
	public static volatile SingularAttribute<viewUserWinesRatings, Integer> userRating;
}
