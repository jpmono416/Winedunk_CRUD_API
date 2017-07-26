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
	
	@Column(name = "destinationURL")
	private String destinationURL;
	public String getDestinationURL() { return destinationURL; }
	public void setDestinationURL(String destinationURL) { this.destinationURL = destinationURL; }
	
	
	public viewRecommendedWines() 
	{
		this.id = null;
        this.name = null;
        this.defaultDescription = null;
        this.shortDescription = null;
        this.imageURL = null;
        this.minimumPrice = null;
        this.destinationURL = null;
    }
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", name\" : \"" + name + "\", defaultDescription\" : \"" + defaultDescription
				+ "\", shortDescription\" : \"" + shortDescription + "\", imageURL\" : \"" + imageURL
				+ "\", minimumPrice\" : \"" + minimumPrice + "\", destinationURL\" : \"" + destinationURL + "] }";
	}
	
}