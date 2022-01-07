package com.infinily.product.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatagoryBean {
	private Long catagoryId;
	@NotEmpty(message = "catagory name is required")
	private String catagoryName;
	private Long organizationId;
}
