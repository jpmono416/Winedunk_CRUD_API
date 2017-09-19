package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewWinesMinimumPrice")
public class viewWinesMinimumPrice extends Object {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wineId")
    private Integer wineId;
    public Integer getWineId() { return wineId; }
	public void setWineId(Integer wineId) { this.wineId = wineId; }
	
	@Column(name = "countryName")
	private String countryName;
    public String getCountryName() { return countryName; }
	public void setCountryName(String countryName) { this.countryName = countryName; }
	
	@Column(name = "regionName")
    private String regionName;
    public String getRegionName() { return regionName; }
	public void setRegionName(String regionName) { this.regionName = regionName; }
	
	@Column(name = "wineryName")
    private String wineryName;
    public String getWineryName() { return wineryName; }
	public void setWineryName(String wineryName) { this.wineryName = wineryName; }
	
	@Column(name = "appellationName")
    private String appellationName;
    public String getAppellationName() { return appellationName; }
	public void setAppellationName(String appellationName) { this.appellationName = appellationName; }
	
	@Column(name = "colourName")
    private String colourName;
    public String getColourName() { return colourName; }
	public void setColourName(String colourName) { this.colourName = colourName; }
	
	@Column(name = "vintage")
    private Integer vintage;
    public Integer getVintage() { return vintage; }
	public void setVintage(Integer vintage) { this.vintage = vintage; }
	
