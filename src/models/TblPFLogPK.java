package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TblPFLogs database table.
 * 
 */
@Embeddable
public class TblPFLogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(unique=true, nullable=false)
	private java.util.Date logDate;
	public java.util.Date getLogDate() { return this.logDate; }
	public void setLogDate(java.util.Date logDate) { this.logDate = logDate; }

	@Column(unique=true, nullable=false)
	private int executionNumber;
	public int getExecutionNumber() { return this.executionNumber; }
	public void setExecutionNumber(int executionNumber) { this.executionNumber = executionNumber; }

	@Column(unique=true, nullable=false)
	private int pfprocessId;
	public int getPfprocessId() { return this.pfprocessId; }
	public void setPfprocessId(int pfprocessId) { this.pfprocessId = pfprocessId; }

	@Column(unique=true, nullable=false)
	private int lineNumber;
	public int getLineNumber() { return this.lineNumber; }
	public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

	public TblPFLogPK() {
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblPFLogPK)) {
			return false;
		}
		TblPFLogPK castOther = (TblPFLogPK)other;
		return 
			this.logDate.equals(castOther.logDate)
			&& (this.executionNumber == castOther.executionNumber)
			&& (this.pfprocessId == castOther.pfprocessId)
			&& (this.lineNumber == castOther.lineNumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.logDate.hashCode();
		hash = hash * prime + this.executionNumber;
		hash = hash * prime + this.pfprocessId;
		hash = hash * prime + this.lineNumber;
		
		return hash;
	}
	@Override
	public String toString() {
		return "TblPFLogPK [logDate=" + logDate + ", executionNumber=" + executionNumber + 
				", pfprocessId=" + pfprocessId + ", lineNumber=" + lineNumber + "]";
	}
}