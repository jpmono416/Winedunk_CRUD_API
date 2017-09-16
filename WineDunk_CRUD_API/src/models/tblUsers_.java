package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.262+0100")
@StaticMetamodel(tblUsers.class)
public class tblUsers_ {
	public static volatile SingularAttribute<tblUsers, Integer> id;
	public static volatile SingularAttribute<tblUsers, tblCountries> countryId;
	public static volatile SingularAttribute<tblUsers, tblCurrencies> preferredCurrencyId;
	public static volatile SingularAttribute<tblUsers, tblTimeZones> preferredTimeZoneId;
	public static volatile SingularAttribute<tblUsers, tblLanguages> preferredLanguageId;
	public static volatile SingularAttribute<tblUsers, String> name;
	public static volatile SingularAttribute<tblUsers, String> preferredEmail;
	public static volatile SingularAttribute<tblUsers, String> preferredPhoneNumber;
	public static volatile SingularAttribute<tblUsers, Date> DoB;
	public static volatile SingularAttribute<tblUsers, String> loginEmail;
	public static volatile SingularAttribute<tblUsers, String> loginPassword;
	public static volatile SingularAttribute<tblUsers, String> loginToken;
	public static volatile SingularAttribute<tblUsers, String> recoveringPassEmail;
	public static volatile SingularAttribute<tblUsers, String> recoveringPassToken;
	public static volatile SingularAttribute<tblUsers, Boolean> isAdmin;
	public static volatile SingularAttribute<tblUsers, Boolean> deleted;
	public static volatile ListAttribute<tblUsers, tblUserPhoneNumbers> phoneNumbers;
	public static volatile ListAttribute<tblUsers, tblUserEmails> emailAddresses;
	public static volatile ListAttribute<tblUsers, tblPlatforms> platforms;
	public static volatile ListAttribute<tblUsers, tblDevices> devices;
	public static volatile ListAttribute<tblUsers, tblUserSearches> searches;
	public static volatile ListAttribute<tblUsers, tblUserSavedSearches> savedSearches;
	public static volatile ListAttribute<tblUsers, tblUserFavouriteWines> favouriteWines;
	public static volatile ListAttribute<tblUsers, tblUserWinesRatings> wineRatings;
	public static volatile ListAttribute<tblUsers, tblUserWineReviews> reviews;
	public static volatile ListAttribute<tblUsers, tblUserWinesViewed> winesViewed;
	public static volatile ListAttribute<tblUsers, tblClicks> clicks;
	public static volatile ListAttribute<tblUsers, tblUserSubscriptions> userSubscriptions;
	public static volatile ListAttribute<tblUsers, tblUsersFacebookLogin> facebookAccounts;
	public static volatile ListAttribute<tblUsers, tblUsersGoogleLogin> googleAccounts;
	public static volatile ListAttribute<tblUsers, tblUserPriceAlerts> userPriceAlerts;
}
