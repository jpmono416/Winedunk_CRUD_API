package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tblPFProductsBlackList database table.
 * Created by aripe on 24-mar-2018
 */
@Entity
@Table(name="tblPFProductsBlackList")
@NamedQueries({
	@NamedQuery(name="TblPFProductsBlackList.findAll", query="SELECT x FROM TblPFProductsBlackList x"),
	@NamedQuery(name = "TblPFProductsBlackList.findById", query = "SELECT x FROM TblPFProductsBlackList x WHERE x.id = :id"),
	@NamedQuery(name = "TblPFProductsBlackList.findAllByPartnerId", query = "SELECT x FROM TblPFProductsBlackList x WHERE x.partnerId = :partnerId"),
	@NamedQuery(name = "TblPFProductsBlackList.findByPartnerIdPartnerProductId", query = "SELECT x FROM TblPFProductsBlackList x WHERE x.partnerId = :partnerId AND x.partnerProductId = :partnerProductId") })
public class TblPFProductsBlackList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int partnerId;

	private String partnerProductId;

	public TblPFProductsBlackList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerProductId() {
		return this.partnerProductId;
	}

	public void setPartnerProductId(String partnerProductId) {
		this.partnerProductId = partnerProductId;
	}

}