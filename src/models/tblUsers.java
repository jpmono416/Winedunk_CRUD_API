package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "tblUsers")
@NamedQueries({
	@NamedQuery(name="tblUsers.findById", query="SELECT x FROM tblUsers x WHERE x.id = :userId")
})
public class tblUsers {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

	@ManyToOne
    @JoinColumn(name = "countryId")
    private tblCountries countryId;
    public tblCountries getCountryId() { return countryId; }
	public void setCountryId(tblCountries countryId) { this.countryId = countryId; }


	@ManyToOne
    @JoinColumn(name = "preferredCurrencyId")
    private tblCurrencies preferredCurrencyId;
    public tblCurrencies getPreferredCurrencyId() { return preferredCurrencyId; }
    public void setPreferredCurrencyId(tblCurrencies preferredCurrencyId) { this.preferredCurrencyId = preferredCurrencyId; }

    @ManyToOne
    @JoinColumn(name = "preferredTimeZoneId")
    private tblTimeZones preferredTimeZoneId;
    public tblTimeZones getPreferredTimeZoneId() { return preferredTimeZoneId; }
    public void setPreferredTimeZoneId(tblTimeZones preferredTimeZoneId) { this.preferredTimeZoneId = preferredTimeZoneId; }

    @ManyToOne
    @JoinColumn(name = "preferredLanguageId")
    private tblLanguages preferredLanguageId;
    public tblLanguages getPreferredLanguageId() { return preferredLanguageId; }
    public void setPreferredLanguageId(tblLanguages preferredLanguageId) { this.preferredLanguageId = preferredLanguageId; }

 	// aripe length attribute added
    @Column(name= "name", length = 45)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // aripe length attribute added
    @Column(name= "preferredEmail", length = 200, nullable = false)
    @NotNull
    private String preferredEmail;
    public String getPreferredEmail() { return preferredEmail; }
	public void setPreferredEmail(String preferredEmail) { this.preferredEmail = preferredEmail; }
	
	// aripe length attribute added
	@Column(name= "preferredPhoneNumber", length = 20)
    private String preferredPhoneNumber;
    public String getPreferredPhoneNumber() { return preferredPhoneNumber; }
	public void setPreferredPhoneNumber(String preferredPhoneNumber) { this.preferredPhoneNumber = preferredPhoneNumber; }


	@Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date DoB;
    public Date getDoB() { return DoB; }
    public void setDoB(Date doB) { DoB = doB; }

    // aripe length attribute added
    @Column(name= "loginEmail", length = 200)
    @NotNull
    private String loginEmail;
    public String getLoginEmail() { return loginEmail; }
	public void setLoginEmail(String loginEmail) { this.loginEmail = loginEmail; }
	
	// aripe length attribute added
	@Column(name= "loginPassword", length = 32)
	private String loginPassword;
	public String getLoginPassword() { return loginPassword; }
	public void setLoginPassword(String loginPassword) { this.loginPassword = loginPassword; }
	
	// aripe length attribute added
	@Column(name= "loginToken", length = 32)
	private String loginToken;
	public String getLoginToken() { return loginToken; }
	public void setLoginToken(String loginToken) { this.loginToken = loginToken; }
	
	// aripe length attribute added
	@Column(name= "recoveringPassEmail", length = 200)
    @NotNull
    private String recoveringPassEmail;
    public String getRecoveringPassEmail() { return recoveringPassEmail; }
    public void setRecoveringPassEmail(String recoveringPassEmail) { this.recoveringPassEmail = recoveringPassEmail; }

    // aripe length attribute added
    @Column(name= "recoveringPassToken", length = 32)
    private String recoveringPassToken;
    public String getRecoveringPassToken() { return recoveringPassToken; }
	public void setRecoveringPassToken(String recoveringPassToken) { this.recoveringPassToken = recoveringPassToken; }

	@Column(name = "isAdmin")
	private Boolean isAdmin;
	public Boolean getIsAdmin() { return isAdmin; }
	public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

	@Column(name= "deleted")
    private Boolean deleted;
    public Boolean isDeleted() {return deleted;}
    public void setDeleted(Boolean deleted) {this.deleted = deleted;}

	@OneToMany(mappedBy = "userId", targetEntity = tblUserPhoneNumbers.class)
    @JsonBackReference("user_phoneNumber")
    private List<tblUserPhoneNumbers> phoneNumbers;
    public List<tblUserPhoneNumbers> getPhoneNumbers() { return phoneNumbers; }
    public void setPhoneNumbers(List<tblUserPhoneNumbers> phoneNumbers) { this.phoneNumbers = phoneNumbers; }

