package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "tblNewsletters")
public class tblNewsletters {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    // aripe length attribute added
    @Column(name= "name", length = 45)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
	
    @OneToMany(mappedBy = "newsletterId", targetEntity = tblUserSubscriptions.class)
    private List<tblUserSubscriptions> userSubscriptions;
    public List<tblUserSubscriptions> getUserSubscriptions() { return userSubscriptions; }
	public void setUserSubscriptions(List<tblUserSubscriptions> userSubscriptions) { this.userSubscriptions = userSubscriptions; }
	
	
	public tblNewsletters(String name) { this.name = name; }
    public tblNewsletters()
    {
        this.id = null;
        this.name = null;
    }
}