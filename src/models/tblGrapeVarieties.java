package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblGrapeVarieties")
public class tblGrapeVarieties {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Column(name= "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

    @ManyToMany(mappedBy = "grapeVarieties", targetEntity = tblWines.class)
    @JsonBackReference
    private List<tblWines> wines;
    public List<tblWines> getWines() { return wines; }
	public void setWines(List<tblWines> wines) { this.wines = wines; }

    public tblGrapeVarieties(Integer id) { this.id = id; }
    public tblGrapeVarieties()
    {
        this.id = null;
        this.name = null;
        this.deleted = null;
        this.wines = null;
    }
    public tblGrapeVarieties(String name) {
        this.name = name;
    }
}