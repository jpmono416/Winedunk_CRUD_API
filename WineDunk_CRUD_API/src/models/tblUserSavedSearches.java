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
@Table(name = "tblUserSavedSearches")
public class tblUserSavedSearches {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { 	return id; }
	public void setId(Integer id) { this.id = id; }

	@NotNull
    @Column(name = "name", nullable = false)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull
    private tblUsers user;
    public tblUsers getUser() { return user; }
    public void setUser(tblUsers user) { this.user = user; }

    //@Formats.DateTime(pattern="yyyy-MM-dd")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "created")
    private Date created;
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    @Column(name = "sqlString")
    private String sqlString;
    public String getSqlString() { return sqlString; }
    public void setSqlString(String SQL) { this.sqlString = SQL; }

    public tblUserSavedSearches(Integer id) {this.id = id;}
    public tblUserSavedSearches()
    {
        this.id = null;
        this.user = null;
        this.name = null;
        this.created = null;
        this.sqlString = null;
    }
    public tblUserSavedSearches(String name) { this.name = name; }
}
