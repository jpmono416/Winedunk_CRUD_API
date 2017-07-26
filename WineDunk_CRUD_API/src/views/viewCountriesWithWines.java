package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewCountriesWithWinesWines")
public class viewCountriesWithWines extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "totalWines")
	private Integer totalWines;
	
	public Integer getTotalWines() { return totalWines; }
	public void setTotalWines(Integer totalWines) { this.totalWines = totalWines; }
	
	
	public viewCountriesWithWines() 
	{
        this.name = null;
        this.totalWines = null;
    }
}
