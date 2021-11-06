package com.infinily.commondb.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class CommonColumns {

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@Column(name = "CREATED_USER", nullable = false, updatable = false)
	private String createdUser;

	@Column(name = "MODIFIED_USER")
	private String modifiedUser;

	@Column(name = "FLG_IS_DELETED")
	private boolean deleted;
}
