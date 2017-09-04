package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tblShops")
public class tblShops {
	
	@Transient
    private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@Column(name= "name")
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Column(name = "logo")
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

	@Column(name= "deleted")
	private Boolean deleted;
	public Boolean isDeleted() {return deleted;}
	public void setDeleted(Boolean deleted) { this.deleted = deleted; }
	
	@OneToMany(mappedBy = "shopId", targetEntity = tblClicks.class)
	@JsonBackReference("shop_click")
	private List<tblClicks> clicks;
    public List<tblClicks> getClicks() { return clicks; }
	public void setClicks(List<tblClicks> clicks) { this.clicks = clicks; }
	
	@OneToMany(mappedBy = "minimumPriceShopId", targetEntity = tblWines.class)
	@JsonBackReference("wine_shop")
	private List<tblWines> wines;
	public List<tblWines> getWines() { return wines; }
	public void setWines(List<tblWines> wines) { this.wines = wines; }
	
	@OneToMany(mappedBy = "merchantId", targetEntity = tblWinesbyMerchants.class)
	@JsonBackReference("winesByMerchant_merchantId")
	private List<tblWinesbyMerchants> winesByMerchant;
	public List<tblWinesbyMerchants> getWinesByMerchant() { return winesByMerchant; }
	public void setWinesByMerchant(List<tblWinesbyMerchants> winesByMerchant) { this.winesByMerchant = winesByMerchant; }
	
	@OneToMany(mappedBy="tblShops", targetEntity = Tblpfmerchanthtmlparsing.class)
	private List<Tblpfmerchanthtmlparsing> parsingByMerchant;
	public List<Tblpfmerchanthtmlparsing> getTblpfmerchanthtmlparsing() { return parsingByMerchant; }
	public void setTblpfmerchanthtmlparsing(List<Tblpfmerchanthtmlparsing> parsingByMerchant) { this.parsingByMerchant = parsingByMerchant; }

	public tblShops(Integer id) { this.id = id; }
	public tblShops(String name) { this.name = name; }
	public tblShops()
	{
		this.id = null;
		this.name = null;
		this.logo = null;
		this.homePage = null;
		this.genericProductPage = null;
		this.deleted = null;
		this.clicks = null;
		this.wines = null;
		this.winesByMerchant = null;
	}
}
