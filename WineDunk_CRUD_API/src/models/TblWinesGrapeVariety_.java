package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-16T19:42:14.140+0100")
@StaticMetamodel(TblWinesGrapeVariety.class)
public class TblWinesGrapeVariety_ {
	public static volatile SingularAttribute<TblWinesGrapeVariety, Integer> id;
	public static volatile SingularAttribute<TblWinesGrapeVariety, tblWines> wine;
	public static volatile SingularAttribute<TblWinesGrapeVariety, tblGrapeVarieties> grapeVariety;
}
