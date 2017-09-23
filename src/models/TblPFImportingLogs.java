package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblPFImportingLogs")
public class TblPFImportingLogs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;

	String logMessage;

	String technicalDetails;

	Tblpf pf;

	Tblpfproduct product;

	Date date;

	public TblPFImportingLogs(){}

	public TblPFImportingLogs(Integer id, String logMessage, String technicalDetails, Tblpf pf, Tblpfproduct product, Date date) {
		super();
		this.id = id;
		this.logMessage = logMessage;
		this.technicalDetails = technicalDetails;
		this.pf = pf;
		this.product = product;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public String getTechnicalDetails() {
		return technicalDetails;
	}
	public void setTechnicalDetails(String technicalDetails) {
		this.technicalDetails = technicalDetails;
	}
	public Tblpf getPf() {
		return pf;
	}
	public void setPf(Tblpf pf) {
		this.pf = pf;
	}
	public Tblpfproduct getProduct() {
		return product;
	}
	public void setProduct(Tblpfproduct product) {
		this.product = product;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TblPFImportingLogs [id=" + id + ", logMessage=" + logMessage + ", technicalDetails=" + technicalDetails
				+ ", pf=" + pf + ", product=" + product + ", date=" + date + "]";
	}

}
