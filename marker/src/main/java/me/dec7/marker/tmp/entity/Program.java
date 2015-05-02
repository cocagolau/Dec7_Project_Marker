package me.dec7.marker.tmp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//@Table(name="program")
//@Cache(region = "program", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Program implements Serializable {
	
	private static final long serialVersionUID = 990588386281113988L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "description", unique = true, length=255, nullable=false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.program", cascade=CascadeType.ALL)
	private Set<UrlRole> urlRoles = new HashSet<UrlRole>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
	private Set<UrlRepository> urlRepository = new HashSet<UrlRepository>(0);
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modify_date")
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UrlRole> getUrlRoles() {
		return urlRoles;
	}

	public void setUrlRoles(Set<UrlRole> urlRoles) {
		this.urlRoles = urlRoles;
	}

	public Set<UrlRepository> getUrlRepository() {
		return urlRepository;
	}

	public void setUrlRepository(Set<UrlRepository> urlRepository) {
		this.urlRepository = urlRepository;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
