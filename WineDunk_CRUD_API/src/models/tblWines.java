package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblWines")
@NamedQueries({ @NamedQuery(name = "tblWines.FindByGtin", query = "SELECT t FROM tblWines t WHERE t.gtin = :gtin"),
		@NamedQuery(name = "tblWines.FindByNameBottleAndVintage", query = "SELECT t FROM tblWines t "
				+ "WHERE t.name = :name " + "AND t.bottleSize = :bottleSize " + "AND t.vintage = :vintage") })
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class tblWines {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public tblWines setId(Integer id) {
		this.id = id;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "countryId")
	private tblCountries country;

	public tblCountries getCountry() {
		return country;
	}

	public tblWines setCountry(tblCountries country) {
		this.country = country;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "regionId")
	private tblRegions region;

	public tblRegions getRegion() {
		return region;
	}

	public tblWines setRegion(tblRegions region) {
		this.region = region;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "appellationId")
	private tblAppellations appellation;

	public tblAppellations getAppellation() {
		return appellation;
	}

	public tblWines setAppellation(tblAppellations apellation) {
		this.appellation = apellation;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "colourId")
	private tblColours colour;

	public tblColours getColour() {
		return colour;
	}

	public tblWines setColour(tblColours colour) {
		this.colour = colour;
		return this;
	}

	@OneToMany(mappedBy = "tblWines", targetEntity=TblWinesWineType.class)
	@JsonBackReference
	private List<TblWinesWineType> tblWinesWineType;
	public List<TblWinesWineType> getTblWinesWineType() {
		return tblWinesWineType;
	}
	public tblWines setTblWinesWineType(List<TblWinesWineType> tblWinesWineType) {
		this.tblWinesWineType = tblWinesWineType;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "wineryId")
	private tblWineries winery;

	public tblWineries getWinery() {
		return winery;
	}

	public tblWines setWinery(tblWineries winery) {
		this.winery = winery;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "closureId")
	tblClosures closure;

	public tblClosures getClosure() {
		return closure;
	}

	public tblWines setClosure(tblClosures closure) {
		this.closure = closure;
		return this;
	}

	@OneToMany(mappedBy="wine", targetEntity=TblWinesGrapeVariety.class)
	private List<TblWinesGrapeVariety> tblWinesGrapeVariety;

	public List<TblWinesGrapeVariety> getTblWinesGrapeVariety() {
		return tblWinesGrapeVariety;
	}

	public void setTblWinesGrapeVariety(List<TblWinesGrapeVariety> tblWinesGrapeVariety) {
		this.tblWinesGrapeVariety = tblWinesGrapeVariety;
	}

	@Column(name = "name", nullable = false)
	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public tblWines setName(String name) {
		this.name = name;
		return this;
	}

	@Column(name = "defaultDescription")
	private String defaultDescription;

	public String getDefaultDescription() {
		return defaultDescription;
	}

	public tblWines setDefaultDescription(String defaultDescription) {
		this.defaultDescription = defaultDescription;
		return this;
	}

	@Column(name = "shortDescription")
	private String shortDescription;

	public String getShortDescription() {
		return shortDescription;
	}

	public tblWines setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
		return this;
	}

	@Column(name = "bottleSize")
	private Float bottleSize;

	public Float getBottleSize() {
		return bottleSize;
	}

	public tblWines setBottleSize(Float bottleSize) {
		this.bottleSize = bottleSize;
		return this;
	}

	@Column(name = "vintage")
	private Integer vintage;

	public Integer getVintage() {
		return vintage;
	}

	public tblWines setVintage(Integer vintage) {
		this.vintage = vintage;
		return this;
	}

	@Column(name = "abv")
	private Float abv;

	public Float getAbv() {
		return abv;
	}

	public tblWines setAbv(Float abv) {
		this.abv = abv;
		return this;
	}

	@Column(name = "imageURL")
	private String imageURL;

	public String getImageURL() {
		return imageURL;
	}

	public tblWines setImageURL(String imageURL) {
		this.imageURL = imageURL;
		return this;
	}

	@Column(name = "gtin")
	private String gtin;

	public String getGtin() {
		return gtin;
	}

	public tblWines setGtin(String gtin) {
		this.gtin = gtin;
		return this;
	}

	@Column(name = "minimumPrice")
	private Float minimumPrice;

	public Float getMinimumPrice() {
		return minimumPrice;
	}

	public tblWines setMinimumPrice(Float minimumPrice) {
		this.minimumPrice = minimumPrice;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "minimumPriceShopId")
	private tblShops minimumPriceShopId;

	public tblShops getMinimumPriceShopId() {
		return minimumPriceShopId;
	}

	public tblWines setMinimumPriceShopId(tblShops minimumPriceShopId) {
		this.minimumPriceShopId = minimumPriceShopId;
		return this;
	}

	@Column(name = "deleted")
	private Boolean deleted;

	public Boolean isDeleted() {
		return deleted;
	}

	public tblWines setDeleted(Boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblUserFavouriteWines.class)
	@JsonBackReference("wine_favouriteWine")
	private List<tblUserFavouriteWines> favouriteWines;

	public List<tblUserFavouriteWines> getFavouriteWines() {
		return favouriteWines;
	}

	public void setFavouriteWines(List<tblUserFavouriteWines> favouriteWines) {
		this.favouriteWines = favouriteWines;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblUserWinesRatings.class)
	@JsonBackReference("wine_wineRatings")
	private List<tblUserWinesRatings> wineRatings;

	public List<tblUserWinesRatings> getWineRatings() {
		return wineRatings;
	}

	public void setWineRatings(List<tblUserWinesRatings> wineRatings) {
		this.wineRatings = wineRatings;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblUserWineReviews.class)
	@JsonBackReference("wine_wineReviews")
	private List<tblUserWineReviews> reviews;

	public List<tblUserWineReviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<tblUserWineReviews> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblUserWinesViewed.class)
	@JsonBackReference("wine_winesViewed")
	private List<tblUserWinesViewed> winesViewed;

	public List<tblUserWinesViewed> getWinesViewed() {
		return winesViewed;
	}

	public void setWinesViewed(List<tblUserWinesViewed> winesViewed) {
		this.winesViewed = winesViewed;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblClicks.class)
	@JsonBackReference("wine_clicks")
	private List<tblClicks> clicks;

	public List<tblClicks> getClicks() {
		return clicks;
	}

	public void setClicks(List<tblClicks> clicks) {
		this.clicks = clicks;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblUserPriceAlerts.class)
	@JsonBackReference("wine_priceAlerts")
	private List<tblUserPriceAlerts> userPriceAlerts;

	public List<tblUserPriceAlerts> getUserPriceAlerts() {
		return userPriceAlerts;
	}

	public void setUserPriceAlerts(List<tblUserPriceAlerts> userPriceAlerts) {
		this.userPriceAlerts = userPriceAlerts;
	}

	@OneToMany(mappedBy = "wineId", targetEntity = tblRecommendedWines.class)
    @JsonBackReference("RecommendedWines")
    private List<tblRecommendedWines> recommendedWines;
    public List<tblRecommendedWines> getRecommendedWines() { return recommendedWines; }
    public void setRecommendedWines(List<tblRecommendedWines> recommendedWines) { this.recommendedWines = recommendedWines; }
    
    @OneToMany(mappedBy = "wineId", targetEntity = tblWinesbyMerchants.class)
    @JsonBackReference("wine_winesByMerchant")
    private List<tblWinesbyMerchants> winesByMerchant;
	public List<tblWinesbyMerchants> getWinesByMerchant() { return winesByMerchant; }
	public void setWinesByMerchant(List<tblWinesbyMerchants> winesByMerchant) { this.winesByMerchant = winesByMerchant; }
	
	@OneToMany(mappedBy = "wineId", targetEntity = tblBestOffersbyType.class)
	@JsonBackReference("wine_bestOffersByType")
	private List<tblBestOffersbyType> bestOffersByType;
	public List<tblBestOffersbyType> getBestOffersByType() { return bestOffersByType; }
	public void setBestOffersByType(List<tblBestOffersbyType> bestOffersByType) { this.bestOffersByType = bestOffersByType; }

	@Column(name = "avgRating")
	private Float avgRating;
	public Float getAvgRating() { return avgRating; }
	public void setAvgRating(Float avgRating) { this.avgRating = avgRating; }
	
    public tblWines(Integer id) { this.id = id; }
    public tblWines()
    {
        this.id = null;
        this.country = null;
        this.name = null;
        this.defaultDescription = null;
        this.bottleSize = null;
        this.abv = null;
        this.gtin = null;
        this.imageURL = null;
        this.deleted = false;
        this.favouriteWines = null;
        this.recommendedWines = null;
        this.reviews = null;
        this.winesViewed = null;
        this.clicks = null;
        this.minimumPrice = null;
        this.minimumPriceShopId = null;
        this.userPriceAlerts = null;
        this.winesByMerchant = null;
        this.bestOffersByType = null;
        this.avgRating = null;
    }
    public tblWines(String name) { this.name = name; }
}