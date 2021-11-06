package com.infinily.commondb.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ATTACHMENT")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AttachmentEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTACHMENT_ID")
	private Long id;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "ATTACHMENT_DATA")
	private byte[] attachmentData;
}
