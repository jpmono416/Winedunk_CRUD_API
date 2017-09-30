package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblWineTypes")
@NamedQuery(name = "tblWineTypes.findByName", query = "SELECT t from tblWineTypes t WHERE t.name = :name")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class tblWineTypes {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "deleted")
	private Boolean deleted;

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@OneToMany(mappedBy = "tblWineTypes", targetEntity = TblWinesWineType.class)
	@JsonBackReference("wine")
	private List<TblWinesWineType> winesWineType;

	public List<TblWinesWineType> getWinesWineType() {
		return winesWineType;
	}

	public void setWinesWineType(List<TblWinesWineType> winesWineType) {
		this.winesWineType = winesWineType;
	}

	@OneToMany(mappedBy = "winetypeId", targetEntity = tblBestOffersbyType.class)
	@JsonBackReference("wineType")
	private List<tblBestOffersbyType> bestOffersByType;

	public List<tblBestOffersbyType> getBestOffersByType() {
		return bestOffersByType;
	}

	public void setBestOffersByType(List<tblBestOffersbyType> bestOffersByType) {
		this.bestOffersByType = bestOffersByType;
	}

	public tblWineTypes(Integer id) {
		this.id = id;
	}

	public tblWineTypes() {
		this.id = null;
		this.name = null;
		this.deleted = null;
		this.winesWineType = null;
	}

	public tblWineTypes(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "tblWineTypes [id=" + id + ", name=" + name + ", deleted=" + deleted + ", bestOffersByType="
				+ bestOffersByType + "]";
	}
}