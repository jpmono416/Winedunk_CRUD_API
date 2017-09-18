package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.348+0100")
@StaticMetamodel(tblDevices.class)
public class tblDevices_ {
	public static volatile SingularAttribute<tblDevices, Integer> id;
	public static volatile SingularAttribute<tblDevices, String> name;
	public static volatile SingularAttribute<tblDevices, tblDeviceTypes> deviceType;
	public static volatile SingularAttribute<tblDevices, tblOperatingSystems> operatingSystem;
	public static volatile ListAttribute<tblDevices, tblBrowsers> browsers;
	public static volatile SingularAttribute<tblDevices, String> deviceId;
	public static volatile SingularAttribute<tblDevices, Boolean> deleted;
	public static volatile SingularAttribute<tblDevices, tblUsers> user;
}
