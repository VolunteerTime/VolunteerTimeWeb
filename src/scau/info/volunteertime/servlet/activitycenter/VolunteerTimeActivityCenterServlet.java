package scau.info.volunteertime.servlet.activitycenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.activitycenter.ActivityCenterDao;
import scau.info.volunteertime.dao.activitycenter.ActivityCenterDaoImpl;
import scau.info.volunteertime.vo.ActivityData;

import com.chao.model.User;
import com.chao.tool.impl.IPTimeStamp;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

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
			case 4:
				sendNewDataByUserId(request, response);
				break;
			case 5:
				choicePrincipal(request, response);
				break;
			case 6:
				addActivity(request, response);
				break;

			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void addActivity(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("doPublishMessage-init");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			SmartUpload smartUpload = new SmartUpload();

			smartUpload.initialize(this.getServletConfig(), request, response);
			// smartUpload.setMaxFileSize(5000000 * 5000000 * 5000000);
			// smartUpload.setAllowedFilesList("JPG,png,jpg,gif,GIF,bmp");

			// smartUpload.setAllowedFilesList("");
			smartUpload.upload();

			File file = smartUpload.getFiles().getFile(0);

			System.out.println(file.getFileName() + ":" + file.getFileExt());
			if (file.getFileExt().matches("JPG")
					|| file.getFileExt().matches("jpg")
					|| file.getFileExt().matches("gif")
					|| file.getFileExt().matches("GIF")) {
				System.out.println(file.getFileName());
				IPTimeStamp iTimeStamp = new IPTimeStamp(
						request.getRemoteAddr());
				String ext = file.getFileExt();
				String fileName = iTimeStamp.getIPTimeRand() + "." + ext;
				String path = getServletContext().getRealPath("/") + "images"
						+ java.io.File.separator;
				String url = path + fileName;
				String saveurl = "images/" + fileName;
				java.io.File pathFile = new java.io.File(path);// �����ļ���
				pathFile.mkdirs();

				System.out.println("url = " + url);
				// String path =
				// "D:\\Program Files (x86)\\Java\\ECLIPSE_HOME\\android workspace2\\";
				file.saveAs(url);
				// file.saveAs(path + "images" + java.io.File.separator +
				// fileName);

				// ������Ϣ
				String title = smartUpload.getRequest().getParameter(
						"inputpublishActivityTitle");
				String content = smartUpload.getRequest().getParameter(
						"textfieldPublishActivityContent");

				// String startTime = smartUpload.getRequest().getParameter(
				// "inputpublisActivitySTime");
				String endTime = smartUpload.getRequest().getParameter(
						"inputpublisActivityETime");

				String limitNum = smartUpload.getRequest().getParameter(
						"inputpublishActivityLimit");

				System.out.println("startTime= " + ":" + endTime
						+ " limitNum = " + limitNum);
				System.out.println("title+userid= " + ":" + title
						+ " saveurl = " + saveurl);

				title = new String(title.trim().getBytes(), "utf-8");
				content = new String(content.trim().getBytes(), "utf-8");

				// title = new String(title.getBytes("GBK"), "utf-8");
				// content = new String(content.getBytes("GBK"), "utf-8");

				User user = (User) request.getSession()
						.getAttribute("userinfo");
				String userid;
				if (user == null)
					userid = "admin";
				else
					userid = user.getUserid();
				System.out.println("startTime= " + ":" + endTime
						+ " limitNum = " + limitNum);
				System.out.println("title+userid= " + ":" + title
						+ " saveurl = " + saveurl);

				ActivityData activityData = new ActivityData();

				activityData.setTitle(title);
				activityData.setContent(content);
				activityData.setEditor(userid);
				activityData.setLimitNum(Integer.parseInt(limitNum));

				activityData.setImage(saveurl);

				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				// startTime = startTime.replace("T", " ");
				endTime = endTime.replace("T", " ");
				Date date = null;
				try {
					// date = formatter.parse(startTime);
					// activityData.setPublishTime(date.getTime());
					date = formatter.parse(endTime);
					activityData.setEndTime(date.getTime());
					System.out.println("TIME:::" + date);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				activityData.setPublishTime(System.currentTimeMillis());
				ActivityCenterDao dao = new ActivityCenterDaoImpl();
				dao.add(activityData);

				out.print("alert(\"ok\")");
			}

			response.sendRedirect("/VolunteerTimeWeb/manageActivityCenter.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("alert(\"no\")");
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void choicePrincipal(HttpServletRequest request,
			HttpServletResponse response) {
		request.getParameterValues("userId");
		String userId = request.getParameter("userId");
		System.out.println("userId = " + userId);
		String value = request.getParameter("id");
		int id = 0;

		if (value != null) {
			id = Integer.parseInt(value);
		}
		System.out.println("id = " + id);
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

			rsStr = dao.choiceMainer(id, userId);

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
	private void sendNewDataByUserId(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = request.getParameter("userId");
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

			rsStr = dao.get(userId);

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
