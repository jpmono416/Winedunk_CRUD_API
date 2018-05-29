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
@Table(name = "viewRegionsWithWines")
@NamedQueries({
		@NamedQuery(name="viewRegionsWithWines.findAllByCountryId", query="SELECT t FROM viewRegionsWithWines t WHERE t.countryId = :countryId") 
	})
public class viewRegionsWithWines {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }
    
    @Column(name= "countryId")
    private Integer countryId;
    public Integer getCountryId() { return countryId; }
    
    @Column(name= "name", length = 45)
    private String name;
    public String getName() { return name; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
	
	public viewRegionsWithWines() { }

    @Override
	public String toString() {
		return "tblRegions [id=" + id + ", name=" + name + ", deleted=" + deleted + "]";
	}
}
