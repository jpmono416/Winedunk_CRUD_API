package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.781+0100")
@StaticMetamodel(tblGrapeVarieties.class)
public class tblGrapeVarieties_ {
	public static volatile SingularAttribute<tblGrapeVarieties, Integer> id;
	public static volatile SingularAttribute<tblGrapeVarieties, String> name;
	public static volatile SingularAttribute<tblGrapeVarieties, Boolean> deleted;
	public static volatile ListAttribute<tblGrapeVarieties, tblWines> wines;
}
