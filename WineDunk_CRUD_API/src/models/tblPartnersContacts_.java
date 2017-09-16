package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.819+0100")
@StaticMetamodel(tblPartnersContacts.class)
public class tblPartnersContacts_ {
	public static volatile SingularAttribute<tblPartnersContacts, Integer> id;
	public static volatile SingularAttribute<tblPartnersContacts, tblPartners> partner;
	public static volatile SingularAttribute<tblPartnersContacts, String> name;
	public static volatile SingularAttribute<tblPartnersContacts, String> position;
	public static volatile SingularAttribute<tblPartnersContacts, String> primaryEmail;
	public static volatile SingularAttribute<tblPartnersContacts, String> primaryPhoneNumber;
	public static volatile SingularAttribute<tblPartnersContacts, String> secondaryEmail;
	public static volatile SingularAttribute<tblPartnersContacts, String> secondaryPhoneNumber;
	public static volatile SingularAttribute<tblPartnersContacts, Boolean> deleted;
}
