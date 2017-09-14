package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblPartners")
public class tblPartners {

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
    private Boolean deleted = false;
    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
    
    @ManyToOne
    @JoinColumn(name = "partnersTypesId")
    private tblPartnersTypes partnerTypeId;
    public tblPartnersTypes getPartnerTypeId() { return partnerTypeId; }
	public void setPartnerTypeId(tblPartnersTypes partnerTypeId) { this.partnerTypeId = partnerTypeId; 	}
	
	@OneToMany(mappedBy = "partner", targetEntity = tblPartnersContacts.class)
	@JsonBackReference("partners_contacts")
	List<tblPartnersContacts> contacts;
	public List<tblPartnersContacts> getContacts() { return contacts; }
	public void setContacts(List<tblPartnersContacts> contacts) { this.contacts = contacts; }
	
	@OneToMany(mappedBy = "partnerId", targetEntity = tblClicks.class)
	@JsonBackReference("partners_clicks")
	private List<tblClicks> clicks;
	public List<tblClicks> getClicks() { return clicks; }
	public void setClicks(List<tblClicks> clicks) { this.clicks = clicks; }

	@OneToMany(mappedBy = "tblPartners", targetEntity = Tblpf.class)
	@JsonBackReference("partners_productfeeds")
	private List<Tblpf> tblPfs;
	public List<Tblpf> getTblpfs() { return tblPfs; }
	public void setTblpfs(List<Tblpf> tblPfs) { this.tblPfs = tblPfs; }

	public tblPartners(Integer id) { this.id = id;}
    public tblPartners()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.partnerTypeId = null;
        
    }
    public tblPartners(String name) { this.name = name; }
    
	@Override
	public String toString() {
		return "tblPartners [id=" + id + ", name=" + name + ", deleted=" + deleted + "]";
	}
    
}
 