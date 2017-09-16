package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.766+0100")
@StaticMetamodel(tblDeviceTypes.class)
public class tblDeviceTypes_ {
	public static volatile SingularAttribute<tblDeviceTypes, Integer> id;
	public static volatile SingularAttribute<tblDeviceTypes, String> name;
	public static volatile SingularAttribute<tblDeviceTypes, Boolean> deleted;
	public static volatile ListAttribute<tblDeviceTypes, tblDevices> devices;
}
