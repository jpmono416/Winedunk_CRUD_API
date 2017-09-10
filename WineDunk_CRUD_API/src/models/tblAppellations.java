package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblAppellations")
@NamedQuery(name="tblAppellations.findByName", query="SELECT t FROM tblAppellations t WHERE t.name = :name")
public class tblAppellations {

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

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    @OneToMany(mappedBy = "appellation", targetEntity = tblWines.class)
    @JsonBackReference
    private List<tblWines> wines;
    public List<tblWines> getWines() { return wines; }
	public void setWines(List<tblWines> wines) { this.wines = wines; }

	@ManyToOne
    @JoinColumn(name = "countryId")
    private tblCountries countryId;
    public tblCountries getCountryId() { return countryId; }
	public void setCountryId(tblCountries countryId) { this.countryId = countryId; }

	@ManyToOne
    @JoinColumn(name = "regionId")
    private tblRegions regionId;
    public tblRegions getRegionId() { return regionId; }
	public void setRegionId(tblRegions regionId) { this.regionId = regionId; }

    public tblAppellations(Integer id) { this.id = id; }
    public tblAppellations(String name) { this.name = name; }
    public tblAppellations()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.wines = null;
        this.countryId = null;
    }
}