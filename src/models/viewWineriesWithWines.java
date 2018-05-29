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
@Table(name = "viewWineriesWithWines")
@NamedQueries({
	@NamedQuery(name="viewWineriesWithWines.findAllByAppellationId", query="SELECT x FROM viewWineriesWithWines x WHERE x.appellationId = :appellationId")
})
public class viewWineriesWithWines {

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
    
    @Column(name= "appellationId")
    private Integer appellationId;
    public Integer getAppellationId() { return appellationId; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

    public viewWineriesWithWines() {}

	@Override
	public String toString() {
		return "tblWineries [id=" + id + ", name=" + name + ", countryId=" + countryId + ", regionId=" + regionId
				+ ", appellationId=" + appellationId + ", deleted=" + deleted + "]";
	}
}