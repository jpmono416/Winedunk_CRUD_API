package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.079+0100")
@StaticMetamodel(Tblpfstatus.class)
public class Tblpfstatus_ {
	public static volatile SingularAttribute<Tblpfstatus, Integer> id;
	public static volatile SingularAttribute<Tblpfstatus, String> name;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> latestStatusList;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> standardisationStatusList;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> importationStatusList;
	public static volatile ListAttribute<Tblpfstatus, Tblpfstatuschangelog> tblpfstatuschangelogs;
}
