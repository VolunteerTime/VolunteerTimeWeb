/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.dao.resultsexhibition;

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
 * @author �̳���
 * 
 */
public class ResultsExhibitionDaoImpl implements ResultsExhibitionDao {

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
		String sql = "SELECT * FROM results ORDER BY publishTime DESC LIMIT 0,"
				+ size;

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

	@Override
	public String getDown(long time, int currentPageSize) {
		String sql = "SELECT * FROM results WHERE publishTime < " + time
				+ " ORDER BY publishTime DESC LIMIT 0," + currentPageSize;

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
			rs = statement.executeQuery(sql);

			jsonInfo = resultSetToJsonPagination(rs, currentPageSize);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

	@Override
	public String getUp(long time, int currentPageSize) {
		String sql = "SELECT * FROM results WHERE publishTime > " + time
				+ " ORDER BY publishTime DESC";

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
			rs = statement.executeQuery(sql);

			jsonInfo = resultSetToJsonPagination(rs, currentPageSize);

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonInfo;
	}

	@Override
	public String checkDate(String ids) {
		try {
			Connection conn = null;
			Statement statement;
			ResultSet rs;
			DatabaseConnection dbc = null;

			// ����
			JSONObject jsonObject = new JSONObject(ids);
			JSONArray jsonArray = jsonObject.getJSONArray("array");

			JSONObject jsonResult;

			// ���ݿ��ʼ��
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();

			JSONArray array = new JSONArray();
			JSONObject jsonObj;

			for (int i = 0; i < jsonArray.length(); i++) {

				jsonResult = jsonArray.getJSONObject(i);
				int id = jsonResult.getInt("id");
				String sql = "SELECT id,read_num FROM results WHERE id = " + id;
				rs = statement.executeQuery(sql);

				System.out.println("checkDate id = " + id + "rs.getRow() = "
						+ rs.getRow());
				if (rs.next()) {
					jsonObj = new JSONObject();
					String value = rs.getString("id");
					jsonObj.put("id", value);
					System.out.println("checkDate value1 = " + value);
					value = rs.getString("read_num");
					jsonObj.put("read_num", value);
					System.out.println("checkDate value2 = " + value);
					array.put(jsonObj);
				} else {
					jsonObj = new JSONObject();
					jsonObj.put("id", id);
					jsonObj.put("read_num", -1);
					array.put(jsonObj);
				}

			}

			JSONObject json = new JSONObject();
			json.put("array", array);
			conn.close();
			dbc.close();
			return json.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
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
		// json����
		JSONArray array = new JSONArray();
		// ��ȡ����
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		int count = 0;

		// ����ResultSet�е�ÿ������
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			count++;
			// ����ÿһ��
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
	public String updateReadNum(int id) {
		String sql = "UPDATE results SET read_num = read_num + 1 WHERE id = "
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

}
