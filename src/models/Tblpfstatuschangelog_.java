package models;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-16T17:39:50.468+0100")
@StaticMetamodel(Tblpfstatuschangelog.class)
public class Tblpfstatuschangelog_ {
	public static volatile SingularAttribute<Tblpfstatuschangelog, TblpfstatuschangelogPK> id;
	public static volatile SingularAttribute<Tblpfstatuschangelog, Timestamp> dateTimeEvent;
	public static volatile SingularAttribute<Tblpfstatuschangelog, Tblpfstatus> tblpfstatus;
	public static volatile SingularAttribute<Tblpfstatuschangelog, Tblpf> tblpf;
}
