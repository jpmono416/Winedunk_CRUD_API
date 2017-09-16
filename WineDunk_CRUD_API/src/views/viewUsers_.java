package views;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.497+0100")
@StaticMetamodel(viewUsers.class)
public class viewUsers_ {
	public static volatile SingularAttribute<viewUsers, Integer> id;
	public static volatile SingularAttribute<viewUsers, Integer> countryId;
	public static volatile SingularAttribute<viewUsers, String> countryShortName;
	public static volatile SingularAttribute<viewUsers, String> countryName;
	public static volatile SingularAttribute<viewUsers, Integer> preferredCurrencyId;
	public static volatile SingularAttribute<viewUsers, String> preferredCurrencyName;
	public static volatile SingularAttribute<viewUsers, Integer> preferredTimeZoneId;
	public static volatile SingularAttribute<viewUsers, String> preferredTimeZoneShortName;
	public static volatile SingularAttribute<viewUsers, String> preferredTimeZoneName;
	public static volatile SingularAttribute<viewUsers, String> preferredTimeZoneUtcOffset;
	public static volatile SingularAttribute<viewUsers, Integer> preferredLanguageId;
	public static volatile SingularAttribute<viewUsers, String> preferredLanguageShortName;
	public static volatile SingularAttribute<viewUsers, String> preferredLanguageName;
	public static volatile SingularAttribute<viewUsers, String> name;
	public static volatile SingularAttribute<viewUsers, String> email;
	public static volatile SingularAttribute<viewUsers, String> phoneNumber;
	public static volatile SingularAttribute<viewUsers, Date> DoB;
	public static volatile SingularAttribute<viewUsers, String> loginEmail;
	public static volatile SingularAttribute<viewUsers, String> loginPassword;
	public static volatile SingularAttribute<viewUsers, String> loginToken;
	public static volatile SingularAttribute<viewUsers, String> recoveringPassEmail;
	public static volatile SingularAttribute<viewUsers, String> recoveringPassToken;
	public static volatile SingularAttribute<viewUsers, Boolean> isAdmin;
	public static volatile SingularAttribute<viewUsers, Boolean> deleted;
}
