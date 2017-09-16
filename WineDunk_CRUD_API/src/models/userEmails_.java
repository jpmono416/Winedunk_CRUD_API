package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.424+0100")
@StaticMetamodel(userEmails.class)
public class userEmails_ {
	public static volatile SingularAttribute<userEmails, Integer> id;
	public static volatile SingularAttribute<userEmails, tblUsers> userId;
	public static volatile SingularAttribute<userEmails, String> emailAddress;
}
