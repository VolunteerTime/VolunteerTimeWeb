/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.dao.resultsexhibition;

import java.util.List;

import scau.info.volunteertime.vo.Result;

/**
 * @author �̳���
 * 
 */
public interface ResultsExhibitionDao {

	public abstract String add(Result result);

	public abstract String delete(int id);

	public abstract String get(int size);

	public abstract List<Result> getAll();
	public abstract List<Result> getAll(String keyword);

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
