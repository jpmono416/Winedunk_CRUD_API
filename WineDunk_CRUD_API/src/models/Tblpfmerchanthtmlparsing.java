package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblpfmerchanthtmlparsing database table.
 * 
 */
@Entity
@NamedQuery(name="Tblpfmerchanthtmlparsing.findAll", query="SELECT t FROM Tblpfmerchanthtmlparsing t")
public class Tblpfmerchanthtmlparsing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int merchantId;

	private String nameInWeb;

	//bi-directional many-to-one association to Tblpfparsingextractionmethod
	@ManyToOne
	@JoinColumn(name="parsingExtractionMethodsId")
	private Tblpfparsingextractionmethod tblpfparsingextractionmethod;

	//bi-directional many-to-one association to Tblpfextractioncolumn
	@ManyToOne
	@JoinColumn(name="extractionColumnsId")
	private Tblpfextractioncolumn tblpfextractioncolumn;

	public Tblpfmerchanthtmlparsing() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getNameInWeb() {
		return this.nameInWeb;
	}

	public void setNameInWeb(String nameInWeb) {
		this.nameInWeb = nameInWeb;
	}

	public Tblpfparsingextractionmethod getTblpfparsingextractionmethod() {
		return this.tblpfparsingextractionmethod;
	}

	public void setTblpfparsingextractionmethod(Tblpfparsingextractionmethod tblpfparsingextractionmethod) {
		this.tblpfparsingextractionmethod = tblpfparsingextractionmethod;
	}

	public Tblpfextractioncolumn getTblpfextractioncolumn() {
		return this.tblpfextractioncolumn;
	}

	public void setTblpfextractioncolumn(Tblpfextractioncolumn tblpfextractioncolumn) {
		this.tblpfextractioncolumn = tblpfextractioncolumn;
	}

}