package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblpfmapping database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Tblpfmapping.findAll", query="SELECT t FROM Tblpfmapping t"),
	@NamedQuery(name="Tblpfmapping.findByPFId", query="SELECT t FROM Tblpfmapping t WHERE t.tblpf = :tblpf")
})
public class Tblpfmapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int clicktagColumn;

	private int deliveryCostColumn;

	private int imageURLColumn;

	private int merchantNameColumn;

	private int merchantProductIdColumn;

	private int nameColumn;

	private int partnerProductDescriptionColumn;

	private int partnerProductIdColumn;

	private int priceColumn;

	private int wineTypeColumn;

	private int partnerMerchantIdColumn;
	//bi-directional many-to-one association to Tblpf
	@ManyToOne
	@JoinColumn(name="pfId")
	private Tblpf tblpf;

	public Tblpfmapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClicktagColumn() {
		return this.clicktagColumn;
	}

	public void setClicktagColumn(int clicktagColumn) {
		this.clicktagColumn = clicktagColumn;
	}

	public int getDeliveryCostColumn() {
		return this.deliveryCostColumn;
	}

	public void setDeliveryCostColumn(int deliveryCostColumn) {
		this.deliveryCostColumn = deliveryCostColumn;
	}

	public int getImageURLColumn() {
		return this.imageURLColumn;
	}

	public void setImageURLColumn(int imageURLColumn) {
		this.imageURLColumn = imageURLColumn;
	}

	public int getMerchantNameColumn() {
		return this.merchantNameColumn;
	}

	public void setMerchantNameColumn(int merchantNameColumn) {
		this.merchantNameColumn = merchantNameColumn;
	}

	public int getMerchantProductIdColumn() {
		return this.merchantProductIdColumn;
	}

	public void setMerchantProductIdColumn(int merchantProductIdColumn) {
		this.merchantProductIdColumn = merchantProductIdColumn;
	}

	public int getNameColumn() {
		return this.nameColumn;
	}

	public void setNameColumn(int nameColumn) {
		this.nameColumn = nameColumn;
	}

	public int getPartnerProductDescriptionColumn() {
		return this.partnerProductDescriptionColumn;
	}

	public void setPartnerProductDescriptionColumn(int partnerProductDescriptionColumn) {
		this.partnerProductDescriptionColumn = partnerProductDescriptionColumn;
	}

	public int getPartnerProductIdColumn() {
		return this.partnerProductIdColumn;
	}

	public void setPartnerProductIdColumn(int partnerProductIdColumn) {
		this.partnerProductIdColumn = partnerProductIdColumn;
	}

	public int getPriceColumn() {
		return this.priceColumn;
	}

	public void setPriceColumn(int priceColumn) {
		this.priceColumn = priceColumn;
	}

	public int getWineTypeColumn() {
		return this.wineTypeColumn;
	}

	public void setWineTypeColumn(int wineTypeColumn) {
		this.wineTypeColumn = wineTypeColumn;
	}

	public int getPartnerMerchantIdColumn() {
		return this.partnerMerchantIdColumn;
	}

	public void setPartnerMerchantIdColumn(int partnerMerchantIdColumn) {
		this.partnerMerchantIdColumn = partnerMerchantIdColumn;
	}

	public Tblpf getTblpf() {
		return this.tblpf;
	}

	public void setTblpf(Tblpf tblpf) {
		this.tblpf = tblpf;
	}
}