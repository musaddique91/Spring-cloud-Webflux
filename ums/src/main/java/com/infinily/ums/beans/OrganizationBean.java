package com.infinily.ums.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationBean {
	private Long organizationId;
	private String name;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String address1;
	private String address2;
}
