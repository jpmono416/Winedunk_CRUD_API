package models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Timestamp;


/**
 * The persistent class for the tblpfstatuschangelog database table.
 * 
 */
@Entity
@Table(name="tblPFStatusChangeLog")
@NamedQuery(name="Tblpfstatuschangelog.findAll", query="SELECT t FROM Tblpfstatuschangelog t")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Tblpfstatuschangelog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp dateTimeEvent;

	//bi-directional many-to-one association to Tblpfstatus
	@ManyToOne
	@JoinColumn(name="pfStatus_id")
	@JsonBackReference("tblpfstatus_status")
	private Tblpfstatus tblpfstatus;

	//bi-directional many-to-one association to Tblpf
	@ManyToOne
	@JoinColumn(name="tblProductFeeds_id")
	@JsonBackReference("tblpfstatuschangelogs_tblpf")
	private Tblpf tblpf;

	public Tblpfstatuschangelog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateTimeEvent() {
		return this.dateTimeEvent;
	}

	public void setDateTimeEvent(Timestamp dateTimeEvent) {
		this.dateTimeEvent = dateTimeEvent;
	}

	public Tblpfstatus getTblpfstatus() {
		return this.tblpfstatus;
	}

	public void setTblpfstatus(Tblpfstatus tblpfstatus) {
		this.tblpfstatus = tblpfstatus;
	}

	public Tblpf getTblpf() {
		return this.tblpf;
	}

	public void setTblpf(Tblpf tblpf) {
		this.tblpf = tblpf;
	}

}