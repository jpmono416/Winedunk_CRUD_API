package models;

import models.tblDevices;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


@Entity
@Table(name = "tblBrowsers")
public class tblBrowsers {

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
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    @ManyToMany(mappedBy = "browsers", targetEntity = tblDevices.class)
    @JsonBackReference("browsers")
    private List<tblDevices> devices;
    public List<tblDevices> getDevices() { return devices; }
    public void setDevices(List<tblDevices> devices) { this.devices = devices; }

    public tblBrowsers(Integer id) { this.id = id;}
    public tblBrowsers()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.devices = null;
    }
    public tblBrowsers(String name) {
        this.name = name;
    }
    
	@Override
	public String toString() {
		return "tblBrowsers [id=" + id + ", name=" + name + ", deleted=" + deleted + ", devices=" + devices + "]";
	}
    
}
 