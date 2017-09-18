package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.330+0100")
@StaticMetamodel(tblCountries.class)
public class tblCountries_ {
	public static volatile SingularAttribute<tblCountries, Integer> id;
	public static volatile SingularAttribute<tblCountries, String> name;
	public static volatile SingularAttribute<tblCountries, String> isoAlpha2Code;
	public static volatile SingularAttribute<tblCountries, String> isoAlpha3Code;
	public static volatile SingularAttribute<tblCountries, Integer> isoNumericCode;
	public static volatile SingularAttribute<tblCountries, Boolean> deleted;
	public static volatile ListAttribute<tblCountries, tblCurrencies> currencies;
	public static volatile ListAttribute<tblCountries, tblLanguages> languages;
	public static volatile ListAttribute<tblCountries, tblTimeZones> timeZones;
	public static volatile ListAttribute<tblCountries, tblUsers> users;
	public static volatile ListAttribute<tblCountries, tblRegions> regions;
	public static volatile ListAttribute<tblCountries, TblPFCountryNameMappingTable> tblPFCountryNameMappingTables;
	public static volatile ListAttribute<tblCountries, tblCountriesWithWines> countriesWithWines;
	public static volatile ListAttribute<tblCountries, tblWineries> tblWineries;
	public static volatile ListAttribute<tblCountries, tblAppellations> appellations;
}
