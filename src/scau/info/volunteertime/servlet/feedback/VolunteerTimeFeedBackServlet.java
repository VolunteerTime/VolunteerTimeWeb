package scau.info.volunteertime.servlet.feedback;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.feedback.FeedBackDao;
import scau.info.volunteertime.dao.feedback.FeedBackDaoImpl;

/**
 * Servlet implementation class VolunteerTimeFeedBackServlet
 */
@WebServlet(description = "VolunteerTimeFeedBackServlet", urlPatterns = { "/VolunteerTimeFeedBackServlet" })
public class VolunteerTimeFeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(VolunteerTimeFeedBackServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeFeedBackServlet() {
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
				getFeedBack(request, response);
				break;
			case 1:
				deleteFeedBack(request, response);
				break;

			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void deleteFeedBack(HttpServletRequest request,
			HttpServletResponse response) {
		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");
		String idStr = request.getParameter("id");
		int id = 0;
		if (idStr != null)
			id = Integer.parseInt(idStr);

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		FeedBackDao dao = new FeedBackDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.delete(id);

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
	private void getFeedBack(HttpServletRequest request,
			HttpServletResponse response) {

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");
		String userId = request.getParameter("userId");
		String content = request.getParameter("content");


		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		FeedBackDao dao = new FeedBackDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.commitUserFeedBack(userId, content);

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
