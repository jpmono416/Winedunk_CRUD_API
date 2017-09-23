package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblRegions")
public class tblRegions {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id;}
    
    @Column(name= "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    @OneToMany(mappedBy = "region")
    @JsonBackReference("user_country")
    private List<tblWines> wines;
    public List<tblWines> getWines() { return wines; }
    public void setWines(List<tblWines> wines) { this.wines = wines; }

    @ManyToOne
    @JoinColumn(name = "countryId")
    private tblCountries countryId;
    public tblCountries getCountryId() { return countryId; }
	public void setCountryId(tblCountries countryId) { this.countryId = countryId; }
	
	
	public tblRegions(Integer id) { this.id = id; }
    public tblRegions()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.wines = null;
        this.countryId = null;
    }
}
