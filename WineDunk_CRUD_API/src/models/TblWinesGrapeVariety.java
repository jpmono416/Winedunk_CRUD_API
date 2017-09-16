package models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity implementation class for Entity: TblGrapeVariety
 *
 */
@Entity
public class TblWinesGrapeVariety implements Serializable {	
	private static final long serialVersionUID = 1L;

   @Id
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "wineId")
   @JsonBackReference("wine_winesvarieties")
   private tblWines wine;

   @ManyToOne
   @JoinColumn(name = "grapeVarietyId")
   @JsonBackReference("variety_winesvarieties")
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