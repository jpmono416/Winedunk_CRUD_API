package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.481+0100")
@StaticMetamodel(tblUserEmails.class)
public class tblUserEmails_ {
	public static volatile SingularAttribute<tblUserEmails, Integer> id;
	public static volatile SingularAttribute<tblUserEmails, String> emailAddress;
	public static volatile SingularAttribute<tblUserEmails, tblUsers> userId;
	public static volatile SingularAttribute<tblUserEmails, Integer> numericUserId;
}
