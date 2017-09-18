package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.413+0100")
@StaticMetamodel(Tblpfextractioncolumn.class)
public class Tblpfextractioncolumn_ {
	public static volatile SingularAttribute<Tblpfextractioncolumn, Integer> id;
	public static volatile SingularAttribute<Tblpfextractioncolumn, String> columnName;
	public static volatile ListAttribute<Tblpfextractioncolumn, Tblpfmerchanthtmlparsing> tblpfmerchanthtmlparsings;
}
