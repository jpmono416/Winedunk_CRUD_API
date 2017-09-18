package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblpfmerchanthtmlparsing database table.
 * 
 */
@Entity
@Table(name="tblPFMerchantHTMLParsing")
@NamedQueries({
	@NamedQuery(name="Tblpfmerchanthtmlparsing.findAll", query="SELECT t FROM Tblpfmerchanthtmlparsing t"),
	//@NamedQuery(name="Tblpfmerchanthtmlparsing.findByTblShops", query="SELECT t FROM Tblpfmerchanthtmlparsing t WHERE t.tblShops = :tblShops") TODO UNCOMMENT ONCE TABLE EXISTS
})
public class Tblpfmerchanthtmlparsing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="merchantId")
	private tblShops tblShops;

	private String nameInWeb;

	//bi-directional many-to-one association to Tblpfparsingextractionmethod
	@ManyToOne
	@JoinColumn(name="parsingExtractionMethodsId")
	private Tblpfparsingextractionmethod tblpfparsingextractionmethod;

	//bi-directional many-to-one association to Tblpfextractioncolumn
	@ManyToOne
	@JoinColumn(name="extractionColumnsId")
	private Tblpfextractioncolumn tblpfextractioncolumn;

	@ManyToOne
	@JoinColumn(name="downloadmethodId")
	private Tbldownloadmethod  downloadMethod;

	public Tblpfmerchanthtmlparsing() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public tblShops getTblShops() {
		return this.tblShops;
	}

	public void setTblShops(tblShops tblShops) {
		this.tblShops = tblShops;
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

	public Tbldownloadmethod getDownloadMethod() {
		return downloadMethod;
	}

	public void setDownloadMethod(Tbldownloadmethod downloadMethod) {
		this.downloadMethod = downloadMethod;
	}

}