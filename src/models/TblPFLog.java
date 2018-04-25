// aripe 2018-04-14

package models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Timestamp;


/**
 * The persistent class for the tblpflogs database table.
 * 
 */
@Entity
@Table(name="tblPFLogs")
@NamedQueries({
	@NamedQuery(name="TblPFLog.findAll", query="SELECT x FROM TblPFLog x"),
	@NamedQuery(name="TblPFLog.findOneByDateNumberProcessAndLineNumber", query="SELECT x FROM TblPFLog x where x.id.logDate=:logDate AND x.id.executionNumber=:executionNumber AND x.id.pfprocessId=:pfprocessId AND x.id.lineNumber=:lineNumber"),
	@NamedQuery(name="TblPFLog.findAllByDateNumberAndProcess", query="SELECT x FROM TblPFLog x where x.id.logDate=:logDate AND x.id.executionNumber=:executionNumber AND x.id.pfprocessId=:pfprocessId"),
	@NamedQuery(name="TblPFLog.findAllByDateAndNumber", query="SELECT x FROM TblPFLog x where x.id.logDate=:logDate AND x.id.executionNumber=:executionNumber"),
	@NamedQuery(name="TblPFLog.findAllByDateAndProcess", query="SELECT x FROM TblPFLog x where x.id.logDate=:logDate AND x.id.pfprocessId=:pfprocessId"),
	@NamedQuery(name="TblPFLog.findAllByDate", query="SELECT x FROM TblPFLog x where x.id.logDate=:logDate")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class TblPFLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblPFLogPK id;
	public TblPFLogPK getId() { return this.id; }
	public void setId(TblPFLogPK id) { this.id = id; }

	@Column(nullable=false)
	private Timestamp lineTimeStamp;
	public Timestamp getLineTimeStamp() { return this.lineTimeStamp; }
	public void setLineTimeStamp(Timestamp lineTimeStamp) { this.lineTimeStamp = lineTimeStamp; }

	//bi-directional many-to-one association to LogCategory
	@ManyToOne
	@JoinColumn(name="logCategoryId", nullable=false)
	private TblPFLogCategories tblPFLogCategory;
	public TblPFLogCategories getLogCategory() { return this.tblPFLogCategory; }
	public void setLogCategory(TblPFLogCategories tblPFLogCategory) { this.tblPFLogCategory = tblPFLogCategory; }

	//bi-directional many-to-one association to LogType
	@ManyToOne
	@JoinColumn(name="logTypeId", nullable=false)
	private TblPFLogTypes tblPFLogType;
	public TblPFLogTypes getLogType() { return this.tblPFLogType; }
	public void setLogType(TblPFLogTypes tblPFLogType) { this.tblPFLogType = tblPFLogType; }

	//bi-directional many-to-one association to Tblpartner
	@ManyToOne
	@JoinColumn(name="partnerId", nullable=false)
	private tblPartners tblpartner;
	public tblPartners getPartner() { return this.tblpartner; }
	public void setPartner(tblPartners tblpartner) { this.tblpartner = tblpartner; }

	@Column(length=45)
	private String partnerProductId;
	public String getPartnerProductId() { return this.partnerProductId; }
	public void setPartnerProductId(String partnerProductId) { this.partnerProductId = partnerProductId; }

	@Column(length=100)
	private String entityName;
	public String getEntityName() { return this.entityName; }
	public void setEntityName(String entityName) { this.entityName = entityName; }

	private int entityId;
	public int getEntityId() { return this.entityId; }
	public void setEntityId(int entityId) { this.entityId = entityId; }

	@Column(length=1000)
	private String description;
	public String getDescription() { return this.description; }
	public void setDescription(String description) { this.description = description; }

	//bi-directional many-to-one association to tblPFLogProcesses
	@ManyToOne
	@JoinColumn(name="pfprocessId", insertable=false, updatable=false)
	private TblPFLogProcesses tblPFLogProcess;
	public TblPFLogProcesses getLogProcess() { return this.tblPFLogProcess; }
	public void setLogProcess(TblPFLogProcesses tblPFLogProcess) { this.tblPFLogProcess = tblPFLogProcess; }

	public TblPFLog() {
	}
	@Override
	public String toString() {
		return "TblPFLog [id=" + id + ", lineTimeStamp=" + lineTimeStamp
				+ ", tblPFLogType=" + tblPFLogType + ", tblpartner=" + tblpartner + ", partnerProductId="
				+ partnerProductId + ", entityName=" + entityName + ", entityId=" + entityId + ", description="
				+ description + ", tblPFLogProcess=" + tblPFLogProcess + "]";
	}

}