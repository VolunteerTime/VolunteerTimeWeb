package com.chao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chao.dao.factory.DAOFactory;
import com.chao.model.Message;
import com.chao.model.User;
import com.chao.tool.impl.IPTimeStamp;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

public class MessageServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public MessageServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html");
    Integer sign = Integer.valueOf(request.getParameter("sign"));
    if (sign == 0) {
      this.doSendSimpleMessagesCheck(request, response);
    } else if (sign == 1) {
      this.doClickDeleteMessage(request, response);
    } else if (sign == 2) {
      this.doBatchMessage(request, response);
    } else if (sign == 3) {
      this.doPublishMessage(request, response);
    } else if (sign == 4) {
      this.doPublishStaticMessage(request, response);
    } else if (sign == 5) {
      this.doPublishNews(request, response);
    } else {
      System.out.println("没干什么");
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doPublishNews(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("doPublishNews-init");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      SmartUpload smartUpload = new SmartUpload();
      smartUpload.initialize(this, request, response);
      // smartUpload.setAllowedFilesList("");
      smartUpload.upload();
      File file = smartUpload.getFiles().getFile(0);
      System.out.println(file.getFileName() + ":" + file.getFileExt());
      if (file.getFileExt().matches("JPG") || file.getFileExt().matches("jpg")
          || file.getFileExt().matches("gif") || file.getFileExt().matches("GIF")) {
        System.out.println(file.getFileName());
        IPTimeStamp iTimeStamp = new IPTimeStamp(request.getRemoteAddr());
        String ext = file.getFileExt();
        String fileName = iTimeStamp.getIPTimeRand() + "." + ext;
        String url = "images" + java.io.File.separator + fileName;
        file.saveAs(getServletContext().getRealPath("/") + "images" + java.io.File.separator
            + fileName);


        // 其他消息
        String title = smartUpload.getRequest().getParameter("smallinputnewstitle");
        String content = smartUpload.getRequest().getParameter("textfieldnewscontent");
        String category = smartUpload.getRequest().getParameter("dropdownnewsclass");

        User user = (User) request.getSession().getAttribute("userinfo");

        String userid = user.getUserid();
        System.out.println("userid= " + userid);
        System.out.println("title= " + title);

        Message message = new Message();

        message.setTitle(title);
        message.setContent(content);
        message.setUserid(userid);
        if ("option1".equals(category)) {
          message.setCategory(5);
        } else if ("option2".equals(category)) {
          message.setCategory(6);
        } else if ("option3".equals(category)) {
          message.setCategory(7);
        }
        message.setImage(url);

        DAOFactory.getMessageDaoInstance().add(message);


        out.print("alert(\"ok\")");
      }
      response.sendRedirect("/ScauActivityWeb/admin/manageScauInfo.jsp");
    } catch (Exception e) {
      e.printStackTrace();
      try {
        out.print("alert(\"no\")");
        response.sendRedirect("/ScauActivityWeb/admin/manageScauInfo.jsp");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doPublishStaticMessage(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("doPublishStaticMessage-init");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      SmartUpload smartUpload = new SmartUpload();
      smartUpload.initialize(this, request, response);
      // smartUpload.setAllowedFilesList("");
      smartUpload.upload();
      File file = smartUpload.getFiles().getFile(0);
      System.out.println(file.getFileName() + ":" + file.getFileExt());
      if (file.getFileExt().matches("JPG") || file.getFileExt().matches("jpg")
          || file.getFileExt().matches("gif") || file.getFileExt().matches("GIF")) {
        System.out.println(file.getFileName());
        IPTimeStamp iTimeStamp = new IPTimeStamp(request.getRemoteAddr());
        String ext = file.getFileExt();
        String fileName = iTimeStamp.getIPTimeRand() + "." + ext;
        String url = "images" + java.io.File.separator + fileName;
        System.out.println(getServletContext().getRealPath("/") + "images" + java.io.File.separator
            + fileName);
        file.saveAs(getServletContext().getRealPath("/") + "images" + java.io.File.separator
            + fileName);


        // 其他消息
        String title = smartUpload.getRequest().getParameter("smallinputtitle");
        String[] checkClass = smartUpload.getRequest().getParameterValues("checkbox1class");
        String content = smartUpload.getRequest().getParameter("largeinputcontent");
        String category = smartUpload.getRequest().getParameter("dropdownreplacenum");

        User user = (User) request.getSession().getAttribute("userinfo");

        String userid = user.getUserid();
        System.out.println("doPublishStaticMessage:userid= " + userid);
        System.out.println("title= " + title);

        Message message = new Message();

        message.setTitle(title);
        message.setContent(content);
        message.setUserid(userid);
        if ("option1".equals(category)) {
          message.setReplacenum(1);
        } else if ("option2".equals(category)) {
          message.setReplacenum(2);
        } else if ("option3".equals(category)) {
          message.setReplacenum(3);
        }
        message.setImage(url);

        for (int i = 0; checkClass != null && i < checkClass.length; i++) {
          if ("1".equals(checkClass[i])) {
            message.setCategory(1);
            DAOFactory.getMessageDaoInstance().add(message);
          } else if ("2".equals(checkClass[i])) {
            message.setCategory(2);
            DAOFactory.getMessageDaoInstance().add(message);
          }
          System.out.println(checkClass[i]);
        }


        out.print("alert(\"ok\")");
      }
      response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
    } catch (Exception e) {
      e.printStackTrace();
      try {
        out.print("alert(\"no\")");
        response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doPublishMessage(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("doPublishMessage-init");
    String index = request.getParameter("index");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      SmartUpload smartUpload = new SmartUpload();
      smartUpload.initialize(this, request, response);
      // smartUpload.setAllowedFilesList("");
      smartUpload.upload();
      File file = smartUpload.getFiles().getFile(0);
      System.out.println(file.getFileName() + ":" + file.getFileExt());
      if (file.getFileExt().matches("JPG") || file.getFileExt().matches("jpg")
          || file.getFileExt().matches("gif") || file.getFileExt().matches("GIF")) {
        System.out.println(file.getFileName());
        IPTimeStamp iTimeStamp = new IPTimeStamp(request.getRemoteAddr());
        String ext = file.getFileExt();
        String fileName = iTimeStamp.getIPTimeRand() + "." + ext;
        String url = "images" + java.io.File.separator + fileName;
        file.saveAs(getServletContext().getRealPath("/") + "images" + java.io.File.separator
            + fileName);


        // 其他消息
        String title = smartUpload.getRequest().getParameter("inputpublishMessageTitle");
        String content = smartUpload.getRequest().getParameter("textfieldPublishMessage");
        String category = smartUpload.getRequest().getParameter("dropdownPublishMessageClass");

        User user = (User) request.getSession().getAttribute("userinfo");

        String userid = user.getUserid();
        System.out.println("title+userid= " + userid + ":" + title);

        Message message = new Message();

        message.setTitle(title);
        message.setContent(content);
        message.setUserid(userid);
        if ("option1".equals(category)) {
          message.setCategory(3);
        } else if ("option2".equals(category)) {
          message.setCategory(4);
        }
        message.setImage(url);

        DAOFactory.getMessageDaoInstance().add(message);

        out.print("alert(\"ok\")");
      }
      if (index != null && !"".equals(index)) {
        response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
      } else {
        response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
      }
    } catch (Exception e) {
      e.printStackTrace();
      try {
        out.print("alert(\"no\")");
        if (index != null && !"".equals(index)) {
          response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
        } else {
          response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
        }
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

  }

  /**
   * @param request
   * @param response
   */
  private void doBatchMessage(HttpServletRequest request, HttpServletResponse response) {
    String[] ids = request.getParameterValues("checkboxmessage");
    String operator = request.getParameter("dropdownmessageoperator");
    String aoperator = request.getParameter("dropdownnewoperator");
    String index = request.getParameter("index");
    if (operator == null || operator.equals("")) {
      operator = aoperator;
    }
    if (ids != null && ids.length > 0)
      if (operator != null && "option2".equals(operator)) {
        for (int i = 0; i < ids.length; i++) {
          System.out.println(Integer.parseInt(ids[i]) + ":" + ids[i]);
          Message message;
          try {
            message =
                (Message) DAOFactory.getMessageDaoInstance().findById(Integer.parseInt(ids[i]));
            if (message != null) {
              DAOFactory.getMessageDaoInstance().delete(Integer.parseInt(ids[i]));

            } else {
            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        try {
          if (index != null && !"".equals(index)) {
            response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
          } else if (operator.equals(aoperator)) {
            response.sendRedirect("/ScauActivityWeb/admin/manageScauInfo.jsp");
          } else {
            response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
          }
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        try {
          if (index != null && !"".equals(index)) {
            response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
          } else if (operator.equals(aoperator)) {
            response.sendRedirect("/ScauActivityWeb/admin/manageScauInfo.jsp");
          } else {
            response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
          }
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    else
      try {
        if (index != null && !"".equals(index)) {
          response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
        } else if (operator.equals(aoperator)) {
          response.sendRedirect("/ScauActivityWeb/admin/manageScauInfo.jsp");
        } else {
          response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

  /**
   * @param request
   * @param response
   * @throws IOException
   */
  private void doClickDeleteMessage(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    System.out.println("doClickDeleteMessage-init");
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println(id);
    try {
      Message message = (Message) DAOFactory.getMessageDaoInstance().findById(id);
      if (message != null) {
        System.out.println(id);
        DAOFactory.getMessageDaoInstance().delete(id);
        out.print(true);
        // response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
      } else {
        out.print(false);
        // response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
      }
      out.close();
    } catch (Exception e) {
      System.out.println("doClickDeleteMessage-err");
      e.printStackTrace();
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doSendSimpleMessagesCheck(HttpServletRequest request, HttpServletResponse response) {
    String index = request.getParameter("index");
    String title = request.getParameter("texttitle");
    String content = request.getParameter("textfieldmessage");
    String operator = request.getParameter("dropdownmessageclass");
    // Session s=r
    User user = (User) request.getSession().getAttribute("userinfo");
    Message message = null;
    if (operator != null && "option1".equals(operator)) {
      message = new Message();
      message.setTitle(title);
      message.setContent(content);
      message.setCategory(3);
      if (user != null) {
        message.setUserid(user.getUserid());
      } else {
        message.setUserid("12345678");
      }
      try {
        DAOFactory.getMessageDaoInstance().add(message);
        if (index != null && !"".equals(index)) {
          response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
        } else {
          response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else if (operator != null && "option2".equals(operator)) {
      message = new Message();
      message.setTitle(title);
      message.setContent(content);
      message.setCategory(4);
      if (user != null) {
        message.setUserid(user.getUserid());
      } else {
        message.setUserid("87654321");
      }
      try {
        DAOFactory.getMessageDaoInstance().add(message);
        if (index != null && !"".equals(index)) {
          response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
        } else {
          response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        if (index != null && !"".equals(index)) {
          response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
        } else {
          response.sendRedirect("/ScauActivityWeb/admin/manageMess.jsp");
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
