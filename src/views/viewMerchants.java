package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "viewMerchants")
public class viewMerchants extends Object {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@Column(name = "name")
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "imageURL")
	private String imageURL;
	public String getImageURL() { return imageURL; }
	public void setImageURL(String imageURL) { this.imageURL = imageURL; }
	
	@Column(name = "homePage")
	private String homePage;
	public String getHomePage() { return homePage; }
	public void setHomePage(String homePage) { this.homePage = homePage; }
	
	@Column(name = "genericProductPage")
	private String genericProductPage;
	public String getGenericProductPage() { return genericProductPage; }
	public void setGenericProductPage(String genericProductPage) { this.genericProductPage = genericProductPage; }
	
	@Column(name = "deleted")
	private Boolean deleted;
	public Boolean getDeleted() { return deleted; }
	public void setDeleted(Boolean deleted) { this.deleted = deleted; }
	
	
	public viewMerchants(Integer id) { this.id = id; } 
	public viewMerchants() 
	{
        this.id = null;
        this.name = null;
        this.homePage = null;
        this.imageURL = null;
        this.genericProductPage = null;
        this.deleted = null;
    }
	
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", name\" : \"" + name + "\", imageURL\" : \"" + imageURL
				+ "\", homePage\" : \"" + homePage + "\", genericProductPage\" : \"" + genericProductPage
				+ "\", deleted\" : \"" + deleted + " }";
	}
}
