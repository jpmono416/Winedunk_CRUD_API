package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "tblUserSubscriptions")
public class tblUserSubscriptions {

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
	
	@ManyToOne
    @JoinColumn(name = "newsletterId")
    private tblNewsletters newsletterId;
	public tblNewsletters getNewsletterId() { return newsletterId; }
	public void setNewsletterId(tblNewsletters newsletterId) { this.newsletterId = newsletterId; }

    public tblUserSubscriptions(Integer id) { this.id = id;}
    public tblUserSubscriptions() 
    {
        this.id = null;
        this.newsletterId = null;
        this.userId = null;
    }
}