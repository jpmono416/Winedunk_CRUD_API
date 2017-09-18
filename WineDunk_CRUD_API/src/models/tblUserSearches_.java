package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.516+0100")
@StaticMetamodel(tblUserSearches.class)
public class tblUserSearches_ {
	public static volatile SingularAttribute<tblUserSearches, Integer> id;
	public static volatile SingularAttribute<tblUserSearches, tblUsers> user;
	public static volatile SingularAttribute<tblUserSearches, tblPlatforms> platform;
	public static volatile SingularAttribute<tblUserSearches, Date> date;
	public static volatile SingularAttribute<tblUserSearches, Date> timeStamp;
	public static volatile SingularAttribute<tblUserSearches, String> keyWords;
}
