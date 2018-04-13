package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblDevices")
public class tblDevices {

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


    @ManyToOne(targetEntity = tblDeviceTypes.class)
    @JoinColumn(name = "deviceTypeId", referencedColumnName = "id")
    private tblDeviceTypes deviceType;
    public tblDeviceTypes getDeviceType() { return deviceType; }
    public void setDeviceType(tblDeviceTypes deviceType) { this.deviceType = deviceType; }

    @ManyToOne(targetEntity = tblOperatingSystems.class)
    @JoinColumn(name = "operatingSystemId", referencedColumnName = "id")
    private tblOperatingSystems operatingSystem;
    public tblOperatingSystems getOperatingSystem() { return operatingSystem; }
    public void setOperatingSystem(tblOperatingSystems operatingSystem) { this.operatingSystem = operatingSystem; }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tblDevices_Browsers",
                    joinColumns = @JoinColumn(name = "tblDevices_id"),
                    inverseJoinColumns = @JoinColumn(name = "tblBrowsers_id"))
    private List<tblBrowsers> browsers;
    public List<tblBrowsers> getBrowsers() { return browsers; }
    public void setBrowsers(List<tblBrowsers> browsers) { this.browsers = browsers; }

    // aripe length attribute added
    @Column(name= "deviceId", length = 100)
    private String deviceId;
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
    
    @ManyToOne(targetEntity = tblUsers.class)
    @JsonBackReference("user_devices")
    @JoinColumn(name = "userId")
    private tblUsers user;
    public tblUsers getUser() { return user; }
    public void setUser(tblUsers user) { this.user = user; }

    public tblDevices(Integer id) { this.id = id; }
    public tblDevices()
    {
        this.id = null;
        this.name = null;
        this.deviceType = null;
        this.operatingSystem = null;
        this.browsers = null;
        this.deviceId = null;
        this.deleted = null;
    }
    public tblDevices(String name) { this.name = name; }
}
