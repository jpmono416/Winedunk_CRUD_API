package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewShops")
public class viewShops extends Object {

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
	
	@Column(name = "Logo")
	private String logo;
	public String getLogo() { return logo; }
	public void setLogo(String logo) { this.logo = logo; }
	
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
	
	
	public viewShops() 
	{
		this.id = null;
        this.name = null;
        this.logo = null;
        this.homePage = null;
        this.genericProductPage = null;
        this.deleted = null;
    }
}
