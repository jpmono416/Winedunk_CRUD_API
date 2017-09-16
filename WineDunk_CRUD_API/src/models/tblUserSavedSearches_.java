package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.272+0100")
@StaticMetamodel(tblUserSavedSearches.class)
public class tblUserSavedSearches_ {
	public static volatile SingularAttribute<tblUserSavedSearches, Integer> id;
	public static volatile SingularAttribute<tblUserSavedSearches, String> name;
	public static volatile SingularAttribute<tblUserSavedSearches, tblUsers> user;
	public static volatile SingularAttribute<tblUserSavedSearches, Date> created;
	public static volatile SingularAttribute<tblUserSavedSearches, String> sqlString;
}
