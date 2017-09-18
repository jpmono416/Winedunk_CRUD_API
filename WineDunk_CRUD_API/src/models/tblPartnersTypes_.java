package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.400+0100")
@StaticMetamodel(tblPartnersTypes.class)
public class tblPartnersTypes_ {
	public static volatile SingularAttribute<tblPartnersTypes, Integer> id;
	public static volatile SingularAttribute<tblPartnersTypes, String> name;
	public static volatile SingularAttribute<tblPartnersTypes, Boolean> deleted;
	public static volatile ListAttribute<tblPartnersTypes, tblPartners> partners;
}
