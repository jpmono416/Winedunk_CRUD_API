package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewCountriesWithBestOffers")
public class viewCountriesWithBestOffers extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "countryId")
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
    @Column(name = "countryName")
    private String countryName;
	public String getCountryName() { return countryName; }
	public void setCountryName(String countryName) { this.countryName = countryName; }
	
	public viewCountriesWithBestOffers() 
	{
		this.id = null;
        this.countryName = null;
    }
}
