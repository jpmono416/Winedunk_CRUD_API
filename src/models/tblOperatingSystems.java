package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblOperatingSystems")
public class tblOperatingSystems {

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

    @OneToMany(mappedBy = "operatingSystem", targetEntity = tblDevices.class)
    @JsonBackReference("operatingSystem")
    private List<tblDevices> devices;
    public List<tblDevices> getDevices() { return devices; }
    public void setDevices(List<tblDevices> devices) { this.devices = devices; }


    public tblOperatingSystems(Integer id) { this.id = id;}
    public tblOperatingSystems() 
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.devices = null;
    }
    public tblOperatingSystems(String name) {
        this.name = name;
    }
}
