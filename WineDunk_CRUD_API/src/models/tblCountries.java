package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblCountries")
@NamedQuery(name="tblCountries.findByName", query="SELECT t FROM tblCountries t")
public class tblCountries {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Column(name= "name")
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(name = "`isoAlpha-2-Code`")
    private String isoAlpha2Code;
    public String getIsoAlpha2Code() { return isoAlpha2Code; }
	public void setIsoAlpha2Code(String isoAlpha2Code) { this.isoAlpha2Code = isoAlpha2Code; }
	
	@Column(name = "`isoAlpha-3-Code`")
    private String isoAlpha3Code;
	public String getIsoAlpha3Code() { return isoAlpha3Code; }
	public void setIsoAlpha3Code(String isoAlpha3Code) { this.isoAlpha3Code = isoAlpha3Code; }
	
    @Column(name = "isoNumericCode")
    private Integer isoNumericCode;
    public Integer getIsoNumericCode() { return isoNumericCode; }
	public void setIsoNumericCode(Integer isoNumericCode) { this.isoNumericCode = isoNumericCode; }

    @Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

    @ManyToMany
    @JoinTable(name = "tblCountries_Currencies",
            joinColumns = @JoinColumn(name = "countryId"),
            inverseJoinColumns = @JoinColumn(name = "currencyId"))
    private List<tblCurrencies> currencies;
    public List<tblCurrencies> getCurrencies() { return currencies; }
    public void setCurrencies(List<tblCurrencies> currencies) { this.currencies = currencies; }

    @ManyToMany
    @JoinTable(name = "tblCountries_Languages",
            joinColumns = @JoinColumn(name = "countryId"),
            inverseJoinColumns = @JoinColumn(name = "languageId"))
    private List<tblLanguages> languages;
    public List<tblLanguages> getLanguages() { return languages; }
    public void setLanguages(List<tblLanguages> languages) { this.languages = languages; }

    @ManyToMany
    @JoinTable(name = "tblCountries_TimeZones",
            joinColumns = @JoinColumn(name = "countryId"),
            inverseJoinColumns = @JoinColumn(name = "timeZoneId"))
    private List<tblTimeZones> timeZones;
    public List<tblTimeZones> getTimeZones() { return timeZones; }
    public void setTimeZones(List<tblTimeZones> timeZones) { this.timeZones = timeZones; }

    @OneToMany(mappedBy = "countryId", targetEntity = tblUsers.class)
    @JsonBackReference("user_country")
    private List<tblUsers> users;
    public List<tblUsers> getUsers() { return users; }
    public void setUsers(List<tblUsers> users) { this.users = users; }

    @OneToMany(mappedBy = "countryId", targetEntity = tblRegions.class)
    @JsonBackReference("region_country")
    private List<tblRegions> regions;
    public List<tblRegions> getRegions() { return regions; }
	public void setRegions(List<tblRegions> regions) { this.regions = regions; }
	
	@OneToMany(mappedBy = "countryId", targetEntity = tblCountriesWithWines.class)
    @JsonBackReference("countryWithWine")
    private List<tblCountriesWithWines> countriesWithWines;
    public List<tblCountriesWithWines> getCountriesWithWines() { return countriesWithWines; }
	public void setCountriesWithWines(List<tblCountriesWithWines> countriesWithWines) { this.countriesWithWines = countriesWithWines; }
	
	@OneToMany(mappedBy = "tblCountry", targetEntity = tblWineries.class)
    @JsonBackReference("winery_coutry")
    private List<tblWineries> tblWineries;
    public List<tblWineries> getTblWineries() { return tblWineries; }
	public void setTblWineries(List<tblWineries> tblWineries) { this.tblWineries = tblWineries; }

	@OneToMany(mappedBy = "countryId", targetEntity = tblAppellations.class)
    @JsonBackReference("appellation_country")
    private List<tblAppellations> appellations;
    public List<tblAppellations> getappellations() { return appellations; }
	public void setappellations(List<tblAppellations> appellations) { this.appellations = appellations; }

	public tblCountries(Integer id) { this.id = id; }
    public tblCountries()
    {
        this.id = null;
        this.name = null;
        this.isoAlpha2Code = null;
        this.isoAlpha3Code = null;
        this.isoNumericCode = null;
        this.deleted = null;
        this.currencies = null;
        this.languages = null;
        this.users = null;
        this.timeZones = null;
        this.regions = null;
        this.countriesWithWines = null;
        this.appellations = null;

    }
    public tblCountries(String name) { this.name = name; }
}
