package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.305+0100")
@StaticMetamodel(tblUsersGoogleLogin.class)
public class tblUsersGoogleLogin_ {
	public static volatile SingularAttribute<tblUsersGoogleLogin, Integer> id;
	public static volatile SingularAttribute<tblUsersGoogleLogin, tblUsers> userId;
	public static volatile SingularAttribute<tblUsersGoogleLogin, String> googleIdToken;
}
