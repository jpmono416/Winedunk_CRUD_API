package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.313+0100")
@StaticMetamodel(tblUserSubscriptions.class)
public class tblUserSubscriptions_ {
	public static volatile SingularAttribute<tblUserSubscriptions, Integer> id;
	public static volatile SingularAttribute<tblUserSubscriptions, tblUsers> userId;
	public static volatile SingularAttribute<tblUserSubscriptions, tblNewsletters> newsletterId;
}