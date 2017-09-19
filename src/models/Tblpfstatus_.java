package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-16T17:39:50.461+0100")
@StaticMetamodel(Tblpfstatus.class)
public class Tblpfstatus_ {
	public static volatile SingularAttribute<Tblpfstatus, Integer> id;
	public static volatile SingularAttribute<Tblpfstatus, String> name;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> tblpfs1;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> tblpfs2;
	public static volatile ListAttribute<Tblpfstatus, Tblpf> tblpfs3;
	public static volatile ListAttribute<Tblpfstatus, Tblpfstatuschangelog> tblpfstatuschangelogs;
}
