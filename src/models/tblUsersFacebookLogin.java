package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblUsersFacebookLogin")
public class tblUsersFacebookLogin {
	
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
	
	// aripe length attribute added
	@Column(name= "fbAccessToken", length = 45)
	private String fbAccessToken;
	public String getFbAccessToken() { return fbAccessToken; }
	public void setFbAccessToken(String fbAccessToken) { this.fbAccessToken = fbAccessToken; }
	
	// aripe length attribute added
	@Column(name= "fbUserID", length = 45)
	private String fbUserID;
	public String getFbUserID() { return fbUserID; }
	public void setFbUserID(String fbUserID) { this.fbUserID = fbUserID; }
	
	public tblUsersFacebookLogin(Integer id) { this.id = id; }
	public tblUsersFacebookLogin()
	{
		this.id = null;
		this.userId = null;
		this.fbAccessToken = null;
		this.fbUserID = null;
	}
	
}