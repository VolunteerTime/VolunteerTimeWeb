package scau.info.volunteertime.servlet.version;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.vo.Version;
import util.JsonUtil;

/**
 * Servlet implementation class VolunteerTimeVersionServlet
 */
@WebServlet(description = "VolunteerTimeVersionServlet", urlPatterns = { "/VolunteerTimeVersionServlet" })
public class VolunteerTimeVersionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  private static final Log log = LogFactory.getLog(VolunteerTimeVersionServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerTimeVersionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName="latest_version.properties";
	    Version andrVersion=new Version();
	    // *******************���а汾��ѯ****************
	    log.debug("*****************��ѯ�汾*********************");
	    log.debug("��ʼ��ѯ......");

	    long starTtime = System.currentTimeMillis(); // ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
	            InputStream in = null;
	            Properties pros = new Properties();
	            try {
	                if (null != fileName) {
	                  in=VolunteerTimeVersionServlet.class.getClassLoader().getResourceAsStream(fileName);
	    //�õ���ǰ���·����������Դ�ļ�����Ϊ������
	                    pros.load(in);
	              andrVersion.setVersionTip(pros.getProperty("version_tip"));
	              andrVersion.setVersionName(pros.getProperty("version_name"));
	              andrVersion.setVersionCode(Integer.parseInt(pros.getProperty("version_code")));
	              andrVersion.setDownloadUrl(pros.getProperty("download_url"));
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	                System.out.println("��ȡ��Դ�ļ�����");
	            } finally {
	                try {
	                if (null != in) {
	                    in.close();
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    System.out.println("�ر���ʧ��");
	                }
	            }

	    long endTime = System.currentTimeMillis(); // ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��

	    String jstrObjectAndrVersion=JsonUtil.objectToJson(andrVersion);
	    log.debug("��ѯ����ʱ�䣺" + (endTime - starTtime) + "����");
	    log.debug("��ѯ���:" + jstrObjectAndrVersion);

	    PrintWriter writer = response.getWriter(); // ��ȡhttp���������

	    log.debug("��ʼ���紫��......");
	    starTtime = System.currentTimeMillis(); // ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
	    // ��json�����䵽�ͻ���
	    writer.write(jstrObjectAndrVersion);
	    endTime = System.currentTimeMillis(); // ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
	    log.debug("���紫������ʱ�䣺" + (endTime - starTtime));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
