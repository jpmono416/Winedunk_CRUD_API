package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblUserPhoneNumbers")
public class userPhoneNumbers {

	@Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@ManyToOne
    @JoinColumn(name = "userId")
    private tblUsers userId;
    public tblUsers getUserId() { return userId; }
	public void setUserId(tblUsers userId) { this.userId = userId; }
	
	@Column(name = "userPhoneNumber")
    private String userPhoneNumber;
    public String getPhoneNumber() { return userPhoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.userPhoneNumber = phoneNumber; }

    @Transient
    private Integer numericUserId;
    public Integer getNumericUserId() { return numericUserId; }
	public void setNumericUserId(Integer numericUserId) { this.numericUserId = numericUserId; }

    public userPhoneNumbers() 
    {
        this.id = null;
        this.userId = null;
        this.userPhoneNumber = null;
        this.numericUserId = null;
    }
}