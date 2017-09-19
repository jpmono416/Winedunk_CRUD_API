package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbldownloadmethods database table.
 * 
 */
@Entity
@Table(name="tbldownloadmethods")
@NamedQuery(name="Tbldownloadmethod.findAll", query="SELECT t FROM Tbldownloadmethod t")
public class Tbldownloadmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Tbldownloadmethod() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}