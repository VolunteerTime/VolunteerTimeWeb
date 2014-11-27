/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-27
 */
package scau.info.volunteertime.vo;

import java.util.ArrayList;

import com.chao.model.IModel;

/**
 * @author 蔡超敏
 * 
 */
public class Vote implements IModel{

	private int id; // 投票编号
	private boolean single; // 单选是true，复选是false
	private String title; // 评论的主题
	private ArrayList<String> choice; // 投票选项
	private ArrayList<Integer> votes; // 对应的投票结果
	private boolean checked = false; // 是否已经选择过了，从本地数据库获得,如果
	public boolean isChange = false; // 判断是否按下了按键，如果按下了那么会变成true，变成true画面会改变，但很快又会变成false
	public long date; // 更新时间

	public long endTime;

	public String userIds;
	public String choiceStr;
	public String voteStr;

	private String editor;

	private int type;

	private int sum;

	/**
	 * @return the sum
	 */
	public int getSum() {
		return sum;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(int sum) {
		this.sum = sum;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the single
	 */
	public boolean isSingle() {
		return single;
	}

	/**
	 * @param single
	 *            the single to set
	 */
	public void setSingle(boolean single) {
		this.single = single;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the choice
	 */
	public ArrayList<String> getChoice() {
		return choice;
	}

	/**
	 * @param choice
	 *            the choice to set
	 */
	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}

	/**
	 * @return the votes
	 */
	public ArrayList<Integer> getVotes() {
		return votes;
	}

	/**
	 * @param votes
	 *            the votes to set
	 */
	public void setVotes(ArrayList<Integer> votes) {
		this.votes = votes;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the isChange
	 */
	public boolean isChange() {
		return isChange;
	}

	/**
	 * @param isChange
	 *            the isChange to set
	 */
	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the choiceStr
	 */
	public String getChoiceStr() {
		return choiceStr;
	}

	/**
	 * @param choiceStr
	 *            the choiceStr to set
	 */
	public void setChoiceStr(String choiceStr) {
		this.choiceStr = choiceStr;
	}

	/**
	 * @return the voteStr
	 */
	public String getVoteStr() {
		return voteStr;
	}

	/**
	 * @param voteStr
	 *            the voteStr to set
	 */
	public void setVoteStr(String voteStr) {
		this.voteStr = voteStr;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
