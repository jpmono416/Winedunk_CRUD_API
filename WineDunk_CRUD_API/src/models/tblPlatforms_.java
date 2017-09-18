package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.446+0100")
@StaticMetamodel(tblPlatforms.class)
public class tblPlatforms_ {
	public static volatile SingularAttribute<tblPlatforms, Integer> id;
	public static volatile SingularAttribute<tblPlatforms, String> name;
	public static volatile SingularAttribute<tblPlatforms, Boolean> deleted;
	public static volatile ListAttribute<tblPlatforms, tblUsers> users;
	public static volatile ListAttribute<tblPlatforms, tblUserSearches> searches;
	public static volatile ListAttribute<tblPlatforms, tblVisitors> visitors;
}
