
package shorturl.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eder
 */
@Entity
@Table(name = "URL_VISITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrlVisits.findAll", query = "SELECT u FROM UrlVisits u"),
    @NamedQuery(name = "UrlVisits.findById", query = "SELECT u FROM UrlVisits u WHERE u.id = :id"),
    @NamedQuery(name = "UrlVisits.findByBrowser", query = "SELECT u FROM UrlVisits u WHERE u.browser = :browser"),
    @NamedQuery(name = "UrlVisits.findByClientDomain", query = "SELECT u FROM UrlVisits u WHERE u.clientDomain = :clientDomain"),
    @NamedQuery(name = "UrlVisits.findByCreatedAt", query = "SELECT u FROM UrlVisits u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "UrlVisits.findByIp", query = "SELECT u FROM UrlVisits u WHERE u.ip = :ip"),
    @NamedQuery(name = "UrlVisits.findByOperativeSystem", query = "SELECT u FROM UrlVisits u WHERE u.operativeSystem = :operativeSystem"),
    @NamedQuery(name = "UrlVisits.findByCountry", query = "SELECT u FROM UrlVisits u WHERE u.country = :country"),
    @NamedQuery(name = "UrlVisits.findByCountryCode", query = "SELECT u FROM UrlVisits u WHERE u.countryCode = :countryCode")})
public class UrlVisits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "BROWSER")
    private String browser;
    @Size(max = 2147483647)
    @Column(name = "CLIENT_DOMAIN")
    private String clientDomain;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 2147483647)
    @Column(name = "IP")
    private String ip;
    @Size(max = 2147483647)
    @Column(name = "OPERATIVE_SYSTEM")
    private String operativeSystem;
    @Size(max = 150)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 3)
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @JoinColumn(name = "URL", referencedColumnName = "ID")
    @ManyToOne
    private Url url;

    public UrlVisits() {
    }

    public UrlVisits(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getClientDomain() {
        return clientDomain;
    }

    public void setClientDomain(String clientDomain) {
        this.clientDomain = clientDomain;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlVisits)) {
            return false;
        }
        UrlVisits other = (UrlVisits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shorturl.entities.UrlVisits[ id=" + id + " ]";
    }
    
}
