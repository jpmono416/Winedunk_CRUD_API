package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.409+0100")
@StaticMetamodel(TblPFCountryNameMappingTable.class)
public class TblPFCountryNameMappingTable_ {
	public static volatile SingularAttribute<TblPFCountryNameMappingTable, Integer> id;
	public static volatile SingularAttribute<TblPFCountryNameMappingTable, tblCountries> tblCountries;
	public static volatile SingularAttribute<TblPFCountryNameMappingTable, String> merchantCountryName;
}
