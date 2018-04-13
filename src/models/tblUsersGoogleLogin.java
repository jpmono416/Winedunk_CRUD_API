package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblUsersGoogleLogin")
public class tblUsersGoogleLogin {
	
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
	@Column(name= "googleIdToken", length = 45)
	private String googleIdToken;
	public String getGoogleIdToken() { return googleIdToken; }
	public void setGoogleIdToken(String googleIdToken) { this.googleIdToken = googleIdToken; }
	
	
	public tblUsersGoogleLogin(Integer id) { this.id = id; }
	public tblUsersGoogleLogin()
	{
		this.id = null;
		this.userId = null;
		this.googleIdToken = null;
	}
	
}