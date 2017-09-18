package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.428+0100")
@StaticMetamodel(Tblpfparsingextractionmethod.class)
public class Tblpfparsingextractionmethod_ {
	public static volatile SingularAttribute<Tblpfparsingextractionmethod, Integer> id;
	public static volatile SingularAttribute<Tblpfparsingextractionmethod, String> method;
	public static volatile ListAttribute<Tblpfparsingextractionmethod, Tblpfmerchanthtmlparsing> tblpfmerchanthtmlparsings;
}
