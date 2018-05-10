package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewWinePriceComparison")
public class viewWinePriceComparison {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@Column(name = "wineId")
	private Integer wineId;
	public Integer getWineId() { return wineId; }
	public void setWineId(Integer wineId) { this.wineId = wineId; }
	
	@Column(name = "shopId")
	private Integer shopId;
	public Integer getShopId() { return shopId; }
	public void setShopId(Integer shopId) { this.shopId = shopId; }
	
	@Column(name = "shopName")
	private String shopName;
	public String getShopName() { return shopName; }
	public void setShopName(String shopName) { this.shopName = shopName; }
	
	@Column(name = "shopImageURL")
	private String shopImageURL;
	public String getShopImageURL() { return shopImageURL; }
	public void setShopImageURL(String shopImageURL) { this.shopImageURL = shopImageURL; }
	
	@Column(name = "partnerId")
	private Integer partnerId;
	public Integer getPartnerId() { return partnerId; }
	public void setPartnerId(Integer partnerId) { this.partnerId = partnerId; }
	
	@Column(name = "partnerProductId")
	private String partnerProductId;
	public String getPartnerProductId() { return partnerProductId; }
	public void setPartnerProductId(String partnerProductId) { this.partnerProductId = partnerProductId; }
	
	@Column(name = "destinationURL")
	private String destinationURL;
	public String getDestinationURL() { return destinationURL; }
	public void setDestinationURL(String destinationURL) { this.destinationURL = destinationURL; }
	
	@Column(name = "oldProductPrice")
	private float oldProductPrice;
	public float getOldProductPrice() { return oldProductPrice; }
	public void setOldProductPrice(float oldProductPrice) { this.oldProductPrice = oldProductPrice; }
	
	@Column(name = "productPrice")
	private float productPrice;
	public float getProductPrice() { return productPrice; }
	public void setProductPrice(float productPrice) { this.productPrice = productPrice; }
	
	@Column(name = "moneySaving")
	private float moneySaving;
	public float getMoneySaving() { if (moneySaving < 0) { return 0; } else { return moneySaving; } }
	public void setMoneySaving(float moneySaving) { this.moneySaving = moneySaving; }
	
	@Column(name = "percentageOff")
	private int percentageOff;
	public int getPercentageOff() { if (percentageOff < 0) { return 0; } else { return percentageOff; } }
	public void setPercentageOff(int percentageOff) { this.percentageOff = percentageOff; }
	
	@Column(name = "deliveringCost")
	private float deliveringCost;
	public float getDeliveringCost() { return deliveringCost; }
	public void setDeliveringCost(float deliveringCost) { this.deliveringCost = deliveringCost; }
	
	@Column(name = "stock")
	private Integer stock;
	public Integer getStock() { return stock; }
	public void setStock(Integer stock) { this.stock = stock; }
	
	public viewWinePriceComparison(Integer id) { this.id = id; } 
	public viewWinePriceComparison() 
	{
        this.id = null; 
        this.wineId = null;
        this.shopId = null;
        this.shopName = null;
        this.partnerId = null;
        this.partnerProductId = null;
        this.shopImageURL = null;
        this.destinationURL = null;
        this.oldProductPrice = 0;
        this.productPrice = 0;
        this.moneySaving = 0;
        this.percentageOff = 0;
        this.deliveringCost = 0;
        this.stock = null;
    }
}
