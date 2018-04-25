// aripe 2018-04-14

package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblPFLogTypes database table.
 * 
 */
@Entity
@Table(name="tblPFLogCategories")
@NamedQueries({
	@NamedQuery(name="TblPFLogCategories.findAll", query="SELECT x FROM TblPFLogCategories x"),
	@NamedQuery(name="TblPFLogCategories.findOneById", query="SELECT x FROM TblPFLogCategories x WHERE x.id = :id"),
	@NamedQuery(name="TblPFLogCategories.findOneByName", query="SELECT x FROM TblPFLogCategories x WHERE x.name = :name")
})
public class TblPFLogCategories implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	@Column(length=50)
	private String name;
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public TblPFLogCategories() {
	}
	@Override
	public String toString() {
		return "TblPFLogTypes [id=" + id + ", name=" + name + "]";
	}



}