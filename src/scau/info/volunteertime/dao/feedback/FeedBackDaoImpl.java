/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.feedback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chao.model.Message;

import scau.info.volunteertime.tool.DatabaseConnection;
import scau.info.volunteertime.tool.factory.DatabaseConnectionFactory;
import scau.info.volunteertime.vo.FeedBack;

/**
 * @author 蔡超敏
 * 
 */
public class FeedBackDaoImpl implements FeedBackDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.feedback.FeedBackDao#commitUserFeedBack(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public String commitUserFeedBack(String userId, String content) {

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();

			String sql = "INSERT INTO feedback (userId,content) VALUES('"
					+ userId + "','" + content + "'	);";

			System.out.println("sql = " + sql);
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("conn.close() err");
				e.printStackTrace();
			}
			dbc.close();
		}
		return "1";
	}

	public List<FeedBack> getFeedBack() {
		String sql = "SELECT id,userId,content FROM feedback ";

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;

		List<FeedBack> feedBacks = new ArrayList<FeedBack>();
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			FeedBack feedBack;
			while (rs.next()) {
				feedBack = new FeedBack();
				feedBack.setId(rs.getInt(1));
				feedBack.setUserId(rs.getString(2));
				feedBack.setContent(rs.getString(3));
				feedBacks.add(feedBack);
			}

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return feedBacks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scau.info.volunteertime.dao.feedback.FeedBackDao#delete(int)
	 */
	@Override
	public String delete(int id) {
		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();

			String sql = "delete from feedback where id = " + id;

			System.out.println("sql = " + sql);
			statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("conn.close() err");
				e.printStackTrace();
			}
			dbc.close();
		}
		return "true";
	}
}
