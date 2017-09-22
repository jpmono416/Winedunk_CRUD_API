package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tblpfstatuschangelog database table.
 * 
 */
@Embeddable
public class TblpfstatuschangelogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int tblProductFeeds_id;

	@Column(insertable=false, updatable=false)
	private int pfStatus_id;

	public TblpfstatuschangelogPK() {
	}
	public int getTblProductFeeds_id() {
		return this.tblProductFeeds_id;
	}
	public void setTblProductFeeds_id(int tblProductFeeds_id) {
		this.tblProductFeeds_id = tblProductFeeds_id;
	}
	public int getPfStatus_id() {
		return this.pfStatus_id;
	}
	public void setPfStatus_id(int pfStatus_id) {
		this.pfStatus_id = pfStatus_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblpfstatuschangelogPK)) {
			return false;
		}
		TblpfstatuschangelogPK castOther = (TblpfstatuschangelogPK)other;
		return 
			(this.tblProductFeeds_id == castOther.tblProductFeeds_id)
			&& (this.pfStatus_id == castOther.pfStatus_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tblProductFeeds_id;
		hash = hash * prime + this.pfStatus_id;
		
		return hash;
	}
}