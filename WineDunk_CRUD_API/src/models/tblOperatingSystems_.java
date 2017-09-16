package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.803+0100")
@StaticMetamodel(tblOperatingSystems.class)
public class tblOperatingSystems_ {
	public static volatile SingularAttribute<tblOperatingSystems, Integer> id;
	public static volatile SingularAttribute<tblOperatingSystems, String> name;
	public static volatile SingularAttribute<tblOperatingSystems, Boolean> deleted;
	public static volatile ListAttribute<tblOperatingSystems, tblDevices> devices;
}
