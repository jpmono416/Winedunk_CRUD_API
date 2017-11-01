package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tblPFWineTypeMappingTable")
@NamedQueries({
	@NamedQuery(name="TblPFWineTypeMappingTable.findByWineType", query="Select wt FROM TblPFWineTypeMappingTable wt WHERE wt.merchantWineType = :wineType")
})
public class TblPFWineTypeMappingTable {
	@Id
	Integer id;

	@ManyToOne
	@JoinColumn(name="wineTypeId")
	tblWineTypes wineType;

	@Column(name="mechantWineType")
	String merchantWineType;

	public TblPFWineTypeMappingTable(Integer id, tblWineTypes wineType, String merchantWineType) {
		super();
		this.id = id;
		this.wineType = wineType;
		this.merchantWineType = merchantWineType;
	}

	public TblPFWineTypeMappingTable() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public tblWineTypes getWineType() {
		return wineType;
	}

	public void setWineType(tblWineTypes wineType) {
		this.wineType = wineType;
	}

	public String getMerchantWineType() {
		return merchantWineType;
	}

	public void setMerchantWineType(String merchantWineType) {
		this.merchantWineType = merchantWineType;
	}

	@Override
	public String toString() {
		return "TblPFWineTypeMappingTable [id=" + id + ", wineType=" + wineType + ", merchantWineType="
				+ merchantWineType + "]";
	}
}
