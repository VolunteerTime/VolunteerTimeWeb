/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-8-16
 */
package scau.info.volunteertime.dao.resultsexhibition;

/**
 * @author 蔡超敏
 * 
 */
public interface ResultsExhibitionDao {

	public abstract String add();

	public abstract String delete();

	public abstract String get(int size);

	/**
	 * @param time
	 * @param currentPageSize
	 * @return String
	 */
	public abstract String getDown(long time, int currentPageSize);

	/**
	 * @param time
	 * @param currentPageSize
	 * @return String
	 */
	public abstract String getUp(long time, int currentPageSize);

	public abstract String checkDate(String ids);

	public abstract String updateReadNum(int id);

}
