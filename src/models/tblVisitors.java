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
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tblVisitors")
public class tblVisitors {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@NotNull
	@ManyToOne
    @JoinColumn(name = "platformId", nullable = false)
    private tblPlatforms platform;
    public tblPlatforms getPlatform() { return platform; }
    public void setPlatform(tblPlatforms platform) { this.platform = platform; }

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timeStamp")
    private Date timeStamp;
    public Date getTimeStamp() { return timeStamp; }
    public void setTimeStamp(Date timeStamp) { this.timeStamp = timeStamp; }

    // aripe length attribute added
    @Column(name= "referrerURL", length = 200)
    private String referrerURL;
    public String getReferrerURL() { return referrerURL; }
    public void setReferrerURL(String referrerURL) { this.referrerURL = referrerURL; }

    // aripe length attribute added
    @Column(name= "userAgent", length = 200)
    private String userAgent;
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    public tblVisitors(Integer id) { this.id = id; }
    public tblVisitors()
    {
        this.id = null;
        this.platform = null;
        this.date = null;
        this.timeStamp = null;
        this.referrerURL = null;
    }
}
