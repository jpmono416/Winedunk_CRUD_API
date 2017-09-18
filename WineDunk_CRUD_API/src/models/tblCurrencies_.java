package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.341+0100")
@StaticMetamodel(tblCurrencies.class)
public class tblCurrencies_ {
	public static volatile SingularAttribute<tblCurrencies, Integer> id;
	public static volatile SingularAttribute<tblCurrencies, String> name;
	public static volatile SingularAttribute<tblCurrencies, String> code;
	public static volatile SingularAttribute<tblCurrencies, Integer> decimalPositions;
	public static volatile SingularAttribute<tblCurrencies, Integer> numericCode;
	public static volatile SingularAttribute<tblCurrencies, Boolean> deleted;
	public static volatile ListAttribute<tblCurrencies, tblCountries> countries;
	public static volatile ListAttribute<tblCurrencies, tblUsers> users;
}
