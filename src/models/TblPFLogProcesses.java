// aripe 2018-04-14

package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblpflogprocesses database table.
 * 
 */
@Entity
@Table(name="tblPFLogProcesses")
@NamedQueries({
	@NamedQuery(name="TblPFLogProcesses.findAll", query="SELECT x FROM TblPFLogProcesses x"),
	@NamedQuery(name="TblPFLogProcesses.findOneById", query="SELECT x FROM TblPFLogProcesses x WHERE x.id=:id"),
	@NamedQuery(name="TblPFLogProcesses.findOneByName", query="SELECT x FROM TblPFLogProcesses x WHERE x.name=:name")
})
public class TblPFLogProcesses implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	@Column(length=30)
	private String name;
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	@Column(length=300)
	private String url;
	public String getUrl() { return this.url; }
	public void setUrl(String url) { this.url = url; }

	public TblPFLogProcesses() {
	}
	@Override
	public String toString() {
		return "TblPFLogProcesses [id=" + id + ", name=" + name + ", url=" + url + "]";
	}


}