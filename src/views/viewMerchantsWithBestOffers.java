package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewMerchantsWithBestOffers")
public class viewMerchantsWithBestOffers extends Object {

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "merchantId")
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
    @Column(name = "merchantName")
    private String name;
    public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "merchantImageURL")
	private String merchantImageURL;
	public String getMerchantImageURL() { return merchantImageURL; }
	public void setMerchantImageURL(String merchantImageURL) { this.merchantImageURL = merchantImageURL; }
	
	public viewMerchantsWithBestOffers() 
	{
		this.id = null;
        this.name = null;
        this.merchantImageURL = null;
    }
}