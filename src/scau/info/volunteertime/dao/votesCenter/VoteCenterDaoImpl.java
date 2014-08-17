/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014年8月16日
 */
package scau.info.volunteertime.dao.votesCenter;

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
public class VoteCenterDaoImpl {
	int SIZE=10;//每次获取10 条数据
	
	
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}
 
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	public String get(int id) {
		String sql = "SELECT * FROM votedata  where id>"+id+" limit 0,"+SIZE;
 
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
			jsonInfo = resultSetToJsonPagination(rs, SIZE);

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

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject(); 
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value;

				System.out.println("adsfasdddddddddddddf  "+columnName);
				// if(columnName.contains("PIC")||columnName.contains("pic"))
				// value =
				// Environment.getImageServer()+rs.getString(columnName);
				// else
				value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		JSONObject json = new JSONObject();
		json.put("records", array);

		json.put("pageSize", size);

		return array.toString();
	}

}
