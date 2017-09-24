package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tblPartnersProducts")
public class tblPartnersProducts {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public tblPartnersProducts setId(Integer id) {
		this.id = id;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "partnerId")
	private tblPartners partnerId;
	public tblPartners getPartnerId() {
		return partnerId;
	}
	public tblPartnersProducts setPartnerId(tblPartners partnerId) {
		this.partnerId = partnerId;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "wineId")
	private tblWines tblWines;
	public tblWines getTblWines() {
		return tblWines;
	}
	public tblPartnersProducts setTblWines(tblWines tblWines) {
		this.tblWines = tblWines;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "shopId")
	private tblShops shopId;
	public tblShops getShopId() {
		return shopId;
	}
	public tblPartnersProducts setShopId(tblShops shopId) {
		this.shopId = shopId;
		return this;
	}

	@Column(name = "partnerProductId")
	private String partnerProductId;
	public String getPartnerProductId() {
		return partnerProductId;
	}
	public tblPartnersProducts setPartnerProductId(String partnerProductId) {
		this.partnerProductId = partnerProductId;
		return this;
	}

	@Column(name = "partnerProductPrice")
	private Float partnerProductPrice;
	public Float getPartnerProductPrice() {
		return partnerProductPrice;
	}
	public tblPartnersProducts setPartnerProductPrice(Float partnerProductPrice) {
		this.partnerProductPrice = partnerProductPrice;
		return this;
	}

	@Column(name = "partnerDestinationUrl")
	private String partnerDestinationUrl;
	public String getPartnerDestinationUrl() {
		return partnerDestinationUrl;
	}
	public tblPartnersProducts setPartnerDestinationUrl(String partnerDestinationUrl) {
		this.partnerDestinationUrl = partnerDestinationUrl;
		return this;
	}

	@Column(name = "partnerMerchantId")
	private String partnerMerchantId;
	public String getPartnerMerchantId() {
		return partnerMerchantId;
	}
	public tblPartnersProducts setPartnerMerchantId(String partnerMerchantId) {
		this.partnerMerchantId = partnerMerchantId;
		return this;
	}

	@Column(name = "partnerMerchantProductId")
	private String partnerMerchantProductId;
	public String getPartnerMerchantProductId() {
		return partnerMerchantProductId;
	}
	public tblPartnersProducts setPartnerMerchantProductId(String partnerMerchantProductId) {
		this.partnerMerchantProductId = partnerMerchantProductId;
		return this;
	}

	@Column(name = "partnerMerchantStock")
	private Integer partnerMerchantStock;
	public Integer getPartnerMerchantStock() {
		return partnerMerchantStock;
	}
	public tblPartnersProducts setPartnerMerchantStock(Integer partnerMerchantStock) {
		this.partnerMerchantStock = partnerMerchantStock;
		return this;
	}

	@Column(name = "partnerMerchantDeliveringCost")
	private Float partnerMerchantDeliveringCost;
	public Float getPartnerMerchantDeliveringCost() {
		return partnerMerchantDeliveringCost;
	}
	public tblPartnersProducts setPartnerMerchantDeliveringCost(Float partnerMerchantDeliveringCost) {
		this.partnerMerchantDeliveringCost = partnerMerchantDeliveringCost;
		return this;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "lastUpdated")
	private Date lastUpdated;
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public tblPartnersProducts setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
		return this;
	}

	@Column(name = "lastMD5")
	private String lastMD5;
	public String getLastMD5() {
		return lastMD5;
	}
	public tblPartnersProducts setLastMD5(String lastMD5) {
		this.lastMD5 = lastMD5;
		return this;
	}

	@Column(name = "deleted")
	private Boolean deleted;
	public Boolean isDeleted() {
		return deleted;
	}
	public tblPartnersProducts setDeleted(Boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "deletedDate")
	private Date deletedDate;
	public Date getDeletedDate() {
		return deletedDate;
	}
	public tblPartnersProducts setDeletedDate(Date timestamp) {
		this.deletedDate = timestamp;
		return this;
	}

	public tblPartnersProducts(Integer id) {
		this.id = id;
	}

	public tblPartnersProducts() {
		this.id = null;
		this.partnerId = null;
		this.tblWines = null;
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

	@Override
	public String toString() {
		return "tblPartnersProducts [id=" + id + ", partnerId=" + partnerId + ", tblWines=" + tblWines + ", shopId="
				+ shopId + ", partnerProductId=" + partnerProductId + ", partnerProductPrice=" + partnerProductPrice
				+ ", partnerDestinationUrl=" + partnerDestinationUrl + ", partnerMerchantId=" + partnerMerchantId
				+ ", partnerMerchantProductId=" + partnerMerchantProductId + ", partnerMerchantStock="
				+ partnerMerchantStock + ", partnerMerchantDeliveringCost=" + partnerMerchantDeliveringCost
				+ ", lastUpdated=" + lastUpdated + ", lastMD5=" + lastMD5 + ", deleted=" + deleted + ", deletedDate="
				+ deletedDate + "]";
	}
}
