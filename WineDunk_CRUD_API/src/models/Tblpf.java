package models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tblpfs database table.
 * 
 */
@Entity
@Table(name="tblpfs")
@NamedQuery(name="Tblpf.findAll", query="SELECT t FROM Tblpf t")
public class Tblpf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String downloadURL;

	private int importPriority;

	private Timestamp lastImportation;

	private Timestamp lastStandardisation;

	private int partnerId;

	private Time startTime;

	private String timePeriod;

	//bi-directional many-to-one association to Tblpfmapping
	@OneToMany(mappedBy="tblpf")
	private List<Tblpfmapping> tblpfmappings;

	//bi-directional many-to-one association to Tblpfproduct
	@OneToMany(mappedBy="tblpf")
	private List<Tblpfproduct> tblpfproducts;

	//bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name="latestStatus")
	private Tblpfstatus latestStatus;

	//bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name="standardisationStatus")
	private Tblpfstatus standardisationStatus;

	//bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name="importationStatus")
	private Tblpfstatus importationStatus;

	//bi-directional many-to-one association to Tblpfstatuschangelog
	@OneToMany(mappedBy="tblpf")
	private List<Tblpfstatuschangelog> tblpfstatuschangelogs;

	public Tblpf() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownloadURL() {
		return this.downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public int getImportPriority() {
		return this.importPriority;
	}

	public void setImportPriority(int importPriority) {
		this.importPriority = importPriority;
	}

	public Timestamp getLastImportation() {
		return this.lastImportation;
	}

	public void setLastImportation(Timestamp lastImportation) {
		this.lastImportation = lastImportation;
	}

	public Timestamp getLastStandardisation() {
		return this.lastStandardisation;
	}

	public void setLastStandardisation(Timestamp lastStandardisation) {
		this.lastStandardisation = lastStandardisation;
	}

	public int getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public String getTimePeriod() {
		return this.timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public List<Tblpfmapping> getTblpfmappings() {
		return this.tblpfmappings;
	}

	public void setTblpfmappings(List<Tblpfmapping> tblpfmappings) {
		this.tblpfmappings = tblpfmappings;
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

	public Tblpfstatus getLatestStatus() {
		return this.latestStatus;
	}

	public void setLatestStatus(Tblpfstatus latestStatus) {
		this.latestStatus = latestStatus;
	}

	public Tblpfstatus getStandardisationStatus() {
		return this.standardisationStatus;
	}

	public void setStandardisationStatus(Tblpfstatus standardisationStatus) {
		this.standardisationStatus = standardisationStatus;
	}

	public Tblpfstatus getImportationStatus() {
		return this.importationStatus;
	}

	public void setImportationStatus(Tblpfstatus importationStatus) {
		this.importationStatus = importationStatus;
	}

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

	@Override
	public String toString() {
		return "Tblpf [id=" + id + ", description=" + description + ", downloadURL=" + downloadURL + ", importPriority="
				+ importPriority + ", lastImportation=" + lastImportation + ", lastStandardisation="
				+ lastStandardisation + ", partnerId=" + partnerId + ", startTime=" + startTime + ", timePeriod="
				+ timePeriod + ", tblpfmappings=" + tblpfmappings + ", tblpfproducts=" + tblpfproducts
				+ ", latestStatus=" + latestStatus + ", standardisationStatus=" + standardisationStatus
				+ ", importationStatus=" + importationStatus + ", tblpfstatuschangelogs=" + tblpfstatuschangelogs + "]";
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