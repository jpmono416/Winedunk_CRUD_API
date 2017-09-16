package models;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the tblpfs database table.
 * 
 */
@Entity
@Table(name = "tblPFs")
@NamedQuery(name = "Tblpf.findAll", query = "SELECT t FROM Tblpf t")
public class Tblpf implements Serializable {
	private static final long serialVersionUID = 1L;

	public Tblpf() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	private String description;
	public String getDescription() {
		return this.description;
	}
	public Tblpf setDescription(String description) {
		this.description = description;
		return this;
	}

	private String downloadURL;
	public String getDownloadURL() {
		return this.downloadURL;
	}
	public Tblpf setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
		return this;
	}

	private Integer importPriority;
	public Integer getImportPriority() {
		return this.importPriority;
	}
	public Tblpf setImportPriority(Integer importPriority) {
		this.importPriority = importPriority;
		return this;
	}

	private Timestamp lastImportation;
	public Timestamp getLastImportation() {
		return this.lastImportation;
	}
	public Tblpf setLastImportation(Timestamp lastImportation) {
		this.lastImportation = lastImportation;
		return this;
	}

	private Timestamp lastStandardisation;
	public Timestamp getLastStandardisation() {
		return this.lastStandardisation;
	}
	public Tblpf setLastStandardisation(Timestamp lastStandardisation) {
		this.lastStandardisation = lastStandardisation;
		return this;
	}

	@ManyToOne
	@JoinColumn(name = "partnerId")
	@JsonBackReference
	private tblPartners partnerId;
	public tblPartners getPartnerId() {
		return this.partnerId;
	}
	public Tblpf setPartnerId(tblPartners partnerId) {
		this.partnerId = partnerId;
		return this;
	}

	private Time startTime;
	public Time getStartTime() {
		return this.startTime;
	}
	public Tblpf setStartTime(Time startTime) {
		this.startTime = startTime;
		return this;
	}

	private String timePeriod;
	public String getTimePeriod() {
		return this.timePeriod;
	}
	public Tblpf setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
		return this;
	}

	// bi-directional many-to-one association to Tblpfmapping
	@OneToMany(mappedBy = "tblpf")
	@JsonBackReference("mapping_tblpf")
	private List<Tblpfmapping> tblpfmappings;
	public List<Tblpfmapping> getTblpfmappings() {
		return this.tblpfmappings;
	}
	public Tblpf setTblpfmappings(List<Tblpfmapping> tblpfmappings) {
		this.tblpfmappings = tblpfmappings;
		return this;
	}
	public Tblpfmapping addTblpfmapping(Tblpfmapping tblpfmapping) {
		getTblpfmappings().add(tblpfmapping);
		tblpfmapping.setTblpf(this);

		return tblpfmapping;
	}
	public Tblpfmapping removeTblpfmapping(Tblpfmapping tblpfmapping) {
		getTblpfmappings().remove(tblpfmapping);
		tblpfmapping.setTblpf(null);

		return tblpfmapping;
	}

	// bi-directional many-to-one association to Tblpfproduct
	@OneToMany(mappedBy = "tblpf", targetEntity=Tblpfproduct.class)
	@JsonBackReference("product_tblpf")
	private List<Tblpfproduct> tblpfproducts;
	public List<Tblpfproduct> getTblpfproducts() {
		return this.tblpfproducts;
	}
	public void setTblpfproducts(List<Tblpfproduct> tblpfproducts) {
		this.tblpfproducts = tblpfproducts;
	}
	public Tblpfproduct addTblpfproduct(Tblpfproduct tblpfproduct) {
		getTblpfproducts().add(tblpfproduct);
		tblpfproduct.setTblpf(this);

		return tblpfproduct;
	}
	public Tblpfproduct removeTblpfproduct(Tblpfproduct tblpfproduct) {
		getTblpfproducts().remove(tblpfproduct);
		tblpfproduct.setTblpf(null);

		return tblpfproduct;
	}

	// bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name = "latestStatus")
	private Tblpfstatus latestStatus;
	public Tblpfstatus getLatestStatus() {
		return this.latestStatus;
	}
	public Tblpf setLatestStatus(Tblpfstatus latestStatus) {
		this.latestStatus = latestStatus;
		return this;
	}

	// bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name = "standardisationStatus")
	private Tblpfstatus standardisationStatus;
	public Tblpfstatus getStandardisationStatus() {
		return this.standardisationStatus;
	}
	public Tblpf setStandardisationStatus(Tblpfstatus standardisationStatus) {
		this.standardisationStatus = standardisationStatus;
		return this;
	}

	// bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name = "importationStatus")
	private Tblpfstatus importationStatus;

	public Tblpfstatus getImportationStatus() {
		return this.importationStatus;
	}
	public Tblpf setImportationStatus(Tblpfstatus importationStatus) {
		this.importationStatus = importationStatus;
		return this;
	}

	// bi-directional many-to-one association to Tblpfstatuschangelog
	@OneToMany(mappedBy = "tblpf", targetEntity=Tblpfstatuschangelog.class)
	@JsonBackReference("statuschangelog_tblpf")
	private List<Tblpfstatuschangelog> tblpfstatuschangelogs;

	public List<Tblpfstatuschangelog> getTblpfstatuschangelogs() {
		return this.tblpfstatuschangelogs;
	}
	public void setTblpfstatuschangelogs(List<Tblpfstatuschangelog> tblpfstatuschangelogs) {
		this.tblpfstatuschangelogs = tblpfstatuschangelogs;
	}
	public Tblpfstatuschangelog addTblpfstatuschangelog(Tblpfstatuschangelog tblpfstatuschangelog) {
		getTblpfstatuschangelogs().add(tblpfstatuschangelog);
		tblpfstatuschangelog.setTblpf(this);

		return tblpfstatuschangelog;
	}
	public Tblpfstatuschangelog removeTblpfstatuschangelog(Tblpfstatuschangelog tblpfstatuschangelog) {
		getTblpfstatuschangelogs().remove(tblpfstatuschangelog);
		tblpfstatuschangelog.setTblpf(null);

		return tblpfstatuschangelog;
	}

	private Boolean isZip;
	public Boolean getIsZip() {
		return isZip;
	}
	public void setIsZip(Boolean isZip) {
		this.isZip = isZip;
	}

	private Boolean hasHeader;
	public Boolean getHasHeader() {
		return hasHeader;
	}
	public void setHasHeader(Boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	// force back ticks as "separator" is a reserved word by MySQL
	@Column(name = "`separator`")
	private String separator;
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public String toString() {
		return "Tblpf [id=" + id + ", description=" + description + ", downloadURL=" + downloadURL + ", importPriority="
				+ importPriority + ", lastImportation=" + lastImportation + ", lastStandardisation="
				+ lastStandardisation + ", partnerId=" + partnerId + ", startTime=" + startTime + ", timePeriod="
				+ timePeriod + ", tblpfmappings=" + tblpfmappings + ", tblpfproducts=" + tblpfproducts
				+ ", latestStatus=" + latestStatus + ", standardisationStatus=" + standardisationStatus
				+ ", importationStatus=" + importationStatus + ", tblpfstatuschangelogs=" + tblpfstatuschangelogs
				+ ", isZip=" + isZip + ", hasHeader=" + hasHeader + ", separator=" + separator + "]";
	}

	public String toBodyRequest() {
		return "id=" + id + ", description=" + description + ", downloadURL=" + downloadURL + ", importPriority="
				+ importPriority + ", lastImportation=" + lastImportation + ", lastStandardisation="
				+ lastStandardisation + ", partnerId=" + partnerId + ", startTime=" + startTime + ", timePeriod="
				+ timePeriod + ", tblpfmappings=" + tblpfmappings + ", tblpfproducts=" + tblpfproducts
				+ ", latestStatus=" + latestStatus + ", standardisationStatus=" + standardisationStatus
				+ ", importationStatus=" + importationStatus + ", tblpfstatuschangelogs=" + tblpfstatuschangelogs;
	}
}