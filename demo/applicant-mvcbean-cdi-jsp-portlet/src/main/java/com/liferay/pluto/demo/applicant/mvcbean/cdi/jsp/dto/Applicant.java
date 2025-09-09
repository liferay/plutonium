/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.liferay.pluto.demo.applicant.mvcbean.cdi.jsp.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.FormParam;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * @author  Neil Griffin
 */
@Dependent
public class Applicant implements Serializable {

	private static final long serialVersionUID = 2774594923346476261L;

	private List<Attachment> attachments;

	@Inject
	@FormParam("city")
	@NotBlank
	private String city;

	private String comments;

	@Inject
	@FormParam("dateOfBirth")
	@NotNull
	@Past
	private Date dateOfBirth;

	@Inject
	@FormParam("emailAddress")
	@Email
	@NotBlank
	private String emailAddress;

	@Inject
	@FormParam("firstName")
	@NotBlank
	private String firstName;

	@Inject
	@FormParam("lastName")
	@NotBlank
	private String lastName;

	@Inject
	@FormParam("phoneNumber")
	@NotBlank
	@Pattern(regexp = "([0-9]+)")
	private String phoneNumber;

	@Inject
	@FormParam("postalCode")
	@NotBlank
	private String postalCode;

	@Inject
	@FormParam("provinceId")
	@NotNull
	@Range(min = 1L)
	private Long provinceId;

	public Applicant() {

		Calendar calendar = new GregorianCalendar();
		this.dateOfBirth = calendar.getTime();
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public String getCity() {
		return city;
	}

	public String getComments() {
		return comments;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
}
