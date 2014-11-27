/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.model.IModel;
import com.chao.model.User;

/**
 * @author 蔡超敏
 * 
 */
public class UserDaoImpl implements IDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean updateCount(int count) throws Exception {
		System.out.println("user-updateCount-init");
		boolean flag = false;
		String sql = "update countperson set countPerson = ? where id = 1";
		this.pstmt = this.conn.prepareStatement(sql);

		this.pstmt.setInt(1, count);

		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		System.out.println("user-updateCount-end");
		return flag;
	}

	public int getCount() throws Exception {
		System.out.println("user-getCount-init");
		String sql = "select countPerson from countperson where id = 1";
		this.pstmt = this.conn.prepareStatement(sql);
		int num = 0;
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			num = rs.getInt(1);
		}
		this.pstmt.close();
		System.out.println("user-getCount-end");
		return num;
	}

	public int getUser(int type) throws Exception {
		String sql = "select count(*) from user where authority = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, type);
		ResultSet rs = this.pstmt.executeQuery();
		int num = 0;
		if (rs.next()) {
			num = rs.getInt(1);
		}
		this.pstmt.close();
		System.out.println("user-getUser-end");
		return num;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chao.dao.IDao#findById(java.lang.Object)
	 */
	@Override
	public IModel findById(Object key) throws Exception {
		User user = null;
		String sql = "select person.login_name,person_info.name,person.password,person_info.type,person_info.sex"
				+ ",person_info.class_name,person_info.long_cell_phone,"
				+ "person_info.brief_cell_phone,person_info.qq,person_info.wechat from person,person_info where"
				+ " person.login_name=person_info.login_name and person.login_name = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, (String) key);
		ResultSet rs = this.pstmt.executeQuery();
		System.out.println("findById key = " + key + " start 1");
		if (rs.next()) {
			user = new User();
			user.setUserid(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setAuthority(Integer.parseInt(rs.getString(4)));
			user.setSex(rs.getString(5));
			user.setGradeAndMajor(rs.getString(6));
			user.setCellPhone(rs.getString(7));
			user.setBriefPhone(rs.getString(8));
			user.setQq(rs.getString(9));
			user.setWechant(rs.getString(10));
			user.setSigndate(new Date(System.currentTimeMillis()));
		}
		System.out.println("findById key = " + key + " end 1");
		this.pstmt.close();
		System.out.println("user-findById-end");
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chao.dao.IDao#findAll(java.lang.Object)
	 */
	@Override
	public List<User> findAll(Object key) throws Exception {
		List<User> users = new ArrayList<User>();
		User user = null;
		String sql = "select userid,username,password,authority,signdate from user";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setUserid(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setAuthority(rs.getInt(4));
			user.setSigndate(rs.getDate(5));
			users.add(user);
		}
		this.pstmt.close();
		System.out.println("user-findAll-end");
		return users;
	}

	public List<User> findAll(String userid, int authority) throws Exception {
		List<User> users = new ArrayList<User>();
		User user = null;
		String sql = "";
		if (userid != null && !userid.equals("")) {
			if (authority == 2) {
				sql = "select userid,username,password,authority,signdate from user where userid like ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, "%" + userid + "%");
			} else {
				sql = "select userid,username,password,authority,signdate from user where userid like ? and authority = ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, "%" + userid + "%");
				this.pstmt.setInt(2, authority);
			}
		} else {
			if (authority == 2) {
				sql = "select userid,username,password,authority,signdate from user";
				this.pstmt = this.conn.prepareStatement(sql);
			} else {
				sql = "select userid,username,password,authority,signdate from user where authority = ?";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, authority);
			}
		}
		System.out.println(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			user = new User();
			user.setUserid(rs.getString(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setAuthority(rs.getInt(4));
			user.setSigndate(rs.getDate(5));
			users.add(user);
		}
		this.pstmt.close();
		System.out.println("user-findAll-end");
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chao.dao.IDao#add(com.chao.model.IModel)
	 */
	@Override
	public boolean add(IModel obj) throws Exception {
		System.out.println("user-add-init");
		boolean flag = false;
		User user = (User) obj;
		String sql = "insert into user(userid,username,password,authority,signdate) values(?,?,?,?,NOW())";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getUserid());
		this.pstmt.setString(2, user.getUsername());
		this.pstmt.setString(3, user.getPassword());
		this.pstmt.setInt(4, user.getAuthority());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		System.out.println("user-add-end");
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chao.dao.IDao#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Object key) throws Exception {
		System.out.println("user-delete-init");
		boolean flag = false;
		String sql = "delete from user where userid = ?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, (String) key);
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		System.out.println("user-delete-end");
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chao.dao.IDao#update(com.chao.model.IModel)
	 */
	@Override
	public boolean update(IModel obj) throws Exception {
		System.out.println("user-update-init");
		boolean flag = false;
		String sql = "update user set username = ?,password = ?,authority = ? where userid = ?";
		User user = (User) obj;
		this.pstmt = this.conn.prepareStatement(sql);

		this.pstmt.setString(1, user.getUsername());
		this.pstmt.setString(2, user.getPassword());
		this.pstmt.setInt(3, user.getAuthority());
		this.pstmt.setString(4, user.getUserid());

		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		System.out.println("user-update-end");
		return flag;
	}

}
