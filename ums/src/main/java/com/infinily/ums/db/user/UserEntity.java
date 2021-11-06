package com.infinily.ums.db.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.infinily.commondb.db.AttachmentEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserEntity extends EntityAuditAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private Long organizationId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "PHONE", unique = true)
	private String phone;
	
	@Column(name="FLG_IS_ACTIVE")
	private boolean active;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "USER_IMAGES", 
			joinColumns = @JoinColumn(name = "USER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "ATTACHMENT_ID")
	)
	private List<AttachmentEntity> images;
	
	@Column(name="ROLE_ID")
	private Long roleId;
	
	@Column(name="RESET_PASSWORD_TOKEN")
	private String resetPasswordToken;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ADDRESS",
			joinColumns = @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="ADDRESS_ID")
	)
	private List<AddressEntity> address;

	public void createPassword(PasswordEncoder passwordEncoder) {
		if (null != this.password) {
			this.password = passwordEncoder.encode(this.password);
		}
	}

}
