package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.552+0100")
@StaticMetamodel(tblUserWinesViewed.class)
public class tblUserWinesViewed_ {
	public static volatile SingularAttribute<tblUserWinesViewed, Integer> id;
	public static volatile SingularAttribute<tblUserWinesViewed, tblUsers> userId;
	public static volatile SingularAttribute<tblUserWinesViewed, tblWines> wineId;
	public static volatile SingularAttribute<tblUserWinesViewed, Date> date;
	public static volatile SingularAttribute<tblUserWinesViewed, Date> timestamp;
}
