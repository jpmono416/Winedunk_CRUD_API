package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewWines")
public class viewWines extends Object {

	@Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wineId")
    private Integer wineId;
    public Integer getWineId() { return wineId; }
	public void setWineId(Integer id) { this.wineId = id; }

	@Column(name = "wineCountryId")
	private Integer wineCountryId;
	public Integer getWineCountryId() { return wineCountryId; }
	public void setWineCountryId(Integer wineCountryId) { this.wineCountryId = wineCountryId; }
	
    @Column(name = "wineCountryName")
    private String wineCountryName;
    public String getWineCountryName() { return wineCountryName; }
	public void setWineCountryName(String wineCountryName) { this.wineCountryName = wineCountryName; }
	
    @Column(name = "wineRegionId")
    private Integer wineRegionId;
    public Integer getWineRegionId() { return wineRegionId; }
	public void setWineRegionId(Integer wineRegionId) { this.wineRegionId = wineRegionId; }
	
    @Column(name = "wineRegionName")
    private String wineRegionName;
    public String getWineRegionName() { return wineRegionName; }
	public void setWineRegionName(String wineRegionName) { this.wineRegionName = wineRegionName; }
	
    @Column(name = "wineWineryId")
    private Integer wineWineryId;
    public Integer getWineWineryId() { return wineWineryId; }
	public void setWineWineryId(Integer wineWineryId) { this.wineWineryId = wineWineryId; }
	
    @Column(name = "wineWineryName")
    private String wineWineryName;
    public String getWineWineryName() { return wineWineryName; }
	public void setWineWineryName(String wineWineryName) { this.wineWineryName = wineWineryName; }
	
    @Column(name = "wineAppellationId")
    private Integer wineAppellationId;
    public Integer getWineAppellationId() { return wineAppellationId; }
	public void setWineAppellationId(Integer wineAppellationId) { this.wineAppellationId = wineAppellationId; }
	
    @Column(name = "wineAppellationName")
    private String wineAppellationName;
    public String getWineAppellationName() { return wineAppellationName; }
	public void setWineAppellationName(String wineAppellationName) { this.wineAppellationName = wineAppellationName; }
	
    @Column(name = "wineColourId")
    private Integer wineColourId;
    public Integer getWineColourId() { return wineColourId; }
	public void setWineColourId(Integer wineColourId) { this.wineColourId = wineColourId; }
	
    @Column(name = "wineColourName")
    private String wineColourName;
    public String getWineColourName() { return wineColourName; }
	public void setWineColourName(String wineColourName) { this.wineColourName = wineColourName; }
	
    @Column(name = "wineVintage")
    private Integer wineVintage;
    public Integer getWineVintage() { return wineVintage; }
	public void setWineVintage(Integer wineVintage) { this.wineVintage = wineVintage; }
	
    @Column(name = "wineName")
    private String wineName;
    public String getWineName() { return wineName; }
	public void setWineName(String wineName) { this.wineName = wineName; }
	
	@Column(name = "wineShortDescription")
	private String wineShortDescription;
	public String getWineShortDescription() { return wineShortDescription; }
	public void setWineShortDescription(String wineShortDescription) { this.wineShortDescription = wineShortDescription; }
	
    @Column(name = "wineDefaultDescription")
    private String wineDefaultDescription;
    public String getWineDefaultDescription() { return wineDefaultDescription; }
	public void setWineDefaultDescription(String wineDefaultDescription) { this.wineDefaultDescription = wineDefaultDescription; }
	
    @Column(name = "wineBottleSize")
    private Integer wineBottleSize;
    public Integer getWineBottleSize() { return wineBottleSize; }
	public void setWineBottleSize(Integer wineBottleSize) { this.wineBottleSize = wineBottleSize; }
	
    @Column(name = "wineAbv")
    private Float wineAbv;
    public Float getWineAbv() { return wineAbv; }
	public void setWineAbv(Float wineAbv) { this.wineAbv = wineAbv; }
	
    @Column(name = "wineImageURL")
    private String wineImageURL;
    public String getWineImageURL() { return wineImageURL; }
	public void setWineImageURL(String wineImageURL) { this.wineImageURL = wineImageURL; }
	
