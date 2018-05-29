package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewAppellationsWithWines")
@NamedQueries({
	@NamedQuery(name="viewAppellationsWithWines.findAllByRegionId", query="SELECT t FROM viewAppellationsWithWines t WHERE t.regionId = :regionId")
})
public class viewAppellationsWithWines {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }

    @Column(name= "name", length = 45)
    private String name;
    public String getName() { return name; }

    @Column(name= "countryId")
    private Integer countryId;
    public Integer getCountryId() { return countryId; }
    
    @Column(name= "regionId")
    private Integer regionId;
    public Integer getRegionId() { return regionId; }
    
    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    
    public viewAppellationsWithWines() { }

    @Override
	public String toString() {
		return "tblAppellations [id=" + id + ", name=" + name + ", deleted=" + deleted + ", countryId=" + countryId + ", regionId=" + regionId + "]";
	}
}