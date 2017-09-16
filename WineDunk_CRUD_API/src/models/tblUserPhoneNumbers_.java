package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.246+0100")
@StaticMetamodel(tblUserPhoneNumbers.class)
public class tblUserPhoneNumbers_ {
	public static volatile SingularAttribute<tblUserPhoneNumbers, Integer> id;
	public static volatile SingularAttribute<tblUserPhoneNumbers, String> userPhoneNumber;
	public static volatile SingularAttribute<tblUserPhoneNumbers, tblUsers> userId;
}
