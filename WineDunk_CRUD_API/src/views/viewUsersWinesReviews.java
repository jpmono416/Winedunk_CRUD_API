package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewUsers_Wines_Reviews")
public class viewUsersWinesReviews extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
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
	
	@Column(name = "reviewDate")
	private String reviewDate;
	public String getReviewDate() { return reviewDate; }
	public void setReviewDate(String reviewDate) { this.reviewDate = reviewDate; }

	@Column(name = "reviewTimestamp")
	private String reviewTimestamp;
	public String getReviewTimestamp() { return reviewTimestamp; }
	public void setReviewTimestamp(String reviewTimestamp) { this.reviewTimestamp = reviewTimestamp; }

	@Column(name = "reviewTitle")
	private String reviewTitle;
	public String getReviewTitle() { return reviewTitle; }
	public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }

	@Column(name = "reviewComments")
	private String reviewComments;
	public String getReviewComments() { return reviewComments; }
	public void setReviewComments(String reviewComments) { this.reviewComments = reviewComments; }

	@Column(name = "userRating")
	private Integer userRating;
	public Integer getUserRating() { return userRating; }
	public void setUserRating(Integer userRating) { this.userRating = userRating; }

	@Column(name = "wineName")
	private String wineName;
	public String getWineName() { return wineName; }
	public void setWineName(String wineName) { this.wineName = wineName; }
	
	public viewUsersWinesReviews() 
	{
		this.id = null;
        this.userId = null;
        this.wineId = null;
        this.reviewDate = null;
        this.reviewTitle = null;
        this.reviewComments = null;
        this.userRating = null;
        this.wineName = null;
    }
}
