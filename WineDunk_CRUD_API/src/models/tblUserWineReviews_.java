package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.540+0100")
@StaticMetamodel(tblUserWineReviews.class)
public class tblUserWineReviews_ {
	public static volatile SingularAttribute<tblUserWineReviews, Integer> id;
	public static volatile SingularAttribute<tblUserWineReviews, tblUsers> userId;
	public static volatile SingularAttribute<tblUserWineReviews, tblWines> wineId;
	public static volatile SingularAttribute<tblUserWineReviews, Date> addedDate;
	public static volatile SingularAttribute<tblUserWineReviews, Date> addedTimestamp;
	public static volatile SingularAttribute<tblUserWineReviews, String> title;
	public static volatile SingularAttribute<tblUserWineReviews, String> comments;
}
