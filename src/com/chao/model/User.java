/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.model;

import java.sql.Date;

/**
 * @author 蔡超敏
 * 
 */
public class User implements IModel {
	private String userid;
	private String username;
	private String password;
	private int authority;
	private Date signdate;

	private String cellPhone;
	private String briefPhone;
	private String gradeAndMajor;
	private String sex;
	private String qq;
	private String wechant;

	public User() {
		System.out.println("-------一个User对象产生-------");
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone
	 *            the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the briefPhone
	 */
	public String getBriefPhone() {
		return briefPhone;
	}

	/**
	 * @param briefPhone
	 *            the briefPhone to set
	 */
	public void setBriefPhone(String briefPhone) {
		this.briefPhone = briefPhone;
	}

	/**
	 * @return the gradeAndMajor
	 */
	public String getGradeAndMajor() {
		return gradeAndMajor;
	}

	/**
	 * @param gradeAndMajor
	 *            the gradeAndMajor to set
	 */
	public void setGradeAndMajor(String gradeAndMajor) {
		this.gradeAndMajor = gradeAndMajor;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the wechant
	 */
	public String getWechant() {
		return wechant;
	}

	/**
	 * @param wechant
	 *            the wechant to set
	 */
	public void setWechant(String wechant) {
		this.wechant = wechant;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public Date getSigndate() {
		return signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

}
