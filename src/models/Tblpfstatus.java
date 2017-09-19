package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tblpfstatus database table.
 * 
 */
@Entity
@NamedQuery(name="Tblpfstatus.findAll", query="SELECT t FROM Tblpfstatus t")
public class Tblpfstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Tblpf
	@OneToMany(mappedBy="tblpfstatus1")
	private List<Tblpf> tblpfs1;

	//bi-directional many-to-one association to Tblpf
	@OneToMany(mappedBy="tblpfstatus2")
	private List<Tblpf> tblpfs2;

	//bi-directional many-to-one association to Tblpf
	@OneToMany(mappedBy="tblpfstatus3")
	private List<Tblpf> tblpfs3;

	//bi-directional many-to-one association to Tblpfstatuschangelog
	@OneToMany(mappedBy="tblpfstatus")
	private List<Tblpfstatuschangelog> tblpfstatuschangelogs;

	public Tblpfstatus() {
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

	public List<Tblpf> getTblpfs1() {
		return this.tblpfs1;
	}

	public void setTblpfs1(List<Tblpf> tblpfs1) {
		this.tblpfs1 = tblpfs1;
	}

	public Tblpf addTblpfs1(Tblpf tblpfs1) {
		getTblpfs1().add(tblpfs1);
		tblpfs1.setTblpfstatus1(this);

		return tblpfs1;
	}

	public Tblpf removeTblpfs1(Tblpf tblpfs1) {
		getTblpfs1().remove(tblpfs1);
		tblpfs1.setTblpfstatus1(null);

		return tblpfs1;
	}

	public List<Tblpf> getTblpfs2() {
		return this.tblpfs2;
	}

	public void setTblpfs2(List<Tblpf> tblpfs2) {
		this.tblpfs2 = tblpfs2;
	}

	public Tblpf addTblpfs2(Tblpf tblpfs2) {
		getTblpfs2().add(tblpfs2);
		tblpfs2.setTblpfstatus2(this);

		return tblpfs2;
	}

	public Tblpf removeTblpfs2(Tblpf tblpfs2) {
		getTblpfs2().remove(tblpfs2);
		tblpfs2.setTblpfstatus2(null);

		return tblpfs2;
	}

	public List<Tblpf> getTblpfs3() {
		return this.tblpfs3;
	}

	public void setTblpfs3(List<Tblpf> tblpfs3) {
		this.tblpfs3 = tblpfs3;
	}

	public Tblpf addTblpfs3(Tblpf tblpfs3) {
		getTblpfs3().add(tblpfs3);
		tblpfs3.setTblpfstatus3(this);

		return tblpfs3;
	}

	public Tblpf removeTblpfs3(Tblpf tblpfs3) {
		getTblpfs3().remove(tblpfs3);
		tblpfs3.setTblpfstatus3(null);

		return tblpfs3;
	}

	public List<Tblpfstatuschangelog> getTblpfstatuschangelogs() {
		return this.tblpfstatuschangelogs;
	}

	public void setTblpfstatuschangelogs(List<Tblpfstatuschangelog> tblpfstatuschangelogs) {
		this.tblpfstatuschangelogs = tblpfstatuschangelogs;
	}

	public Tblpfstatuschangelog addTblpfstatuschangelog(Tblpfstatuschangelog tblpfstatuschangelog) {
		getTblpfstatuschangelogs().add(tblpfstatuschangelog);
		tblpfstatuschangelog.setTblpfstatus(this);

		return tblpfstatuschangelog;
	}

	public Tblpfstatuschangelog removeTblpfstatuschangelog(Tblpfstatuschangelog tblpfstatuschangelog) {
		getTblpfstatuschangelogs().remove(tblpfstatuschangelog);
		tblpfstatuschangelog.setTblpfstatus(null);

		return tblpfstatuschangelog;
	}

}