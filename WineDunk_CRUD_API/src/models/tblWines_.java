package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="Dali", date="2017-09-18T14:16:45.729+0100")
=======
@Generated(value="Dali", date="2017-09-17T11:31:56.243+0100")
>>>>>>> branch 'master' of https://github.com/jpmono416/Winedunk_CRUD_API.git
@StaticMetamodel(tblWines.class)
public class tblWines_ {
	public static volatile SingularAttribute<tblWines, Integer> id;
	public static volatile SingularAttribute<tblWines, tblCountries> country;
	public static volatile SingularAttribute<tblWines, tblRegions> region;
	public static volatile SingularAttribute<tblWines, tblAppellations> appellation;
	public static volatile SingularAttribute<tblWines, tblColours> colour;
	public static volatile ListAttribute<tblWines, TblWinesWineType> tblWinesWineType;
	public static volatile SingularAttribute<tblWines, tblWineries> winery;
	public static volatile SingularAttribute<tblWines, tblClosures> closure;
	public static volatile ListAttribute<tblWines, TblWinesGrapeVariety> tblWinesGrapeVariety;
	public static volatile SingularAttribute<tblWines, String> name;
	public static volatile SingularAttribute<tblWines, String> defaultDescription;
	public static volatile SingularAttribute<tblWines, String> shortDescription;
	public static volatile SingularAttribute<tblWines, Float> bottleSize;
	public static volatile SingularAttribute<tblWines, Integer> vintage;
	public static volatile SingularAttribute<tblWines, Float> abv;
	public static volatile SingularAttribute<tblWines, String> imageURL;
	public static volatile SingularAttribute<tblWines, String> gtin;
	public static volatile SingularAttribute<tblWines, Float> minimumPrice;
	public static volatile SingularAttribute<tblWines, tblShops> minimumPriceShopId;
	public static volatile SingularAttribute<tblWines, Boolean> deleted;
	public static volatile ListAttribute<tblWines, tblUserFavouriteWines> favouriteWines;
	public static volatile ListAttribute<tblWines, tblUserWinesRatings> wineRatings;
	public static volatile ListAttribute<tblWines, tblUserWineReviews> reviews;
	public static volatile ListAttribute<tblWines, tblUserWinesViewed> winesViewed;
	public static volatile ListAttribute<tblWines, tblClicks> clicks;
	public static volatile ListAttribute<tblWines, tblUserPriceAlerts> userPriceAlerts;
	public static volatile ListAttribute<tblWines, tblRecommendedWines> recommendedWines;
	public static volatile ListAttribute<tblWines, tblWinesbyMerchants> winesByMerchant;
	public static volatile ListAttribute<tblWines, tblBestOffersbyType> bestOffersByType;
	public static volatile SingularAttribute<tblWines, Float> avgRating;
}
