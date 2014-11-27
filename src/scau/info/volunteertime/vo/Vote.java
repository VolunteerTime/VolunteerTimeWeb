/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-27
 */
package scau.info.volunteertime.vo;

import java.util.ArrayList;

import com.chao.model.IModel;

/**
 * @author �̳���
 * 
 */
public class Vote implements IModel{

	private int id; // ͶƱ���
	private boolean single; // ��ѡ��true����ѡ��false
	private String title; // ���۵�����
	private ArrayList<String> choice; // ͶƱѡ��
	private ArrayList<Integer> votes; // ��Ӧ��ͶƱ���
	private boolean checked = false; // �Ƿ��Ѿ�ѡ����ˣ��ӱ������ݿ���,���
	public boolean isChange = false; // �ж��Ƿ����˰����������������ô����true�����true�����ı䣬���ܿ��ֻ���false
	public long date; // ����ʱ��

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
