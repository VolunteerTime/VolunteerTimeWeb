/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-25
 */
package scau.info.volunteertime.dao.userinfo;

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
public class UserInfoDaoImpl implements UserInfoDao {

	@Override
	public String checkUserLogin(String userId, String password) {
		String sql = "SELECT password FROM person WHERE login_name = '"
				+ userId + "'";

		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			System.out.println("checkDate password = " + password);
			if (rs.next()) {
				String checkPassword = rs.getString("password");
				System.out
						.println("checkDate checkPassword = " + checkPassword);
				if (checkPassword.trim().equals(password)) {
					return "1";
				} else {
					return "-1";
				}

			} else {
				return "-2";
			}
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
		return null;
	}

	@Override
	public String checkUserRegister(String userId, String password) {
		String sql = "SELECT * FROM person WHERE login_name = '" + userId + "'";

		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			System.out.println("checkDate password = " + password);
			if (rs.next()) {
				return "10";

			} else {
				return "12";
			}
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
		return null;
	}

	@Override
	public String commitUserData(String userId, String password, String name,
			String sex, String className, String longPhone, String briefPhone,
			String qq, String wechant) {
		String sql = "SELECT * FROM person WHERE login_name = '" + userId + "'";

		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			System.out.println("checkDate password = " + password);
			if (rs.next()) {
				return "10";
			} else {

				// save user
				sql = "INSERT INTO person(login_name , password) VALUES ('"
						+ userId + "','" + password + "')";
				statement = conn.createStatement();
				statement.executeUpdate(sql);

				sql = "INSERT INTO person_info (login_name,name,sex,class_name,long_cell_phone,brief_cell_phone,"
						+ "qq,	wechat) VALUES('"
						+ userId
						+ "','"
						+ name
						+ "','"
						+ sex
						+ "','"
						+ className
						+ "','"
						+ longPhone
						+ "','"
						+ briefPhone
						+ "','"
						+ qq
						+ "','"
						+ wechant
						+ "'	);";

				System.out.println("sql = " + sql);
				statement = conn.createStatement();
				statement.executeUpdate(sql);

				return "12";
			}
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
		return null;
	}

	@Override
	public String getUserInfo(String userId) {
		String sql = "SELECT * FROM person_info WHERE login_name = '" + userId
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
	 * @see
	 * scau.info.volunteertime.dao.userinfo.UserInfoDao#commitUserInfo(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String commitUserInfo(String userId, String key, String value) {

		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();

			String sql = "UPDATE person_info SET " + key + " = '" + value
					+ "' WHERE login_name = '" + userId + "'";

			System.out.println("sql = " + sql);
			statement.executeUpdate(sql);

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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.userinfo.UserInfoDao#changePassword(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String changePassword(String userId, String orignPassword,
			String newPassword) {
		String sql = "SELECT password FROM person WHERE login_name = '"
				+ userId + "'";

		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			System.out.println("checkDate password = " + orignPassword);
			if (rs.next()) {
				String checkPassword = rs.getString("password");
				System.out
						.println("checkDate checkPassword = " + checkPassword);
				if (checkPassword.trim().equals(orignPassword)) {

					// 修改密码
					sql = "UPDATE person SET password = '" + newPassword
							+ "' WHERE login_name = '" + userId + "'";

					System.out.println("sql = " + sql);
					statement = conn.createStatement();
					statement.executeUpdate(sql);

					return "2";
				} else {
					return "-1";
				}

			} else {
				return "-3";
			}
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
		return null;
	}

}
