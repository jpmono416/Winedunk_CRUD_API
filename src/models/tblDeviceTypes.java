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
@Table(name = "tblDeviceTypes")
public class tblDeviceTypes {

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

    @OneToMany(mappedBy = "deviceType", targetEntity = tblDevices.class)
    @JsonBackReference("deviceType")
    private List<tblDevices> devices;
    public List<tblDevices> getDevices() { return devices; }
    public void setDevices(List<tblDevices> devices) { this.devices = devices; }

    public tblDeviceTypes(Integer id) { this.id = id;}
    public tblDeviceTypes()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.devices = null;
    }
    public tblDeviceTypes(String name) {this.name = name;}
}