package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: TblGrapeVariety
 *
 */
@Entity
@Table(name="tblWinesGrapeVarieties")
@NamedQueries({
	@NamedQuery(name="tblWinesGrapesVarieties.finAll", query="SELECT wg FROM TblWinesGrapeVariety wg")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class TblWinesGrapeVariety implements Serializable {	
	private static final long serialVersionUID = 1L;

   @Id
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "wineId")
   private tblWines wine;

   @ManyToOne
   @JoinColumn(name = "grapeVarietyId")
   private tblGrapeVarieties grapeVariety;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public tblWines getWine() {
		return wine;
	}
	
	public void setWine(tblWines wine) {
		this.wine = wine;
	}
	
	public tblGrapeVarieties getGrapeVariety() {
		return grapeVariety;
	}
	
	public void setGrapeVariety(tblGrapeVarieties grapeVariety) {
		this.grapeVariety = grapeVariety;
	}

	@Override
	public String toString() {
		return "TblWinesGrapeVariety [id=" + id + ", wine=" + wine + ", grapeVariety=" + grapeVariety + "]";
	}
}