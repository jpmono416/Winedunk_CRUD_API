package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "tblUsers_wines_ratings")
public class tblUsers_wines_ratings {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @NotNull
    private tblUsers userId;
    public tblUsers getUserId() { return userId; }
    public void setUserId(tblUsers userId) { this.userId = userId; }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wineId")    
    @NotNull
    private tblWines wineId;
    public tblWines getWineId() { return wineId; }
    public void setWineId(tblWines wineId) { this.wineId = wineId; }

    @Column(name = "addedDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addedDate;
    public Date getAddedDate() { return addedDate; }
    public void setAddedDate(Date addedDate) { this.addedDate = addedDate; }

    @Column(name = "addedTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addedTimestamp;
    public Date getAddedTimestamp() { return addedTimestamp; }
    public void setAddedTimestamp(Date addedTimestamp) { this.addedTimestamp = addedTimestamp; }

    @Column(name = "rating")
    private Integer rating;
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public tblUsers_wines_ratings(tblUsers id) {this.userId = id;}
    public tblUsers_wines_ratings()
    {
        this.id = null;
        this.userId = null;
        this.wineId = null;
        this.addedDate = null;
        this.addedTimestamp = null;
        this.rating = null;
    }
}
