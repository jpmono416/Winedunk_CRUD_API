package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.265+0100")
@StaticMetamodel(tblBestOffersbyType.class)
public class tblBestOffersbyType_ {
	public static volatile SingularAttribute<tblBestOffersbyType, Integer> id;
	public static volatile SingularAttribute<tblBestOffersbyType, tblWineTypes> winetypeId;
	public static volatile SingularAttribute<tblBestOffersbyType, tblWines> wineId;
	public static volatile SingularAttribute<tblBestOffersbyType, Integer> positionIndex;
}
