package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblWineries")
@NamedQuery(name="tblWineries.findByName", query="SELECT t FROM tblWineries t WHERE t.name = :name")
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

    @ManyToOne
    @JoinColumn(name="countryId")
    private tblCountries tblCountry;
    public tblCountries getTblCountries() { return this.tblCountry; }
    public void setTblCountries(tblCountries tblCountries) { this.tblCountry = tblCountries; }

    private Integer regionId;
    public Integer getRegionId() { return this.regionId; }
    public void setRegionId(Integer regionId) { this.regionId = regionId; }

    private Integer appellationId;
    public Integer getAppellationId() { return this.appellationId; }
    public void setAppellationId(Integer appellationId) { this.appellationId = appellationId; }

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
	@Override
	public String toString() {
		return "tblWineries [id=" + id + ", name=" + name + ", tblCountry=" + tblCountry + ", regionId=" + regionId
				+ ", appellationId=" + appellationId + ", deleted=" + deleted + ", wines=" + wines + "]";
	}
}