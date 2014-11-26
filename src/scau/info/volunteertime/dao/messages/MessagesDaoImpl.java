/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.messages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import scau.info.volunteertime.tool.DatabaseConnection;
import scau.info.volunteertime.tool.factory.DatabaseConnectionFactory;

/**
 * @author 蔡超敏
 * 
 */
public class MessagesDaoImpl implements MessagesDao {

	@Override
	public String saveMessages(String principalId, String participators,
			String message, String title) {
		String sql = "";

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();

			JSONObject json = new JSONObject(participators);
			JSONArray array = json.getJSONArray("userIds");
			int len = array.length();
			for (int i = 0; i < len; i++) {
				JSONObject jsonObj = array.getJSONObject(i);
				String userIdByJson = jsonObj.getString("userId");

				sql = "INSERT INTO message ( `receive_user_id`, `launch_user_id`, `content`, `title`, `launch_time`, `is_send`)"
						+ " VALUES ( '"
						+ userIdByJson
						+ "', '"
						+ principalId.trim()
						+ "', '"
						+ message
						+ "', '"
						+ title
						+ "', '"
						+ System.currentTimeMillis()
						+ "', '0')";

				statement = conn.createStatement();
				statement.executeUpdate(sql);

			}

			return "success";

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
		return "failure";
	}

	@Override
	public String getNewMessages(String userId) {
		String sql = "SELECT * FROM message WHERE receive_user_id = '" + userId
				+ "'";

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;

		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			jsonInfo = resultSetToJsonPagination(rs);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

	/**
	 * @param rs
	 * @return
	 */
	private String resultSetToJsonPagination(ResultSet rs) throws SQLException,
			JSONException {
		// json数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		int count = 0;

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			count++;
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		JSONObject json = new JSONObject();
		json.put("records", array);

		json.put("pageSize", count);

		return json.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see scau.info.volunteertime.dao.messages.MessagesDao#updateRead(int)
	 */
	@Override
	public String updateRead(int id) {
		String sql = "UPDATE message SET is_send = 2 WHERE id = " + id;

		System.out.println("sql = " + sql);

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;

		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

		return "success";
	}

	/* (non-Javadoc)
	 * @see scau.info.volunteertime.dao.messages.MessagesDao#getNewMessage(java.lang.String)
	 */
	@Override
	public String getNewMessage(String userId) {
		String sql = "SELECT * FROM message WHERE is_send=0 and receive_user_id = '" + userId
				+ "' LIMIT 0,1";

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;

		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			jsonInfo = resultSetToJsonPagination(rs);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

}
