package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.594+0100")
@StaticMetamodel(tblWineTypes.class)
public class tblWineTypes_ {
	public static volatile SingularAttribute<tblWineTypes, Integer> id;
	public static volatile SingularAttribute<tblWineTypes, String> name;
	public static volatile SingularAttribute<tblWineTypes, Boolean> deleted;
	public static volatile ListAttribute<tblWineTypes, TblWinesWineType> wines;
	public static volatile ListAttribute<tblWineTypes, tblBestOffersbyType> bestOffersByType;
}
