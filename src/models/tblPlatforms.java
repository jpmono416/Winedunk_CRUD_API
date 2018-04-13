package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tblPlatforms")
public class tblPlatforms {

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

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    @ManyToMany(mappedBy = "platforms",  targetEntity = tblUsers.class)
    @JsonBackReference("user_platforms")
    private List<tblUsers> users;
    public List<tblUsers> getUsers() { return users; }
    public void setUsers(List<tblUsers> users) { this.users = users; }

    @OneToMany(mappedBy = "platform",  targetEntity = tblUserSearches.class)
    @JsonBackReference("userSearches_platforms")
    private List<tblUserSearches> searches;
    public List<tblUserSearches> getSearches() { return searches; }
    public void setSearches(List<tblUserSearches> searches) { this.searches = searches; }

    @OneToMany(mappedBy = "platform", targetEntity = tblVisitors.class)
    @JsonBackReference("visitor_platform")
    private List<tblVisitors> visitors;
    public List<tblVisitors> getVisitors() { return visitors; }
    public void setVisitors(List<tblVisitors> visitors) { this.visitors = visitors; }

    public tblPlatforms(Integer id) { this.id = id; }
    public tblPlatforms()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.users = null;
    }
    public tblPlatforms(String name) { this.name = name; }
}