    @OneToMany(mappedBy = "userId", targetEntity = tblUserEmails.class)
    @JsonBackReference("user_emailAddress")
    private List<tblUserEmails> emailAddresses;
    public List<tblUserEmails> getEmailAddresses() { return emailAddresses; }
    public void setEmailAddresses(List<tblUserEmails> emailAddresses) { this.emailAddresses = emailAddresses; }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tblUsers_Platforms",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "platformId"))
    private List<tblPlatforms> platforms;
    public List<tblPlatforms> getPlatforms() { return platforms; }
    public void setPlatforms(List<tblPlatforms> platforms) { this.platforms = platforms; }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<tblDevices> devices;
    public List<tblDevices> getDevices() { return devices; }
    public void setDevices(List<tblDevices> devices) { this.devices = devices; }

    @OneToMany(mappedBy = "user", targetEntity = tblUserSearches.class)
    @JsonBackReference("userSearches_users")
    private List<tblUserSearches> searches;
    public List<tblUserSearches> getSearches() { return searches; }
    public void setSearches(List<tblUserSearches> searches) { this.searches = searches; }

    @OneToMany(mappedBy = "user", targetEntity = tblUserSavedSearches.class)
    @JsonBackReference("user_savedSearches")
    private List<tblUserSavedSearches> savedSearches;
    public List<tblUserSavedSearches> getSavedSearches() { return savedSearches; }
    public void setSavedSearches(List<tblUserSavedSearches> savedSearches) { this.savedSearches = savedSearches; }

    @OneToMany(mappedBy = "userId", targetEntity = tblUserFavouriteWines.class)
    @JsonBackReference("user_favouriteWine")
    private List<tblUserFavouriteWines> favouriteWines;
    public List<tblUserFavouriteWines> getFavouriteWines() { return favouriteWines; }
    public void setFavouriteWines(List<tblUserFavouriteWines> favouriteWines) { this.favouriteWines = favouriteWines; }

    @OneToMany(mappedBy = "userId", targetEntity = tblUserWinesRatings.class)
    @JsonBackReference("user_wineRatings")
    private List<tblUserWinesRatings> wineRatings;
    public List<tblUserWinesRatings> getWineRatings() { return wineRatings; }
    public void setWineRatings(List<tblUserWinesRatings> wineRatings) { this.wineRatings = wineRatings; }

    @OneToMany(mappedBy = "userId", targetEntity = tblUserWineReviews.class)
    @JsonBackReference("user_wineReviews")
    private List<tblUserWineReviews> reviews;
    public List<tblUserWineReviews> getReviews() { return reviews; }
    public void setReviews(List<tblUserWineReviews> reviews) { this.reviews = reviews; }

    @OneToMany(mappedBy = "userId", targetEntity = tblUserWinesViewed.class)
    @JsonBackReference("user_winesViewed")
    private List<tblUserWinesViewed> winesViewed;
    public List<tblUserWinesViewed> getWinesViewed() { return winesViewed; }
    public void setWinesViewed(List<tblUserWinesViewed> winesViewed) { this.winesViewed = winesViewed; }
    
    @OneToMany(mappedBy = "userId", targetEntity = tblClicks.class)
    @JsonBackReference("user_clicks")
    private List<tblClicks> clicks;
    public List<tblClicks> getClicks() { return clicks; }
	public void setClicks(List<tblClicks> clicks) { this.clicks = clicks; }
	
	@OneToMany(mappedBy = "userId", targetEntity = tblUserSubscriptions.class)
	@JsonBackReference("user_subscriptions")
    private List<tblUserSubscriptions> userSubscriptions;
    public List<tblUserSubscriptions> getUserSubscriptions() { return userSubscriptions; }
	public void setUserSubscriptions(List<tblUserSubscriptions> userSubscriptions) { this.userSubscriptions = userSubscriptions; }
	
	@OneToMany(mappedBy = "userId", targetEntity = tblUsersFacebookLogin.class)
	@JsonBackReference("user_facebookLogin")
	private List<tblUsersFacebookLogin> facebookAccounts;
	public List<tblUsersFacebookLogin> getFacebookAccounts() { return facebookAccounts; }
	public void setFacebookAccounts(List<tblUsersFacebookLogin> facebookAccounts) { this.facebookAccounts = facebookAccounts; }
	
