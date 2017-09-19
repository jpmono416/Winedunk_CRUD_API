package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-16T17:39:50.441+0100")
@StaticMetamodel(Tblpfparsingextractionmethod.class)
public class Tblpfparsingextractionmethod_ {
	public static volatile SingularAttribute<Tblpfparsingextractionmethod, Integer> id;
	public static volatile SingularAttribute<Tblpfparsingextractionmethod, String> method;
	public static volatile ListAttribute<Tblpfparsingextractionmethod, Tblpfmerchanthtmlparsing> tblpfmerchanthtmlparsings;
}
