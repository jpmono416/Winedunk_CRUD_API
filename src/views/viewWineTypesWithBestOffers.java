package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewWineTypesWithBestOffers")
public class viewWineTypesWithBestOffers extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "winetypeId")
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
    @Column(name = "winetypeName")
    private String wineTypeName;
	public String getWineTypeName() { return wineTypeName; }
	public void setWineTypeName(String wineTypeName) { this.wineTypeName = wineTypeName; }
	
	public viewWineTypesWithBestOffers() 
	{
		this.id = null;
        this.wineTypeName = null;
    }
}