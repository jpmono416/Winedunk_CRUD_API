package views;

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
@Table(name = "viewUsers_Wines_Ratings")
public class viewUserWinesRatings {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Column(name = "userId")
    private Integer userId;
    public Integer getUserId() { return userId; }
	public void setUserId(Integer userId) { this.userId = userId; }
	
    @Column(name = "wineId")    
    private Integer wineId;
    public Integer getWineId() { return wineId; }
	public void setWineId(Integer wineId) { this.wineId = wineId; }
	
    @Column(name = "wineName")
    private String wineName;
    public String getWineName() { return wineName; }
	public void setWineName(String wineName) { this.wineName = wineName; }
	
    @Column(name = "wineImageURL")
    private String wineImageURL;
    public String getWineImageURL() { return wineImageURL; }
	public void setWineImageURL(String wineImageURL) { this.wineImageURL = wineImageURL; }

	@Column(name = "addedDate")
    private Date addedDate;
    public Date getAddedDate() { return addedDate; }
    public void setAddedDate(Date addedDate) { this.addedDate = addedDate; }

    @Column(name = "addedTimestamp")
    private Date addedTimestamp;
    public Date getAddedTimestamp() { return addedTimestamp; }
    public void setAddedTimestamp(Date addedTimestamp) { this.addedTimestamp = addedTimestamp; }

    @Column(name = "userRating")
    private Integer userRating;
    public Integer getUserRating() { return userRating; }
	public void setUserRating(Integer userRating) { this.userRating = userRating; }

	@Transient
    private Integer numericWineId;
	public Integer getNumericWineId() { return numericWineId; }
	public void setNumericWineId(Integer numericWineId) { this.numericWineId = numericWineId; }
	
	@Transient
	private Integer numericUserId;
    public Integer getNumericUserId() { return numericUserId; } 
    public void setNumericUserId(Integer numericUserId) { this.numericUserId = numericUserId; }

    public viewUserWinesRatings()
    {
        this.id = null;
        this.wineId = null;
        this.wineImageURL = null;
        this.wineName = null;
        this.userId = null;
        this.wineId = null;
        this.addedDate = null;
        this.addedTimestamp = null;
        this.userRating = null;
        this.numericUserId = null;
        this.numericWineId = null;
    }
    
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\" , \"userId\" : \"" + userId + "\" , \"wineId\" : \"" + wineId
				+ "\" , \"addedDate\" : \"" + addedDate + "\" , \"addedTimestamp\" : \"" + addedTimestamp
				+ "\" , \"userRating\" : \"" + userRating + "\" }";
	}
}