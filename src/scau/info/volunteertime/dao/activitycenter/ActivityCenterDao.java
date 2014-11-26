/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-24
 */
package scau.info.volunteertime.dao.activitycenter;

/**
 * @author �̳���
 * 
 */
public interface ActivityCenterDao {
	public abstract String add();

	public abstract String delete();

	public abstract String get(int size);

	public abstract String checkIn(String userId, int activityId);

	/**
	 * @param userId
	 * @param activityId
	 * @return String
	 */
	public abstract String quitIn(String userId, int activityId);

	/**
	 * @param id
	 * @return String
	 */
	public abstract String updateReadNum(int id);

	/**
	 * @param userId
	 * @return
	 */
	public abstract String get(String userId);

}
