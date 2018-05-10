package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewRecommendedWines")
public class viewRecommendedWines {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
    @Column(name = "name")
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

	@Column(name = "imageURL")
	private String imageURL;
	public String getImageURL() { return imageURL; }
	public void setImageURL(String imageURL) {this.imageURL = imageURL; }
	
	@Column(name = "minimumPrice")
	private Float minimumPrice;
	public Float getMinimumPrice() { return minimumPrice; }
	public void setMinimumPrice(Float minimumPrice) { this.minimumPrice = minimumPrice; }
	
	@Column(name = "previousMaxPrice")
	private Float previousMaxPrice;
	public Float getPreviousMaxPrice() { return previousMaxPrice; }
	public void setPreviousMaxPrice(Float previousMaxPrice) { this.previousMaxPrice = previousMaxPrice; }
	
	@Column(name = "saving")
	private Float saving;
	public Float getSaving() { return saving; }
	public void setSaving(Float saving) { this.saving = saving; }
	
	@Column(name = "percentageOff")
	private Integer percentageOff;
	public Integer getPercentageOff() { return percentageOff; }
	public void setPercentageOff(Integer percentageOff) { this.percentageOff = percentageOff; }
	
	@Column(name = "minimumPriceClicktag")
	private String minimumPriceClicktag;
	public String getMinimumPriceClicktag() { return minimumPriceClicktag; }
	public void setMinimumPriceClicktag(String minimumPriceClicktag) { this.minimumPriceClicktag = minimumPriceClicktag; }
	
	
	public viewRecommendedWines() 
	{
		this.id = null;
        this.name = null;
        this.defaultDescription = null;
        this.shortDescription = null;
        this.imageURL = null;
        this.minimumPrice = null;
        this.previousMaxPrice = null;
        this.saving = null;
        this.percentageOff = null;
        this.minimumPriceClicktag = null;
    }
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", name\" : \"" + name + "\", defaultDescription\" : \"" + defaultDescription
				+ "\", shortDescription\" : \"" + shortDescription + "\", imageURL\" : \"" + imageURL
				+ "\", minimumPrice\" : \"" + minimumPrice 
				+ "\", previousMaxPrice\" : \"" + previousMaxPrice 
				+ "\", saving\" : \"" + saving 
				+ "\", percentageOff\" : \"" + percentageOff 
				+ "\", minimumPriceClicktag\" : \"" + minimumPriceClicktag + "] }";
	}
	
}