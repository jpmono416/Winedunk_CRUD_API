package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.557+0100")
@StaticMetamodel(tblVisitors.class)
public class tblVisitors_ {
	public static volatile SingularAttribute<tblVisitors, Integer> id;
	public static volatile SingularAttribute<tblVisitors, tblPlatforms> platform;
	public static volatile SingularAttribute<tblVisitors, Date> date;
	public static volatile SingularAttribute<tblVisitors, Date> timeStamp;
	public static volatile SingularAttribute<tblVisitors, String> referrerURL;
	public static volatile SingularAttribute<tblVisitors, String> userAgent;
}
