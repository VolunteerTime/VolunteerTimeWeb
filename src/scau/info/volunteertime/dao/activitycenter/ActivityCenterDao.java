/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-24
 */
package scau.info.volunteertime.dao.activitycenter;

/**
 * @author 蔡超敏
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
