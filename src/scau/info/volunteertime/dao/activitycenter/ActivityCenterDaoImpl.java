/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-24
 */
package scau.info.volunteertime.dao.activitycenter;

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
public class ActivityCenterDaoImpl implements ActivityCenterDao {

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(int size) {
		String sql = "SELECT * FROM activity_center AS a LEFT OUTER JOIN activity_group AS b ON a.group_id=b.id";

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

			jsonInfo = resultSetToJsonPagination(rs, size);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

	/**
	 * @param rs
	 * @param size
	 * @return String
	 * @throws JSONException
	 * @throws SQLException
	 */
	private String resultSetToJsonPagination(ResultSet rs, int size)
			throws SQLException, JSONException {
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

	@Override
	public String checkIn(String userId, int activityId) {
		String sql = "SELECT participators_num , limit_num , group_id , end_time FROM activity_center WHERE id ="
				+ activityId;

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

			System.out.println("checkDate activityId = " + activityId);
			if (rs.next()) {
				long end_time = rs.getLong("end_time");
				System.out.println("checkDate end_time = " + end_time);
				if (end_time < System.currentTimeMillis()) {
					return "failure";
				}

				int participators_num = rs.getInt("participators_num");
				System.out.println("checkDate participators_num = "
						+ participators_num);
				int limit_num = rs.getInt("limit_num");
				System.out.println("checkDate limit_num = " + limit_num);

				int group_id = rs.getInt("group_id");
				System.out.println("checkDate group_id = " + group_id);

				if (participators_num < limit_num) {
					// set participators_num + +
					sql = "UPDATE activity_center SET participators_num = participators_num + 1 WHERE id = "
							+ activityId;
					statement = conn.createStatement();
					statement.executeUpdate(sql);

					if (group_id == 0) {// create new row from activity_group
						// json数组
						JSONArray array = new JSONArray();
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("userId", userId);
						array.put(jsonObj);
						JSONObject json = new JSONObject();
						json.put("userIds", array);
						String jsonStr = json.toString();
						System.out.println("jsonStr = " + jsonStr);

						sql = "INSERT INTO activity_group (principal_id , participators) VALUES (' ','"
								+ jsonStr + "')";
						statement = conn.createStatement();
						statement.executeUpdate(sql);

						sql = "select max(id) maxid from activity_group";
						statement = conn.createStatement();
						rs = statement.executeQuery(sql);
						rs.next();
						int maxid = rs.getInt("maxid");
						System.out.println("checkDate maxid = " + maxid);

						// set participators_num + +
						sql = "UPDATE activity_center SET group_id = " + maxid
								+ " WHERE id = " + activityId;
						statement = conn.createStatement();
						statement.executeUpdate(sql);

					} else {
						System.out.println("checkDate group_id = " + group_id);

						sql = "select principal_id , participators from activity_group WHERE id ="
								+ group_id;
						statement = conn.createStatement();
						rs = statement.executeQuery(sql);
						rs.next();
						String principal_id = rs.getString("principal_id");
						System.out.println("checkDate principal_id = "
								+ principal_id);

						String participators = rs.getString("participators");
						System.out.println("checkDate participators = "
								+ participators);

						JSONObject json = new JSONObject(participators);
						JSONArray array = json.getJSONArray("userIds");
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("userId", userId);
						array.put(jsonObj);
						json = new JSONObject();
						json.put("userIds", array);
						String jsonStr = json.toString();
						System.out.println("jsonStr = " + jsonStr);

						sql = "UPDATE activity_group SET participators = '"
								+ jsonStr + "' WHERE id = " + group_id;
						System.out.println("sql = " + sql);

						statement = conn.createStatement();
						statement.executeUpdate(sql);

					}
					return "success";
				} else {
					return "failure";
				}

			} else {
				return "failure";
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

		return jsonInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.activitycenter.ActivityCenterDao#quitIn(java
	 * .lang.String, int)
	 */
	@Override
	public String quitIn(String userId, int activityId) {
		String sql = "SELECT participators_num , limit_num , group_id , end_time FROM activity_center WHERE id ="
				+ activityId;

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

			System.out.println("checkDate activityId = " + activityId);
			if (rs.next()) {
				long end_time = rs.getLong("end_time");
				System.out.println("checkDate end_time = " + end_time);
				if (end_time < System.currentTimeMillis()) {
					return "failure";
				}

				int participators_num = rs.getInt("participators_num");
				System.out.println("checkDate participators_num = "
						+ participators_num);
				int limit_num = rs.getInt("limit_num");
				System.out.println("checkDate limit_num = " + limit_num);

				int group_id = rs.getInt("group_id");
				System.out.println("checkDate group_id = " + group_id);

				if (group_id != 0) {
					sql = "select principal_id , participators from activity_group WHERE id ="
							+ group_id;
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					rs.next();
					String principal_id = rs.getString("principal_id");
					System.out.println("checkDate principal_id = "
							+ principal_id);

					String participators = rs.getString("participators");
					System.out.println("checkDate participators = "
							+ participators);

					JSONObject json = new JSONObject(participators);
					JSONArray array = json.getJSONArray("userIds");
					int i;
					int len = array.length();
					for (i = 0; i < len; i++) {
						JSONObject jsonObj = array.getJSONObject(i);
						String userIdByJson = jsonObj.getString("userId");
						if (userIdByJson.trim().equals(userId.trim())) {
							array.remove(i);
							break;
						}
					}

					if (i < len) {
						json = new JSONObject();
						json.put("userIds", array);
						String jsonStr = json.toString();
						System.out.println("jsonStr = " + jsonStr);

						sql = "UPDATE activity_group SET participators = '"
								+ jsonStr + "' WHERE id = " + group_id;
						System.out.println("sql = " + sql);

						statement = conn.createStatement();
						statement.executeUpdate(sql);

						sql = "UPDATE activity_center SET participators_num = participators_num - 1 WHERE id = "
								+ activityId;
						statement = conn.createStatement();
						statement.executeUpdate(sql);

						return "success";
					} else {
						return "noIdInActivityGroup";
					}
				} else {
					return "hasReadyQuit";
				}

			} else {
				return "failure";
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

		return jsonInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.activitycenter.ActivityCenterDao#updateReadNum
	 * (int)
	 */
	@Override
	public String updateReadNum(int id) {
		String sql = "UPDATE activity_center SET read_num = read_num + 1 WHERE id = "
				+ id;

		System.out.println("sql = " + sql);

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.activitycenter.ActivityCenterDao#get(java
	 * .lang.String)
	 */
	@Override
	public String get(String userId) {
		String sql = "SELECT * FROM activity_center AS a LEFT OUTER JOIN activity_group AS b ON a.group_id=b.id";

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

			jsonInfo = resultSetToJsonPagination(rs, userId);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

	/**
	 * @param rs
	 * @param userId
	 * @return
	 */
	private String resultSetToJsonPagination(ResultSet rs, String userId)
			throws SQLException, JSONException {
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
			System.out.println("jsonObj.toString() = " + jsonObj.toString());
			System.out.println("contains= "
					+ (jsonObj.toString().contains("\\\"" + userId + "\\\"")));
			if (!jsonObj.toString().contains("\\\"" + userId + "\\\"")) {
				continue;
			}
			array.put(jsonObj);
		}
		JSONObject json = new JSONObject();
		json.put("records", array);

		json.put("pageSize", count);

		return json.toString();
	}

}
