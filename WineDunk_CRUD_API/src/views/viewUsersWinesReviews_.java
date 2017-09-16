package views;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.502+0100")
@StaticMetamodel(viewUsersWinesReviews.class)
public class viewUsersWinesReviews_ {
	public static volatile SingularAttribute<viewUsersWinesReviews, Integer> id;
	public static volatile SingularAttribute<viewUsersWinesReviews, Integer> userId;
	public static volatile SingularAttribute<viewUsersWinesReviews, Integer> wineId;
	public static volatile SingularAttribute<viewUsersWinesReviews, String> reviewDate;
	public static volatile SingularAttribute<viewUsersWinesReviews, String> reviewTimestamp;
	public static volatile SingularAttribute<viewUsersWinesReviews, String> reviewTitle;
	public static volatile SingularAttribute<viewUsersWinesReviews, String> reviewComments;
	public static volatile SingularAttribute<viewUsersWinesReviews, Integer> userRating;
	public static volatile SingularAttribute<viewUsersWinesReviews, String> wineName;
}
