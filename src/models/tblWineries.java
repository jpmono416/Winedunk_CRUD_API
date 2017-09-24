package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblWineries")
public class tblWineries {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Column(name= "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name="appellationId")
    private Integer appellation;
    public Integer getAppellation() { return appellation; }
	public void setAppellation(Integer appellation) { this.appellation = appellation; }

	@Column(name="regionId")
    private Integer region;
    public Integer getRegion() { return region; }
	public void setRegion(Integer region) { this.region = region; }

	@Column(name="countryId")
    private tblCountries country;
    public tblCountries getCountry() { return country; }
	public void setCountry(tblCountries country) { this.country = country; }
	
    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

    @OneToMany(mappedBy = "winery", targetEntity = tblWines.class)
    @JsonBackReference//("currency")
    private List<tblWines> wines;
    public List<tblWines> getWines() { return wines; }
	public void setWines(List<tblWines> wines) { this.wines = wines; }

    public tblWineries(Integer id) { this.id = id; }
    public tblWineries()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.wines = null;
    }
    public tblWineries(String name) {
        this.name = name;
    }
}