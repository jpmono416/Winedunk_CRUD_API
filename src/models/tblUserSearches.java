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

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "tblUserSearches")
public class tblUserSearches {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull
    private tblUsers user;
    public tblUsers getUser() { return user; }
    public void setUser(tblUsers user) { this.user = user; }

    @ManyToOne
    @JoinColumn(name = "platformId", nullable = false)
    @NotNull
    private tblPlatforms platform;
    public tblPlatforms getPlatform() { return platform; }
    public void setPlatform(tblPlatforms platform) { this.platform = platform; }

    @Temporal(TemporalType.DATE)
    private Date date;
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "timeStamp")
    private Date timeStamp;
    public Date getTimeStamp() { return timeStamp; }
    public void setTimeStamp(Date timeStamp) { this.timeStamp = timeStamp; }

    @Column(name = "keyWords")
    private String keyWords;
    public String getKeyWords() { return keyWords; }
    public void setKeyWords(String keyWords) { this.keyWords = keyWords; }

    public tblUserSearches(Integer id) {this.id = id;}
    public tblUserSearches()
    {
        this.id = null;
        this.user = null;
        this.platform = null;
        this.date = null;
        this.timeStamp = null;
        this.keyWords = null;
    }
}
