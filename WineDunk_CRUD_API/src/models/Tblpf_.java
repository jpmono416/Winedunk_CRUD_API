package models;

import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T14:16:44.968+0100")
@StaticMetamodel(Tblpf.class)
public class Tblpf_ {
	public static volatile SingularAttribute<Tblpf, Integer> id;
	public static volatile SingularAttribute<Tblpf, String> description;
	public static volatile SingularAttribute<Tblpf, String> downloadURL;
	public static volatile SingularAttribute<Tblpf, Integer> importPriority;
	public static volatile SingularAttribute<Tblpf, Timestamp> lastImportation;
	public static volatile SingularAttribute<Tblpf, Timestamp> lastStandardisation;
	public static volatile SingularAttribute<Tblpf, tblPartners> partnerId;
	public static volatile SingularAttribute<Tblpf, Time> startTime;
	public static volatile SingularAttribute<Tblpf, String> timePeriod;
	public static volatile ListAttribute<Tblpf, Tblpfmapping> tblpfmappings;
	public static volatile ListAttribute<Tblpf, Tblpfproduct> tblpfproducts;
	public static volatile SingularAttribute<Tblpf, Tblpfstatus> latestStatus;
	public static volatile SingularAttribute<Tblpf, Tblpfstatus> standardisationStatus;
	public static volatile SingularAttribute<Tblpf, Tblpfstatus> importationStatus;
	public static volatile ListAttribute<Tblpf, Tblpfstatuschangelog> tblpfstatuschangelogs;
	public static volatile SingularAttribute<Tblpf, Boolean> isZip;
	public static volatile SingularAttribute<Tblpf, Boolean> hasHeader;
	public static volatile SingularAttribute<Tblpf, String> separator;
}
