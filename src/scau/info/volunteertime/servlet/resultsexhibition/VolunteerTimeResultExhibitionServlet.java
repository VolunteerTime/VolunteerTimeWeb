/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.servlet.resultsexhibition;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDaoImpl;

/**
 * Servlet implementation class VolunteerTimeResultExhibitionServlet
 */
/**
 * @author �̳���
 * 
 */
@WebServlet(description = "VolunteerTimeResultExhibitionServlet", urlPatterns = { "/VolunteerTimeResultExhibitionServlet" })
public class VolunteerTimeResultExhibitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(VolunteerTimeResultExhibitionServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeResultExhibitionServlet() {
		super();
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
			}
		}
	}

	private void sendNewData(HttpServletRequest request,
			HttpServletResponse response) {
		int currentPageSize = 0;
		String currentPageNumberParam = request.getParameter("currentPageSize");
		if (currentPageNumberParam != null) {
			currentPageSize = Integer.parseInt(currentPageNumberParam);
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			if (currentPageSize != 0)
				rsStr = dao.get(currentPageSize);
			else
				rsStr = dao.get(5);

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
