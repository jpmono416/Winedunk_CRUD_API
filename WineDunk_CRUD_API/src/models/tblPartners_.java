package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.810+0100")
@StaticMetamodel(tblPartners.class)
public class tblPartners_ {
	public static volatile SingularAttribute<tblPartners, Integer> id;
	public static volatile SingularAttribute<tblPartners, String> name;
	public static volatile SingularAttribute<tblPartners, Boolean> deleted;
	public static volatile SingularAttribute<tblPartners, tblPartnersTypes> partnerTypeId;
	public static volatile ListAttribute<tblPartners, tblPartnersContacts> contacts;
	public static volatile ListAttribute<tblPartners, tblClicks> clicks;
	public static volatile ListAttribute<tblPartners, Tblpf> tblPfs;
}
