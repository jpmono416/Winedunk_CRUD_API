package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "tblPartnersProducts")
@NamedQuery(name="tblPartnersProducts.findByPartProdIdAndMercProdId", query="SELECT t FROM tblPartnersProducts t "
																		  + "WHERE t.partnerProductId = :ppId "
																		  	+ "AND t.partnerMerchantProductId = :mpId")
public class tblPartnersProducts {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @ManyToOne
    @JoinColumn(name = "partnerId")
    private tblPartners partnerId;
    public tblPartners getPartnerId() { return partnerId; }
	public void setPartnerId(tblPartners partnerId) { this.partnerId = partnerId; }
	
    @ManyToOne
    @JoinColumn(name = "wineId")
    private tblWines wineId;
    public tblWines getWineId() { return wineId; }
	public void setWineId(tblWines wineId) { this.wineId = wineId; }
	
    @ManyToOne
    @JoinColumn(name = "shopId")
    private tblShops shopId;
    public tblShops getShopId() { return shopId; }
	public void setShopId(tblShops shopId) { this.shopId = shopId; }
	
    @Column(name = "partnerProductId")
    private String partnerProductId;
    public String getPartnerProductId() { return partnerProductId; }
	public void setPartnerProductId(String partnerProductId) { this.partnerProductId = partnerProductId; }
	
    @Column(name = "partnerProductPrice")
    private Float partnerProductPrice;
    public Float getPartnerProductPrice() { return partnerProductPrice; }
	public void setPartnerProductPrice(Float partnerProductPrice) { this.partnerProductPrice = partnerProductPrice; }
	
    @Column(name = "partnerDestinationUrl")
    private String partnerDestinationUrl;
    public String getPartnerDestinationUrl() { return partnerDestinationUrl; }
	public void setPartnerDestinationUrl(String partnerDestinationUrl) { this.partnerDestinationUrl = partnerDestinationUrl; }
	
    @Column(name = "partnerMerchantId")
    private String partnerMerchantId;
    public String getPartnerMerchantId() { return partnerMerchantId; }
	public void setPartnerMerchantId(String partnerMerchantId) { this.partnerMerchantId = partnerMerchantId; }
	
    @Column(name = "partnerMerchantProductId")
    private String partnerMerchantProductId;
    public String getPartnerMerchantProductId() { return partnerMerchantProductId; }
	public void setPartnerMerchantProductId(String partnerMerchantProductId) { this.partnerMerchantProductId = partnerMerchantProductId; }
	
    @Column(name = "partnerMerchantStock")
    private Integer partnerMerchantStock;
    public Integer getPartnerMerchantStock() { return partnerMerchantStock; }
	public void setPartnerMerchantStock(Integer partnerMerchantStock) { this.partnerMerchantStock = partnerMerchantStock; }
	
    @Column(name = "partnerMerchantDeliveringCost")
    private Float partnerMerchantDeliveringCost;
    public Float getPartnerMerchantDeliveringCost() { return partnerMerchantDeliveringCost; }
	public void setPartnerMerchantDeliveringCost(Float partnerMerchantDeliveringCost) { this.partnerMerchantDeliveringCost = partnerMerchantDeliveringCost; }
	
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "lastUpdated")
    private Date lastUpdated;
    public Date getLastUpdated() { return lastUpdated; }
	public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
	
    @Column(name = "lastMD5")
    private String lastMD5;
    public String getLastMD5() { return lastMD5; }
	public void setLastMD5(String lastMD5) { this.lastMD5 = lastMD5; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "deletedDate")
	private Date deletedDate;
	public Date getDeletedDate() { return deletedDate; }
	public void setDeletedDate(Date timestamp) { this.deletedDate = timestamp; }
	
	
    public tblPartnersProducts(Integer id) { this.id = id;}
    public tblPartnersProducts() 
    {
        this.id = null;
        this.partnerId = null;
        this.wineId = null;
        this.shopId = null;
        this.partnerProductId = null;
        this.partnerProductPrice = null;
        this.partnerDestinationUrl = null;
        this.partnerMerchantId = null;
        this.partnerMerchantProductId = null;
        this.partnerMerchantStock = null;
        this.partnerMerchantDeliveringCost = null;
        this.lastUpdated = null;
        this.lastMD5 = null;
        this.deleted = null;
        this.deletedDate = null;
    }
}
