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
	    // *******************进行版本查询****************
	    log.debug("*****************查询版本*********************");
	    log.debug("开始查询......");

	    long starTtime = System.currentTimeMillis(); // 获取系统当前时间，用于统计所花费的时间
	            InputStream in = null;
	            Properties pros = new Properties();
	            try {
	                if (null != fileName) {
	                  in=VolunteerTimeVersionServlet.class.getClassLoader().getResourceAsStream(fileName);
	    //得到当前类的路径，并把资源文件名作为输入流
	                    pros.load(in);
	              andrVersion.setVersionTip(pros.getProperty("version_tip"));
	              andrVersion.setVersionName(pros.getProperty("version_name"));
	              andrVersion.setVersionCode(Integer.parseInt(pros.getProperty("version_code")));
	              andrVersion.setDownloadUrl(pros.getProperty("download_url"));
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	                System.out.println("读取资源文件出错");
	            } finally {
	                try {
	                if (null != in) {
	                    in.close();
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    System.out.println("关闭流失败");
	                }
	            }

	    long endTime = System.currentTimeMillis(); // 获取系统当前时间，用于统计所花费的时间

	    String jstrObjectAndrVersion=JsonUtil.objectToJson(andrVersion);
	    log.debug("查询所花时间：" + (endTime - starTtime) + "毫秒");
	    log.debug("查询结果:" + jstrObjectAndrVersion);

	    PrintWriter writer = response.getWriter(); // 获取http输出流对象

	    log.debug("开始网络传输......");
	    starTtime = System.currentTimeMillis(); // 获取系统当前时间，用于统计所花费的时间
	    // 将json串传输到客户端
	    writer.write(jstrObjectAndrVersion);
	    endTime = System.currentTimeMillis(); // 获取系统当前时间，用于统计所花费的时间
	    log.debug("网络传输所用时间：" + (endTime - starTtime));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
