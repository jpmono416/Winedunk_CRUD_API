package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.227+0100")
@StaticMetamodel(tblUserEmails.class)
public class tblUserEmails_ {
	public static volatile SingularAttribute<tblUserEmails, Integer> id;
	public static volatile SingularAttribute<tblUserEmails, String> emailAddress;
	public static volatile SingularAttribute<tblUserEmails, tblUsers> userId;
	public static volatile SingularAttribute<tblUserEmails, Integer> numericUserId;
}
