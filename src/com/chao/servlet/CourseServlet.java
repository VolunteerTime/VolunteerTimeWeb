package com.chao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chao.dao.factory.DAOFactory;
import com.chao.model.Course;
import com.chao.model.User;
import com.chao.model.UserCourse;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CourseServlet() {
    super();
    // TODO Auto-generated constructor stub
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
      this.doAddCourse(request, response);
    } else if (sign == 1) {
      this.doDeleteCourse(request, response);
    } else if (sign == 2) {
      this.doBatchCourse(request, response);
    } else if (sign == 3) {
      this.doUserAddCourse(request, response);
    } else if (sign == 4) {
      this.doUserDeleteCourse(request, response);
    } else {
      System.out.println(sign);
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doUserDeleteCourse(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("doUserDeleteCourse-init");
    String courseid = request.getParameter("courseid");
    User user = (User) request.getSession().getAttribute("commonUserinfo");
    System.out.println(courseid + ":" + user.getUserid());
    PrintWriter out;
    try {
      out = response.getWriter();
      UserCourse userCourse =
          DAOFactory.getUserCourseDaoInstance().findByUserAndCourse(user.getUserid(), courseid);
      if (userCourse != null) {
        DAOFactory.getUserCourseDaoInstance().delete(userCourse.getId());
        out.print(true);
        System.out.println("doUserDeleteCourse-success");
        // response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
      } else {
        out.print(false);
        System.out.println("doUserDeleteCourse-failure");
        // response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
      }
      out.close();
    } catch (Exception e) {
      System.out.println("doUserDeleteCourse-err");
      e.printStackTrace();
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doUserAddCourse(HttpServletRequest request, HttpServletResponse response) {
    String courseid = request.getParameter("courseid");
    User user = (User) request.getSession().getAttribute("commonUserinfo");
    System.out.println(courseid + ":" + user.getUserid());
    System.out.println("doPublishNews-init");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      if (DAOFactory.getUserCourseDaoInstance().findByUserAndCourse(user.getUserid(), courseid) != null) {
        out.print(false);
      } else {
        UserCourse userCourse = new UserCourse();
        userCourse.setUserid(user.getUserid());
        userCourse.setCourseid(courseid);
        DAOFactory.getUserCourseDaoInstance().add(userCourse);
        out.print(true);
      }
    } catch (Exception e) {
      out.print(false);
      e.printStackTrace();
    }

  }

  /**
   * @param request
   * @param response
   */
  private void doBatchCourse(HttpServletRequest request, HttpServletResponse response) {
    String[] ids = request.getParameterValues("checkboxcourse");
    String operator = request.getParameter("dropdowncourseoperator");
    if (ids != null && ids.length > 0)
      if (operator != null && "option2".equals(operator)) {
        for (int i = 0; i < ids.length; i++) {
          System.out.println(Integer.parseInt(ids[i]) + ":" + ids[i]);
          Course course;
          try {
            course =
                (Course) DAOFactory.getCourseDaoInstance().findByKeyId(Integer.parseInt(ids[i]));
            if (course != null) {
              DAOFactory.getCourseDaoInstance().delete(Integer.parseInt(ids[i]));

            } else {
            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        try {
          response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      } else {
        try {
          response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    else
      try {
        response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
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
  private void doDeleteCourse(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    System.out.println("doDeleteCourse-init");
    PrintWriter out = response.getWriter();
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println(id);
    try {
      Course course = (Course) DAOFactory.getCourseDaoInstance().findByKeyId(id);
      if (course != null) {
        System.out.println(id);
        DAOFactory.getCourseDaoInstance().delete(id);
        out.print(true);
        System.out.println("doDeleteCourse-success");
        // response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
      } else {
        out.print(false);
        System.out.println("doDeleteCourse-failure");
        // response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
      }
      out.close();
    } catch (Exception e) {
      System.out.println("doDeleteCourse-err");
      out.print(false);
      e.printStackTrace();
    }
  }

  /**
   * @param request
   * @param response
   */
  private void doAddCourse(HttpServletRequest request, HttpServletResponse response) {
    String courseid = request.getParameter("smallinputcourseid");
    String coursename = request.getParameter("smallinputcoursename");
    String starttime = request.getParameter("dropdownstarttime");
    String endtime = request.getParameter("dropdownendtime");
    String[] isweeks = request.getParameterValues("checkboxweeks");
    String[] weekdays = request.getParameterValues("checkboxweekdays");
    String addresshead = request.getParameter("dropdownaddresshead");
    String addressbody = request.getParameter("smallinputaddressbody");
    String teacher = request.getParameter("smallinputteacher");
    // String str =
    // courseid + ":" + coursename + ":" + starttime + ":" + endtime + ":" + addresshead + ":"
    // + addressbody + ":" + teacher + ":";
    // for (int i = 0; isweeks != null && i < isweeks.length; i++) {
    // str += isweeks[i] + ":";
    // }
    // for (int i = 0; weekdays != null && i < weekdays.length; i++) {
    // str += weekdays[i] + ":";
    // }
    // System.out.println("str = " + str);

    if (courseid == null || "".equals(courseid)) {
      try {
        response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      Course course = new Course();
      course.setCourseid(courseid);
      course.setCoursename(coursename);
      course.setStartweek(Integer.parseInt(starttime));
      course.setEndweek(Integer.parseInt(endtime));
      course.setAddressnumber(addresshead + addressbody);
      course.setTeachername(teacher);
      if (isweeks != null && isweeks.length == 1) {
        String temp = isweeks[0].trim();
        if (temp.equals("1")) {
          course.setIsAll(1);
        } else {
          course.setIsAll(2);
        }
      } else {
        course.setIsAll(0);
      }
      if (weekdays != null) {
        String wString = "";
        for (int i = 0; i < weekdays.length - 1; i++) {
          wString += weekdays[i] + "-";
        }
        wString += weekdays[weekdays.length - 1];
        course.setWeekday(wString);
      }

      try {
        DAOFactory.getCourseDaoInstance().add(course);
        response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
      } catch (Exception e) {
        e.printStackTrace();
        try {
          response.sendRedirect("/ScauActivityWeb/admin/manageClass.jsp");
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }

    }

  }

}
