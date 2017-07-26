package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
@Table(name = "tblUsers_Favourite_Wines")
public class tblUserFavouriteWines {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @ManyToOne
    @JoinColumn(name = "userId")
    private tblUsers userId;
    public tblUsers getUserId() { return userId; }
    public void setUserId(tblUsers userId) { this.userId = userId; }

    @ManyToOne
    @JoinColumn(name = "wineId")
    private tblWines wineId;
    public tblWines getWineId() { return wineId; }
    public void setWineId(tblWines wineId) { this.wineId = wineId; }

    @Column(name = "addedDate")
    @Temporal(TemporalType.DATE)
    private Date addedDate;
    public Date getAddedDate() { return addedDate; }
    public void setAddedDate(Date addedDate) { this.addedDate = addedDate; }

    @Column(name = "addedTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedTimestamp;
    public Date getAddedTimestamp() { return addedTimestamp; }
    public void setAddedTimestamp(Date addedTimestamp) { this.addedTimestamp = addedTimestamp; }
    
    @Transient
    private Integer numericUserId;
    public Integer getNumericUserId() { return numericUserId; }
	public void setNumericUserId(Integer numericUserId) { this.numericUserId = numericUserId; }
	
    @Transient
    private Integer numericWineId;
    public Integer getNumericWineId() { return numericWineId; }
	public void setNumericWineId(Integer numericWineId) { this.numericWineId = numericWineId; }
	
    public tblUserFavouriteWines(tblUsers id) {this.userId = id;}
    public tblUserFavouriteWines()
    {
        this.id = null;
        this.userId = null;
        this.wineId = null;
        this.addedDate = null;
        this.addedTimestamp = null;
        this.numericUserId = null;
        this.numericWineId = null;
    }
}
