package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.336+0100")
@StaticMetamodel(tblUserWinesRatings.class)
public class tblUserWinesRatings_ {
	public static volatile SingularAttribute<tblUserWinesRatings, Integer> id;
	public static volatile SingularAttribute<tblUserWinesRatings, tblUsers> userId;
	public static volatile SingularAttribute<tblUserWinesRatings, tblWines> wineId;
	public static volatile SingularAttribute<tblUserWinesRatings, Date> addedDate;
	public static volatile SingularAttribute<tblUserWinesRatings, Date> addedTimestamp;
	public static volatile SingularAttribute<tblUserWinesRatings, Integer> rating;
}
