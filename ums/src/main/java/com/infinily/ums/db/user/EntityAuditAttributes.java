package com.infinily.ums.db.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class EntityAuditAttributes {

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_DATE")
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@Column(name = "CREATED_USER", nullable = false, updatable = false)
	@CreatedBy
	private String createdUser;

	@Column(name = "MODIFIED_USER")
	@LastModifiedBy
	private String modifiedUser;

	@Column(name = "FLG_IS_DELETED")
	private boolean deleted;
}
