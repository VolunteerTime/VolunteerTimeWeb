/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-27
 */
package scau.info.volunteertime.vo;

import java.util.List;

import com.chao.model.IModel;

/**
 * @author �̳���
 * 
 */
public class ActivityData implements IModel {

	private int id;// id

	private String title;// ����
	private String content;// ����
	private String image;// ͼƬ
	private String editor;// ����
	private long publishTime;// ʱ��
	private long endTime; // ����ʱ��
	private int limitNum; // ��������
	private int readNum;// �Ķ���
	private int groupId;// С��id
	private int participatorsNum;

	private String principalId;
	private String participators;

	private List<String> participatorList;

	/**
	 * @return the participatorList
	 */
	public List<String> getParticipatorList() {
		return participatorList;
	}

	/**
	 * @param participatorList
	 *            the participatorList to set
	 */
	public void setParticipatorList(List<String> participatorList) {
		this.participatorList = participatorList;
	}

	/**
	 * 
	 */
	public ActivityData() {
		System.out.println("ActivityData ...");
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @return the publishTime
	 */
	public long getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
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
	 * @return the limitNum
	 */
	public int getLimitNum() {
		return limitNum;
	}

	/**
	 * @param limitNum
	 *            the limitNum to set
	 */
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	/**
	 * @return the readNum
	 */
	public int getReadNum() {
		return readNum;
	}

	/**
	 * @param readNum
	 *            the readNum to set
	 */
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the participatorsNum
	 */
	public int getParticipatorsNum() {
		return participatorsNum;
	}

	/**
	 * @param participatorsNum
	 *            the participatorsNum to set
	 */
	public void setParticipatorsNum(int participatorsNum) {
		this.participatorsNum = participatorsNum;
	}

	/**
	 * @return the principalId
	 */
	public String getPrincipalId() {
		return principalId;
	}

	/**
	 * @param principalId
	 *            the principalId to set
	 */
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	/**
	 * @return the participators
	 */
	public String getParticipators() {
		return participators;
	}

	/**
	 * @param participators
	 *            the participators to set
	 */
	public void setParticipators(String participators) {
		this.participators = participators;
	}

}
