package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.655+0100")
@StaticMetamodel(tblBrowsers.class)
public class tblBrowsers_ {
	public static volatile SingularAttribute<tblBrowsers, Integer> id;
	public static volatile SingularAttribute<tblBrowsers, String> name;
	public static volatile SingularAttribute<tblBrowsers, Boolean> deleted;
	public static volatile ListAttribute<tblBrowsers, tblDevices> devices;
}