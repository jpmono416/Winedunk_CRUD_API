package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblBestOffersbyCountry")
public class tblBestOffersbyCountries {
	
	@Transient
    private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@ManyToOne
	@JoinColumn(name = "countryId")
	private tblShops countryId;
	public tblShops getCountryId() { return countryId; }
	public void setCountryId(tblShops countryId) { this.countryId = countryId; }

	@ManyToOne
	@JoinColumn(name = "wineId")
	private tblWines wineId;
	public tblWines getBestOfferId() { return wineId; }
	public void setBestOfferId(tblWines wineId) { this.wineId = wineId; }
	
	@Column(name = "positionIndex")
	private Integer positionIndex;
	public Integer getPositionIndex() { return positionIndex; }
	public void setPositionIndex(Integer positionIndex) { this.positionIndex = positionIndex; }
	
	@Transient
	private Integer numericMerchantId;
	public Integer getNumericMerchantId() { return numericMerchantId; }
	public void setNumericMerchantId(Integer numericMerchantId) { this.numericMerchantId = numericMerchantId; }
	
	@Transient
	private Integer numericWineId;
	public Integer getNumericWineId() { return numericWineId; }
	public void setNumericWineId(Integer numericWineId) { this.numericWineId = numericWineId; }
	
	public tblBestOffersbyCountries(Integer id) { this.id = id; }
	public tblBestOffersbyCountries()
	{
		this.id = null;
		this.countryId = null;
		this.wineId = null;
		this.positionIndex = null;
		this.numericMerchantId = null;
		this.numericWineId = null;
	}
}