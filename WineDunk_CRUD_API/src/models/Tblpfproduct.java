package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblpfproducts database table.
 * 
 */
@Entity
@Table(name="tblpfproducts")
@NamedQueries({
	@NamedQuery(name="Tblpfproduct.findAll", query="SELECT t FROM Tblpfproduct t ORDER BY t.merchantName"),
	@NamedQuery(name="Tblpfproduct.findByTblpf", query="SELECT t FROM Tblpfproduct t WHERE t.tblpf = :tblpf ORDER BY t.merchantName"),
	@NamedQuery(name="Tblpfproduct.findByPartnerIdAndMerchantId", query="SELECT t FROM Tblpfproduct t "
																	  + "WHERE t.merchantProductId = :merchantProductId "
																	  	+ "AND t.partnerProductId = :partnerProductId "
																	  + "ORDER BY t.merchantName")
})

public class Tblpfproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String clicktag;

	private float deliveryCost;

	private String imageURL;

	private String merchantName;

	private String merchantProductId;

	private String name;

	@Lob
	private String partnerProductDescription;

	private String partnerProductId;

	private float price;

	private String productType;

	private String productURL;

	//bi-directional many-to-one association to Tblpf
	@ManyToOne
	@JoinColumn(name="pfId")
	private Tblpf tblpf;

	public Tblpfproduct() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClicktag() {
		return this.clicktag;
	}

	public void setClicktag(String clicktag) {
		this.clicktag = clicktag;
	}

	public float getDeliveryCost() {
		return this.deliveryCost;
	}

	public void setDeliveryCost(float deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public String getImageURL() {
		return this.imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantProductId() {
		return this.merchantProductId;
	}

	public void setMerchantProductId(String merchantProductId) {
		this.merchantProductId = merchantProductId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartnerProductDescription() {
		return this.partnerProductDescription;
	}

	public void setPartnerProductDescription(String partnerProductDescription) {
		this.partnerProductDescription = partnerProductDescription;
	}

	public String getPartnerProductId() {
		return this.partnerProductId;
	}

	public void setPartnerProductId(String partnerProductId) {
		this.partnerProductId = partnerProductId;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductURL() {
		return this.productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public Tblpf getTblpf() {
		return this.tblpf;
	}

	public void setTblpf(Tblpf tblpf) {
		this.tblpf = tblpf;
	}

}