
package shorturl.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eder
 */
@Entity
@Table(name = "URL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Url.findAll", query = "SELECT u FROM Url u"),
    @NamedQuery(name = "Url.findById", query = "SELECT u FROM Url u WHERE u.id = :id"),
    @NamedQuery(name = "Url.findByShortUrl", query = "SELECT u FROM Url u WHERE u.shortUrl = :shortUrl"),
    @NamedQuery(name = "Url.findByFullUrl", query = "SELECT u FROM Url u WHERE u.fullUrl = :fullUrl"),
    @NamedQuery(name = "Url.findByCreatedAt", query = "SELECT u FROM Url u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Url.findByUpdatedAt", query = "SELECT u FROM Url u WHERE u.updatedAt = :updatedAt")})
public class Url implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "SHORT_URL")
    private String shortUrl;
    @Size(max = 255)
    @Column(name = "FULL_URL")
    private String fullUrl;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "url")
    private List<UrlVisits> urlVisitsList;
    @JoinColumn(name = "USER", referencedColumnName = "ID")
    @ManyToOne
    private User user;

    public Url() {
    }

    public Url(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public List<UrlVisits> getUrlVisitsList() {
        return urlVisitsList;
    }

    public void setUrlVisitsList(List<UrlVisits> urlVisitsList) {
        this.urlVisitsList = urlVisitsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Url)) {
            return false;
        }
        Url other = (Url) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shorturl.entities.Url[ id=" + id + " ]";
    }
    
}
