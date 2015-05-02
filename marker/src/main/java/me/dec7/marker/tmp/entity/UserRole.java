package me.dec7.marker.tmp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//@Table(name = "user_role")
//@Cache(region = "userRole", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@AssociationOverrides({
//		@AssociationOverride(name = "pk.user", 
//			joinColumns = @JoinColumn(name = "user_id")),
//		@AssociationOverride(name = "pk.role", 
//			joinColumns = @JoinColumn(name = "role_id")) })
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -9000610753115162266L;

	@EmbeddedId
	private UserRoleID pk = new UserRoleID();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	public UserRoleID getPk() {
		return pk;
	}

	public void setPk(UserRoleID pk) {
		this.pk = pk;
	}
	
	@Transient
	public Role getRole() {
		return this.pk.getRole();
	}
	
	public void setRole(Role role) {
		pk.setRole(role);
	}
	
	@Transient
	public User getUser() {
		return this.pk.getUser();
	}
	
	public void setUser(User user) {
		pk.setUser(user);
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

	@Override
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		
		if (modifyDate == null) {
			if (other.modifyDate != null)
				return false;
		} else if (!modifyDate.equals(other.modifyDate))
			return false;
		
		return true;
	}
	
	

}