	@OneToMany(mappedBy = "userId", targetEntity = tblUsersGoogleLogin.class)
	private List<tblUsersGoogleLogin> googleAccounts;
	public List<tblUsersGoogleLogin> getGoogleAccounts() { return googleAccounts; }
	public void setGoogleAccounts(List<tblUsersGoogleLogin> googleAccounts) { this.googleAccounts = googleAccounts; }
	
	@OneToMany(mappedBy = "userId", targetEntity = tblUserPriceAlerts.class)
	@JsonBackReference("user_googleLogin")
    private List<tblUserPriceAlerts> userPriceAlerts;
    public List<tblUserPriceAlerts> getUserPriceAlerts() { return userPriceAlerts; }
	public void setUserPriceAlerts(List<tblUserPriceAlerts> userPriceAlerts) { this.userPriceAlerts = userPriceAlerts; }
	
	// Fields not included in the DB
	@Transient
	private Integer numericCountryId;
	public Integer getNumericCountryId() { return numericCountryId; }
	public void setNumericCountryId(Integer numericCountryId) { this.numericCountryId = numericCountryId; }
	
	@Transient
	private Integer numericCurrencyId;
	public Integer getNumericCurrencyId() { return numericCurrencyId;  }
	public void setNumericCurrencyId(Integer numericCurrencyId) { this.numericCurrencyId = numericCurrencyId; }
	
	@Transient
	private Integer numericTimeZoneId;
	public Integer getNumericTimeZoneId() { return numericTimeZoneId; }
	public void setNumericTimeZoneId(Integer numericTimeZoneId) { this.numericTimeZoneId = numericTimeZoneId; }
	
	@Transient
	private Integer numericLanguageId;
	public Integer getNumericLanguageId() { return numericLanguageId; }
	public void setNumericLanguageId(Integer numericLanguageId) { this.numericLanguageId = numericLanguageId; }
	
	
	public tblUsers()
    {
        this.id = null;
        this.countryId = null;
        this.preferredCurrencyId = null;
        this.preferredTimeZoneId = null;
        this.preferredLanguageId = null;
        this.name = null;
        this.preferredEmail = null;
        this.preferredPhoneNumber = null;
        this.DoB = null;
        this.recoveringPassEmail = null;
        this.isAdmin = false;
        this.deleted = false;
        this.loginEmail = null;
        this.loginPassword = null;
        this.loginToken = null;
        this.emailAddresses = null;
        this.platforms = null;
        this.favouriteWines = null;
        this.wineRatings = null;
        this.winesViewed = null;
        this.clicks = null;
        this.userSubscriptions = null;
        this.facebookAccounts = null;
        this.googleAccounts = null;
        this.userPriceAlerts = null;
        this.recoveringPassToken = null;
    }
    public tblUsers(Integer id) { this.id = id; }
	
    @Override
	public String toString() {
		return "tblUsers { \"id\" : \"" + id + "\" , countryId\" : \"" + countryId + "\" , preferredCurrencyId\" : \""
				+ preferredCurrencyId + "\" , preferredTimeZoneId\" : \"" + preferredTimeZoneId
				+ "\" , preferredLanguageId\" : \"" + preferredLanguageId + "\" , name\" : \"" + name
				+ "\" , preferredEmail\" : \"" + preferredEmail + "\" , preferredPhoneNumber\" : \""
				+ preferredPhoneNumber + "\" , DoB\" : \"" + DoB + "\" , loginEmail\" : \"" + loginEmail
				+ "\" , loginPassword\" : \"" + loginPassword + "\" , loginToken\" : \"" + loginToken
				+ "\" , recoveringPassEmail\" : \"" + recoveringPassEmail + "\" , recoveringPassToken\" : \""
				+ recoveringPassToken + "\" , isAdmin\" : \"" + isAdmin + "\" , deleted\" : \"" + deleted
				+ "\" , phoneNumbers\" : \"" + phoneNumbers + "\" , emailAddresses\" : \"" + emailAddresses
				+ "\" , platforms\" : \"" + platforms + "\" , devices\" : \"" + devices + "\" , searches\" : \""
				+ searches + "\" , savedSearches\" : \"" + savedSearches + "\" , favouriteWines\" : \"" + favouriteWines
				+ "\" , wineRatings\" : \"" + wineRatings + "\" , reviews\" : \"" + reviews + "\" , winesViewed\" : \""
				+ winesViewed + "\" , clicks\" : \"" + clicks + "\" , userSubscriptions\" : \"" + userSubscriptions
				+ "\" , facebookAccounts\" : \"" + facebookAccounts + "\" , googleAccounts\" : \"" + googleAccounts
				+ "\" , userPriceAlerts\" : \"" + userPriceAlerts + " }";
	}

    
    
}
