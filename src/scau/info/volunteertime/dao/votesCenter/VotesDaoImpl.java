/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.votesCenter;

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
import scau.info.volunteertime.vo.Vote;

/**
 * @author 蔡超敏
 * 
 */
public class VotesDaoImpl implements VotesDao {

	@Override
	public String getNewVotes() {
		String sql = "SELECT * FROM votes";

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
	 * scau.info.volunteertime.dao.votesCenter.VotesDao#updateChoice(java.lang
	 * .String, int, java.lang.String)
	 */
	@Override
	public String updateChoice(String userId, int id, String votes) {
		String sql;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			sql = "SELECT user_ids FROM votes where id = " + id;
			rs = statement.executeQuery(sql);
			rs.next();
			String user_ids = rs.getString("user_ids");
			System.out.println("user_ids user_ids = " + user_ids);
			String jsonStr = "";
			try {
				JSONObject json = new JSONObject(user_ids);
				JSONArray array = json.getJSONArray("userIds");
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("userId", userId);
				array.put(jsonObj);
				json = new JSONObject();
				json.put("userIds", array);
				jsonStr = json.toString();
			} catch (JSONException e) {

				System.out.println("updateChoice error");
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("userId", userId);
				JSONArray array = new JSONArray();
				array.put(jsonObj);
				JSONObject json = new JSONObject();
				json.put("userIds", array);
				jsonStr = json.toString();

				System.out.println("updateChoice jsonStr = " + jsonStr);
				e.printStackTrace();
			}

			System.out.println("jsonStr = " + jsonStr);

			sql = "UPDATE votes SET votes = '" + votes + "', user_ids = '"
					+ jsonStr + "' WHERE id = " + id;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.votesCenter.VotesDao#getAll(java.lang.String)
	 */
	@Override
	public List<Vote> getAll(String keyword) {
		String sql;
		if (keyword == null || keyword.equals(""))
			sql = "SELECT * FROM votes ORDER BY publish_date DESC ";
		else {
			sql = "SELECT * FROM votes where content like '%" + keyword
					+ "%' ORDER BY publish_date DESC ";
		}

		System.out.println("sql = " + sql);
		String jsonInfo = null;
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		DatabaseConnection dbc = null;
		List<Vote> votes = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			votes = new ArrayList<Vote>();

			Vote vote;
			while (rs.next()) {
				vote = new Vote();

				vote.setId(rs.getInt("id"));
				vote.setTitle(rs.getString("content"));
				vote.setVoteStr(rs.getString("votes"));
				vote.setChoiceStr(rs.getString("choice"));
				vote.setEditor(rs.getString("editor_id"));
				vote.setDate(rs.getLong("publish_date"));

				// 解析data
				ArrayList<String> strings = new ArrayList<String>();
				try {
					JSONArray array;
					JSONObject json = new JSONObject(vote.getChoiceStr());
					array = json.getJSONArray("choices");
					int len = array.length();
					for (int j = 0; j < len; j++) {
						JSONObject jsonObj = array.getJSONObject(j);
						String choiceByJson = jsonObj.getString("choice");
						strings.add(choiceByJson);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				vote.setChoice(strings);

				ArrayList<Integer> integers = new ArrayList<Integer>();
				int sum = 0;
				try {
					JSONArray array;
					JSONObject json = new JSONObject(vote.getVoteStr());
					array = json.getJSONArray("votes");
					int len = array.length();
					for (int j = 0; j < len; j++) {
						JSONObject jsonObj = array.getJSONObject(j);
						int choiceByJson = jsonObj.getInt("vote");
						sum += choiceByJson;
						integers.add(choiceByJson);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				vote.setVotes(integers);
				vote.setSum(sum);

				votes.add(vote);
			}

			conn.close();
			dbc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return votes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scau.info.volunteertime.dao.votesCenter.VotesDao#checkDate(java.lang.
	 * String, java.lang.String, int, java.lang.String[])
	 */
	@Override
	public String checkDate(String userid, String title, int type,
			String[] choices) {
		String sql = "";

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();

			JSONObject jsonObjChoices = new JSONObject();
			JSONObject jsonObjVotes = new JSONObject();

			JSONArray arrayChoices = new JSONArray();
			JSONArray arrayVotes = new JSONArray();

			JSONObject objectChoice, objectVote;

			for (String value : choices) {
				objectChoice = new JSONObject();
				objectChoice.put("choice", value);
				objectVote = new JSONObject();
				objectVote.put("vote", 0);
				arrayChoices.put(objectChoice);
				arrayVotes.put(objectVote);
			}
			jsonObjChoices.put("choices", arrayChoices);
			jsonObjVotes.put("votes", arrayVotes);

			String choiceStr = jsonObjChoices.toString();
			String voteStr = jsonObjVotes.toString();

			sql = "INSERT INTO votes (type,content,choice,votes,user_ids,editor_id,publish_date,end_date"
					+ ") VALUES('"
					+ type
					+ "','"
					+ title
					+ "','"
					+ choiceStr
					+ "','"
					+ voteStr
					+ "','','"
					+ userid
					+ "',"
					+ System.currentTimeMillis()
					+ ","
					+ System.currentTimeMillis() + ");";

			System.out.println("sql = " + sql);
			statement = conn.createStatement();
			statement.executeUpdate(sql);

			return "true";
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

	/* (non-Javadoc)
	 * @see scau.info.volunteertime.dao.votesCenter.VotesDao#delete(int)
	 */
	@Override
	public String delete(int id) {
		String sql = "";

		Connection conn = null;
		Statement statement;
		DatabaseConnection dbc = null;
		try {
			dbc = DatabaseConnectionFactory.getDatabaseConnection();
			conn = dbc.getConnection();
			sql = "delete from votes where id = " + id;

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

}
