package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.528+0100")
@StaticMetamodel(tblUsersGoogleLogin.class)
public class tblUsersGoogleLogin_ {
	public static volatile SingularAttribute<tblUsersGoogleLogin, Integer> id;
	public static volatile SingularAttribute<tblUsersGoogleLogin, tblUsers> userId;
	public static volatile SingularAttribute<tblUsersGoogleLogin, String> googleIdToken;
}
