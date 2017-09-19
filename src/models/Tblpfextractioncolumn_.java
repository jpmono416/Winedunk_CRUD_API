package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-16T17:39:50.416+0100")
@StaticMetamodel(Tblpfextractioncolumn.class)
public class Tblpfextractioncolumn_ {
	public static volatile SingularAttribute<Tblpfextractioncolumn, Integer> id;
	public static volatile SingularAttribute<Tblpfextractioncolumn, String> columnName;
	public static volatile ListAttribute<Tblpfextractioncolumn, Tblpfmerchanthtmlparsing> tblpfmerchanthtmlparsings;
}
