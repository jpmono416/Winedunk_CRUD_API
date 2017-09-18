package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.489+0100")
@StaticMetamodel(tblUserFavouriteWines.class)
public class tblUserFavouriteWines_ {
	public static volatile SingularAttribute<tblUserFavouriteWines, Integer> id;
	public static volatile SingularAttribute<tblUserFavouriteWines, tblUsers> userId;
	public static volatile SingularAttribute<tblUserFavouriteWines, tblWines> wineId;
	public static volatile SingularAttribute<tblUserFavouriteWines, Date> addedDate;
	public static volatile SingularAttribute<tblUserFavouriteWines, Date> addedTimestamp;
}
