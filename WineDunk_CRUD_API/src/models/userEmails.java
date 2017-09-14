package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblUserEmails")
public class userEmails {

	@Id
    private Integer id;
    public Integer getId() { return this.id; }    
    public void setId(Integer id) { this.id = id; }

    @ManyToOne
    @JoinColumn(name = "userId")
    private tblUsers userId;
    public tblUsers getUserId() { return userId; }
	public void setUserId(tblUsers userId) { this.userId = userId; }

	@Column(name = "emailAddress")
    private String emailAddress;
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    @Transient
    private Integer numericUserId;
    public Integer getNumericUserId() { return numericUserId; }
	public void setNumericUserId(Integer numericUserId) { this.numericUserId = numericUserId; }
	
	public userEmails(Integer id) {this.id = id;}
    public userEmails() 
    {
        this.id = null;
        this.userId = null;
        this.numericUserId = null;
        this.emailAddress = null;
    }
}
