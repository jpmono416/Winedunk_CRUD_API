package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.051+0100")
@StaticMetamodel(Tblpfmerchanthtmlparsing.class)
public class Tblpfmerchanthtmlparsing_ {
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, Integer> id;
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, tblShops> tblShops;
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, String> nameInWeb;
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, Tblpfparsingextractionmethod> tblpfparsingextractionmethod;
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, Tblpfextractioncolumn> tblpfextractioncolumn;
	public static volatile SingularAttribute<Tblpfmerchanthtmlparsing, Tbldownloadmethod> downloadMethod;
}
