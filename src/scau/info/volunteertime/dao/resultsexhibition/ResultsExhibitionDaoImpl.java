/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-8-16
 */
package scau.info.volunteertime.dao.resultsexhibition;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import scau.info.volunteertime.tool.DatabaseConnection;
import scau.info.volunteertime.tool.factory.DatabaseConnectionFactory;
import scau.info.volunteertime.vo.Result;

/**
 * @author 蔡超敏
 * 
 */
public class ResultsExhibitionDaoImpl implements ResultsExhibitionDao {

	@Override
	public String delete(int id) {
		String sql = "";

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			sql = "delete from results where id = " + id;

			System.out.println("sql = " + sql);
			statement = conn.createStatement();
			if (statement.executeUpdate(sql) > 0) {
				return "true";
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
		return "false";
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

			// 输入
			JSONObject jsonObject = new JSONObject(ids);
			JSONArray jsonArray = jsonObject.getJSONArray("array");

			JSONObject jsonResult;

			// 数据库初始化
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao#getAll
	 * ()
	 */
	@Override
	public List<Result> getAll() {
		String sql = "SELECT * FROM results ORDER BY publishTime DESC ";

		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		List<Result> results = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			results = new ArrayList<Result>();

			Result result;
			while (rs.next()) {
				result = new Result();

				result.setId(rs.getInt("id"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setImage(rs.getString("image"));
				result.setEditor(rs.getString("editor"));
				result.setPublishTime(rs.getLong("publishTime"));
				result.setReadNum(rs.getInt("read_num"));
				result.setCommentNum(rs.getInt("comment_num"));

				results.add(result);
			}

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao#getAll
	 * (java.lang.String)
	 */
	@Override
	public List<Result> getAll(String keyword) {
		String sql;
		if (keyword == null || keyword.equals(""))
			sql = "SELECT * FROM results ORDER BY publishTime DESC ";
		else {
			sql = "SELECT * FROM results where title like '%" + keyword
					+ "%' ORDER BY publishTime DESC ";
		}

		System.out.println("sql = " + sql);
		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		List<Result> results = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			results = new ArrayList<Result>();

			Result result;
			while (rs.next()) {
				result = new Result();

				result.setId(rs.getInt("id"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setImage(rs.getString("image"));
				result.setEditor(rs.getString("editor"));
				result.setPublishTime(rs.getLong("publishTime"));
				result.setReadNum(rs.getInt("read_num"));
				result.setCommentNum(rs.getInt("comment_num"));

				results.add(result);
			}

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao#add
	 * (scau.info.volunteertime.vo.Result)
	 */
	@Override
	public String add(Result result) {
		String sql = "";

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			sql = "INSERT INTO results (title,content,image,editor,publishTime,read_num"
					+ ") VALUES('"
					+ result.getTitle()
					+ "','"
					+ result.getContent()
					+ "','"
					+ result.getImage()
					+ "','"
					+ result.getEditor()
					+ "',"
					+ System.currentTimeMillis()
					+ ",0);";

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
		return null;
	}
}
