package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewCountriesWithWinesWines")
public class viewWineries extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
    @Column(name = "countryId")
    private Integer countryId;
    public Integer getCountryId() { return countryId; }
	public void setCountryId(Integer countryId) { this.countryId = countryId; }
	
    @Column(name = "countryName")
    private String countryName;
    public String getCountryName() { return countryName; }
	public void setCountryName(String countryName) { this.countryName = countryName; }
	
    @Column(name = "regionId")
    private Integer regionId;
    public Integer getRegionId() { return regionId; }
	public void setRegionId(Integer regionId) { this.regionId = regionId; }
	
    @Column(name = "regionName")
    private String regionName;
    public String getRegionName() { return regionName; }
	public void setRegionName(String regionName) { this.regionName = regionName; }
	
    @Column(name = "appellationId")
    private Integer appellationId;
    public Integer getAppellationId() { return appellationId; }
	public void setAppellationId(Integer appellationId) { this.appellationId = appellationId; }
	
    @Column(name = "appellationName")
    private String appellationName;
    public String getAppellationName() { return appellationName; }
	public void setAppellationName(String appellationName) { this.appellationName = appellationName; }
	
    @Column(name = "name")
    private String name;
    public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "deleted")
	private Boolean deleted;
	public Boolean isDeleted() { return deleted; }
	public void setDeleted(Boolean deleted) { this.deleted = deleted; }
	
	
	public viewWineries() 
	{
		this.id = null;
        this.name = null;
        this.countryId = null;
        this.countryName = null;
        this.regionId = null;
        this.regionName = null;
        this.appellationId = null;
        this.appellationName = null;
        this.deleted = null;
    }
}
