/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-23
 */
package com.chao.model;

/**
 * @author �̳���
 * 
 */
public class CourseAllContent {

  private CourseKeyContent[][] courseday;

  public CourseAllContent() {
    System.out.println("һ��CourseAllContent���󴴽���");
    courseday = new CourseKeyContent[7][7];
  }

  public void setContent(String courseContent, String coursename, String teachername,
      String addressnumber) {
    System.out.println(courseContent);
    String[] weekdays = courseContent.split("-");
    if (weekdays == null)
      return;
    for (int i = 0; i < weekdays.length; i++) {
      System.out.println(weekdays[i]);
      String[] days = weekdays[i].split(":");
      System.out.println(days[0] + ":" + days[1]);
      int row = Integer.parseInt(days[0]);
      int column = Integer.parseInt(days[1]);
      if (row < 0 || row > 6 || column < 0 || column > 6)
        continue;
      courseday[row][column] = new CourseKeyContent();
      courseday[row][column].setCourseName(coursename);
      courseday[row][column].setTeacherName(teachername);
      courseday[row][column].setCourseAddress(addressnumber);
    }
  }


  public CourseKeyContent getCourseKeyContent(int row, int column) {
    if (courseday[row][column] != null)
      return courseday[row][column];
    else
      return null;
  }

  public String getSection(int i) {
    switch (i) {
      case 0:
        return "1-2��";
      case 1:
        return "3-4��";
      case 2:
        return "5-6��";
      case 3:
        return "7-8��";
      case 4:
        return "9-10��";
      case 5:
        return "11-12��";
      case 6:
        return "13��";
      default:
        return "14��";
    }
  }

  public String get(int row, int column) {
    CourseKeyContent content;
    if (courseday[row][column] != null) {
      content = getCourseKeyContent(row, column);
      String str =
          "<p ><span class=\"label label-info\" >" + content.getCourseName() + "</span></p>"
              + "<p ><span class=\"label label-warning\" >" + content.getTeacherName()
              + "</span></p>" + "<p ><span class=\"label label-danger\" >"
              + content.getCourseAddress() + "</span></p>";

      return str;
    } else {
      return "<br><br>";
    }
  }
}