    @Column(name = "wineClosureId")
    private Integer wineClosureId;
    public Integer getWineClosureId() { return wineClosureId; }
	public void setWineClosureId(Integer wineClosureId) { this.wineClosureId = wineClosureId; }
	
    @Column(name = "wineClosureName")
    private String wineClosureName;
    public String getWineClosureName() { return wineClosureName; }
	public void setWineClosureName(String wineClosureName) { this.wineClosureName = wineClosureName; }
	
    @Column(name = "wineGtin")
    private String wineGtin;
    public String getWineGtin() { return wineGtin; }
	public void setWineGtin(String wineGtin) { this.wineGtin = wineGtin; }
	
	@Column(name = "wineMinimumPrice")
	private Float wineMinimumPrice;
	public Float getWineMinimumPrice() { return wineMinimumPrice; }
	public void setWineMinimumPrice(Float wineMinimumPrice) { this.wineMinimumPrice = wineMinimumPrice; }
	
	@Column(name = "wineDeleted")
    private Boolean wineDeleted;
	public Boolean getWineDeleted() { return wineDeleted; }
	public void setWineDeleted(Boolean wineDeleted) { this.wineDeleted = wineDeleted; }
	
	@Column(name = "avgRating")
	private Float avgRating;
	public Float getAvgRating() { return avgRating; }
	public void setAvgRating(Float avgRating) { this.avgRating = avgRating; }
	
	public viewWines(Integer id) { this.wineId = id; }
	public viewWines() 
	{
        this.wineId = null;
        this.wineCountryId = null; 
        this.wineCountryName = null;
        this.wineRegionId = null;
        this.wineRegionName = null;
        this.wineWineryId = null;
        this.wineWineryName = null;
        this.wineAppellationId = null;
        this.wineAppellationName = null;
        this.wineColourId = null;
        this.wineColourName = null;
        this.wineVintage = null;
        this.wineName = null;
        this.wineShortDescription = null;
        this.wineDefaultDescription = null;
        this.wineBottleSize = null;
        this.wineAbv = null;
        this.wineImageURL = null;
        this.wineClosureId = null;
        this.wineClosureName = null;
        this.wineGtin = null;
        this.wineMinimumPrice = null;
        this.wineDeleted = false;
        this.avgRating = null;
    }
	
	@Override
	public String toString() {
		return "{ \"wineId\" : \"" + wineId + "\" , \"wineCountryId\" : \"" + wineCountryId
				+ "\" , \"wineCountryName\" : \"" + wineCountryName + "\" , \"wineRegionId\" : \"" + wineRegionId
				+ "\" , \"wineRegionName\" : \"" + wineRegionName + "\" , \"wineWineryId\" : \"" + wineWineryId
				+ "\" , \"wineWineryName\" : \"" + wineWineryName + "\" , \"wineAppellationId\" : \""
				+ wineAppellationId + "\" , \"wineAppellationName\" : \"" + wineAppellationName
				+ "\" , \"wineColourId\" : \"" + wineColourId + "\" , \"wineColourName\" : \"" + wineColourName
				+ "\" , \"wineVintage\" : \"" + wineVintage + "\" , \"wineName\" : \"" + wineName
				+ "\" , \"wineShortDescription\" : \"" + wineShortDescription + "\" , \"wineDefaultDescription\" : \""
				+ wineDefaultDescription + "\" , \"wineBottleSize\" : \"" + wineBottleSize + "\" , \"wineAbv\" : \""
				+ wineAbv + "\" , \"wineImageURL\" : \"" + wineImageURL + "\" , \"wineClosureId\" : \"" + wineClosureId
				+ "\" , \"wineClosureName\" : \"" + wineClosureName + "\" , \"wineGtin\" : \"" + wineGtin
				+ "\" , \"wineMinimumPrice\" : \"" + wineMinimumPrice + "\" , \"wineDeleted\" : \"" + wineDeleted
				+ "\" , \"avgRating\" : \"" + avgRating + "\" }";
	}
	
}
