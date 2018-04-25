// aripe 2018-04-14

package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblPFLogTypes database table.
 * 
 */
@Entity
@Table(name="tblPFLogTypes")
@NamedQueries({
	@NamedQuery(name="TblPFLogTypes.findAll", query="SELECT x FROM TblPFLogTypes x"),
	@NamedQuery(name="TblPFLogTypes.findOneById", query="SELECT x FROM TblPFLogTypes x WHERE x.id = :id"),
	@NamedQuery(name="TblPFLogTypes.findOneByName", query="SELECT x FROM TblPFLogTypes x WHERE x.name = :name")
})
public class TblPFLogTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	@Column(length=20)
	private String name;
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public TblPFLogTypes() {
	}
	@Override
	public String toString() {
		return "TblPFLogTypes [id=" + id + ", name=" + name + "]";
	}



}