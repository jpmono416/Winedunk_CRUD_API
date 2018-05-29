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
@Table(name = "viewCountriesWithWines")
@NamedQueries({
		@NamedQuery(name="viewCountriesWithWines.findAll", query="SELECT t FROM viewCountriesWithWines t") 
	})
public class viewCountriesWithWines {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() { return id; }
    
    @Column(name= "name", length = 45)
    private String name;
    public String getName() { return name; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
	
	public viewCountriesWithWines() { }

    @Override
	public String toString() {
		return "viewCountriesWithWines [id=" + id + ", name=" + name + ", deleted=" + deleted + "]";
	}
}