	@Column(name = "name")
    private String name;
    public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "defaultDescription")
    private String defaultDescription;
    public String getDefaultDescription() { return defaultDescription; }
	public void setDefaultDescription(String defaultDescription) { this.defaultDescription = defaultDescription; }
	
	@Column(name = "bottleSize")
    private Integer bottleSize;
    public Integer getBottleSize() { return bottleSize; }
	public void setBottleSize(Integer bottleSize) { this.bottleSize = bottleSize; }
	
	@Column(name = "abv")
	private Float abv;
    public Float getAbv() { return abv; } 
	public void setAbv(Float abv) { this.abv = abv; }
	
	@Column(name = "imageURL")
    private String imageURL;
    public String getImageURL() { return imageURL; }
	public void setImageURL(String imageURL) { this.imageURL = imageURL; }
	
	@Column(name = "closureName")
    private String closureName;
    public String getClosureName() { return closureName; }
	public void setClosureName(String closureName) { this.closureName = closureName; }
	
	@Column(name = "gtin")
    private String gtin;
    public String getGtin() { return gtin; }
	public void setGtin(String gtin) { this.gtin = gtin; }
	
	@Column(name = "wineTypeName")
	private String wineTypeName;
	public String getWineTypeName() { return wineTypeName;  } 
	public void setWineTypeName(String wineType) { this.wineTypeName = wineType; }
	
	@Column(name = "grapeVarietyName")
	private String grapeVarietyName;
	public String getGrapeVarietyName() { return grapeVarietyName; }
	public void setGrapeVarietyName(String grapeVariety) { this.grapeVarietyName = grapeVariety; }
	
	@Column(name = "partnerProductId")
    private String partnerProductId;
    public String getPartnerProductId() { return partnerProductId; }
	public void setPartnerProductId(String partnerProductId) { this.partnerProductId = partnerProductId; }
	
    @Column(name = "partnerMerchantId")
    private Integer partnerMerchantId;
    public Integer getPartnerMerchantId() { return partnerMerchantId; }
	public void setPartnerMerchantId(Integer partnerMerchantId) { this.partnerMerchantId = partnerMerchantId; }
	
    @Column(name = "partnerMerchantProductId")
    private String partnerMerchantProductId;
    public String getPartnerMerchantProductId() { return partnerMerchantProductId; }
	public void setPartnerMerchantProductId(String partnerMerchantProductId) { this.partnerMerchantProductId = partnerMerchantProductId; }

	@Column(name = "minimumPrice")
	private Float minimumPrice;
	public Float getMinimumPrice() { return minimumPrice; }
	public void setMinimumPrice(Float minimumPrice) { this.minimumPrice = minimumPrice; }
	
	@Column(name = "minimumPriceShopName")
	private String minimumPriceShopName;
	public String getMinimumPriceShopName() { return minimumPriceShopName; }
	public void setMinimumPriceShopName(String minimumPriceShopName) { this.minimumPriceShopName = minimumPriceShopName; }
	
	@Column(name = "minimumPriceShopImgURL")
	private String minimumPriceShopImgURL;
	public String getMinimumPriceShopImgURL() { return minimumPriceShopImgURL; }
	public void setMinimumPriceShopImgURL(String minimumPriceShopImgURL) { this.minimumPriceShopImgURL = minimumPriceShopImgURL; }
	
	@Column(name = "minimumPriceDestinationURL")
	private String minimumPriceDestinationURL;
	public String getMinimumPriceDestinationURL() { return minimumPriceDestinationURL; }
	public void setMinimumPriceDestinationURL(String minimumPriceDestinationURL) { this.minimumPriceDestinationURL = minimumPriceDestinationURL; }

	@Column(name = "deleted")
    private Boolean deleted;
	public Boolean getDeleted() { return deleted; }
	public void setDeleted(Boolean deleted) { this.deleted = deleted; }
	
	@Transient
	//@Column(name = "avgRating")
	private Float avgRating;
	public Float getAvgRating() { return avgRating; }
	public void setAvgRating(Float avgRating) { this.avgRating = avgRating; }

	
	public viewWinesMinimumPrice(Integer wineId) { this.wineId = wineId; } 
	public viewWinesMinimumPrice()
	{
		this.wineId = null;
		this.countryName = null;
		this.regionName = null;
		this.wineryName = null;
		this.appellationName = null;
		this.colourName = null;
		this.vintage = null;
		this.name = null;
		this.defaultDescription = null;
		this.bottleSize = null;
		this.abv = null;
		this.imageURL = null;
		this.wineTypeName = null;
		this.grapeVarietyName = null;
		this.closureName = null;
		this.gtin = null;
		this.partnerMerchantId = null;
		this.partnerProductId = null;
		this.partnerMerchantProductId = null;
		this.minimumPrice = null;
		this.minimumPriceShopName = null;
		this.minimumPriceShopImgURL = null;
		this.minimumPriceDestinationURL = null;
		this.deleted = null;
		this.avgRating = null;
		
	}
	
	@Override
	public String toString() {
		return "{ \"wineId\" : \"" + wineId + "\" , \"countryName\" : \"" + countryName + "\" , \"regionName\" : \""
				+ regionName + "\" , \"wineryName\" : \"" + wineryName + "\" , \"appellationName\" : \""
				+ appellationName + "\" , \"colourName\" : \"" + colourName + "\" , \"vintage\" : \"" + vintage
				+ "\" , \"name\" : \"" + name + "\" , \"defaultDescription\" : \"" + defaultDescription
				+ "\" , \"bottleSize\" : \"" + bottleSize + "\" , \"abv\" : \"" + abv + "\" , \"imageURL\" : \""
				+ imageURL + "\" , \"closureName\" : \"" + closureName + "\" , \"gtin\" : \"" + gtin
				+ "\" , \"wineTypeName\" : \"" + wineTypeName + "\" , \"grapeVarietyName\" : \"" + grapeVarietyName
				+ "\" , \"partnerProductId\" : \"" + partnerProductId + "\" , \"partnerMerchantId\" : \""
				+ partnerMerchantId + "\" , \"partnerMerchantProductId\" : \"" + partnerMerchantProductId
				+ "\" , \"minimumPrice\" : \"" + minimumPrice + "\" , \"minimumPriceShopName\" : \""
				+ minimumPriceShopName + "\" , \"minimumPriceShopImgURL\" : \"" + minimumPriceShopImgURL
				+ "\" , \"minimumPriceDestinationURL\" : \"" + minimumPriceDestinationURL + "\" , \"deleted\" : \""
				+ deleted + "\" , \"avgRating\" : \"" + avgRating + "\" }";
	}
}
