package scau.info.volunteertime.servlet.activitycenter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.activitycenter.ActivityCenterDao;
import scau.info.volunteertime.dao.activitycenter.ActivityCenterDaoImpl;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDaoImpl;
import scau.info.volunteertime.servlet.resultsexhibition.VolunteerTimeResultExhibitionServlet;

/**
 * Servlet implementation class VolunteerTimeActivityCenterServlet
 */
@WebServlet(description = "VolunteerTimeActivityCenterServlet", urlPatterns = { "/VolunteerTimeActivityCenterServlet" })
public class VolunteerTimeActivityCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(VolunteerTimeActivityCenterServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeActivityCenterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("action_type");
		if (actionType != null) {
			switch (Integer.parseInt(actionType.trim())) {
			case 0:
				sendNewData(request, response);
				break;
			case 1:
				checkLimitNumAndPartIn(request, response);
				break;
			case 2:
				quitPartIn(request, response);
				break;
			case 3:
				updateReadNum(request, response);
				break;

			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void updateReadNum(HttpServletRequest request,
			HttpServletResponse response) {
		String value = request.getParameter("id");
		int id = 0;

		if (value != null) {
			id = Integer.parseInt(value);
		}

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		ActivityCenterDao dao = new ActivityCenterDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateReadNum(id);
			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void quitPartIn(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String activityIdStr = request.getParameter("activityId");

		int activityId = 0;
		if (activityIdStr != null && !activityIdStr.equals("")) {
			activityId = Integer.parseInt(activityIdStr);
		}

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		ActivityCenterDao dao = new ActivityCenterDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.quitIn(userId, activityId);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void checkLimitNumAndPartIn(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String activityIdStr = request.getParameter("activityId");

		int activityId = 0;
		if (activityIdStr != null && !activityIdStr.equals("")) {
			activityId = Integer.parseInt(activityIdStr);
		}

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		ActivityCenterDao dao = new ActivityCenterDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.checkIn(userId, activityId);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void sendNewData(HttpServletRequest request,
			HttpServletResponse response) {

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		ActivityCenterDao dao = new ActivityCenterDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.get(Integer.MAX_VALUE);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
