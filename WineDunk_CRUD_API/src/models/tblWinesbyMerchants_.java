package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.576+0100")
@StaticMetamodel(tblWinesbyMerchants.class)
public class tblWinesbyMerchants_ {
	public static volatile SingularAttribute<tblWinesbyMerchants, Integer> id;
	public static volatile SingularAttribute<tblWinesbyMerchants, tblShops> merchantId;
	public static volatile SingularAttribute<tblWinesbyMerchants, tblWines> wineId;
	public static volatile SingularAttribute<tblWinesbyMerchants, Integer> positionIndex;
}
