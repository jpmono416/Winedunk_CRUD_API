package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblWines")
public class tblWines {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

    @ManyToOne
    @JoinColumn(name = "countryId")
    private tblCountries country;
    public tblCountries getCountry() { return country; }
    public void setCountry(tblCountries country) { this.country = country; }

    @ManyToOne
    @JoinColumn(name = "regionId")
    private tblRegions region;
    public tblRegions getRegion() { return region; }
    public void setRegion(tblRegions region) { this.region = region; }
    
    @ManyToOne
    @JoinColumn(name = "appellationId")
    private tblAppellations appellation;
    public tblAppellations getAppellation() { return appellation; }
	public void setAppellation(tblAppellations apellation) { this.appellation = apellation; }
	
	@ManyToOne
    @JoinColumn(name = "colourId")
    private tblColours colour;
	public tblColours getColour() { return colour; }
	public void setColour(tblColours colour) { this.colour = colour; }

	@ManyToMany
	@JoinTable(name = "tblWinesWineTypes",
    joinColumns = @JoinColumn(name = "wineId"),
    inverseJoinColumns = @JoinColumn(name = "typeId"))
	private List<tblWineTypes> wineTypes;
	public List<tblWineTypes> getWineTypes() { return wineTypes; }
	public void setWineTypes(List<tblWineTypes> wineTypes) { this.wineTypes = wineTypes; }

	@ManyToOne
    @JoinColumn(name = "wineryId")
    private tblWineries winery;
    public tblWineries getWinery() { return winery; }
	public void setWinery(tblWineries winery) { this.winery = winery; }
	
	@ManyToOne
    @JoinColumn(name = "closureId")
	tblClosures closure;
	public tblClosures getClosure() { return closure; }
	public void setClosure(tblClosures closure) { this.closure = closure; }
	
	@ManyToMany
    @JoinTable(name = "tblWinesGrapeVarieties",
    joinColumns = @JoinColumn(name = "wineId"),
    inverseJoinColumns = @JoinColumn(name = "grapeVarietyId"))
	private List<tblGrapeVarieties> grapeVarieties;
	public List<tblGrapeVarieties> getGrapeVarieties() { return grapeVarieties; }
	public void setGrapeVarieties(List<tblGrapeVarieties> grapeVarieties) { this.grapeVarieties = grapeVarieties; }

	@Column(name= "name", nullable = false)
    @NotNull
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "defaultDescription")
    private String defaultDescription;
    public String getDefaultDescription() { return defaultDescription; }
	public void setDefaultDescription(String defaultDescription) { this.defaultDescription = defaultDescription; }
	
	@Column(name = "shortDescription")
	private String shortDescription;
	public String getShortDescription() { return shortDescription; }
	public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

	@Column(name = "bottleSize")
    private Float bottleSize;
    public Float getBottleSize() { return bottleSize; }
	public void setBottleSize(Float bottleSize) { this.bottleSize = bottleSize; }
	
    @Column(name = "abv")
    private Float abv;
    public Float getAbv() { return abv; }
	public void setAbv(Float abv) { this.abv = abv; }
	
    @Column(name = "imageURL")
    private String imageURL;
    public String getImageURL() { return imageURL; }
	public void setImageURL(String imageURL) { this.imageURL = imageURL; }
	
    @Column(name = "gtin")
    private String gtin;
    public String getGtin() { return gtin; }
	public void setGtin(String gtin) { this.gtin = gtin; }
	
	@Column(name = "minimumPrice")
	private Float minimumPrice;
	public Float getMinimumPrice() { return minimumPrice; }
	public void setMinimumPrice(Float minimumPrice) { this.minimumPrice = minimumPrice; }
	
	@ManyToOne
	@JoinColumn(name = "minimumPriceShopId")
	private tblShops minimumPriceShopId;
	public tblShops getMinimumPriceShopId() { return minimumPriceShopId; }
	public void setMinimumPriceShopId(tblShops minimumPriceShopId) { this.minimumPriceShopId = minimumPriceShopId; }

	@Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

    @OneToMany(mappedBy = "wineId", targetEntity = tblUserFavouriteWines.class)
    @JsonBackReference("wine_favouriteWine")
    private List<tblUserFavouriteWines> favouriteWines;
    public List<tblUserFavouriteWines> getFavouriteWines() { return favouriteWines; }
    public void setFavouriteWines(List<tblUserFavouriteWines> favouriteWines) { this.favouriteWines = favouriteWines; }

    @OneToMany(mappedBy = "wineId", targetEntity = tblUserWinesRatings.class)
    @JsonBackReference("wine_wineRatings")
    private List<tblUserWinesRatings> wineRatings;
    public List<tblUserWinesRatings> getWineRatings() { return wineRatings; }
    public void setWineRatings(List<tblUserWinesRatings> wineRatings) { this.wineRatings = wineRatings; }

    @OneToMany(mappedBy = "wineId", targetEntity = tblUserWineReviews.class)
    @JsonBackReference("wine_wineReviews")
    private List<tblUserWineReviews> reviews;
    public List<tblUserWineReviews> getReviews() { return reviews; }
    public void setReviews(List<tblUserWineReviews> reviews) { this.reviews = reviews; }

    @OneToMany(mappedBy = "wineId", targetEntity = tblUserWinesViewed.class)
    @JsonBackReference("wine_winesViewed")
    private List<tblUserWinesViewed> winesViewed;
    public List<tblUserWinesViewed> getWinesViewed() { return winesViewed; }
    public void setWinesViewed(List<tblUserWinesViewed> winesViewed) { this.winesViewed = winesViewed; }
    
    @OneToMany(mappedBy = "wineId", targetEntity = tblClicks.class)
    @JsonBackReference("wine_clicks")
    private List<tblClicks> clicks;
    public List<tblClicks> getClicks() { return clicks; }
	public void setClicks(List<tblClicks> clicks) { this.clicks = clicks; }
	
	@OneToMany(mappedBy = "wineId", targetEntity = tblUserPriceAlerts.class)
	@JsonBackReference("wine_priceAlerts")
    private List<tblUserPriceAlerts> userPriceAlerts;
    public List<tblUserPriceAlerts> getUserPriceAlerts() { return userPriceAlerts; }
	public void setUserPriceAlerts(List<tblUserPriceAlerts> userPriceAlerts) { this.userPriceAlerts = userPriceAlerts; }
    
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
    }
    public tblWines(String name) { this.name = name; }
}
