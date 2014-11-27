/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-22
 */
package com.chao.tool.impl;

import java.util.ArrayList;
import java.util.List;

import com.chao.model.IModel;

/**
 * @author 蔡超敏
 * 
 */
public class MyPagination {
  public List<IModel> list = null;

  private int recordCount = 0;

  private int pagesize = 0;

  private int maxPage = 0;

  public List<IModel> getInitPage(List<IModel> list, int Page, int pagesize) {
    List<IModel> newList = new ArrayList<IModel>();
    this.list = list;
    recordCount = list.size();
    this.pagesize = pagesize;
    this.maxPage = getMaxPage();
    try {
      for (int i = (Page - 1) * pagesize; i <= Page * pagesize - 1; i++) {
        try {
          if (i >= recordCount) {
            break;
          }
        } catch (Exception e) {
        }
        newList.add(list.get(i));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newList;
  }

  public List<IModel> getAppointPage(int Page) {
    List<IModel> newList = new ArrayList<IModel>();
    try {
      for (int i = (Page - 1) * pagesize; i <= Page * pagesize - 1; i++) {
        try {
          if (i >= recordCount) {
            break;
          }
        } catch (Exception e) {
        }
        newList.add(list.get(i));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newList;
  }

  public int getMaxPage() {
    int maxPage =
        (recordCount % pagesize == 0) ? (recordCount / pagesize) : (recordCount / pagesize + 1);
    return maxPage;
  }

  public int getRecordSize() {
    return recordCount;
  }

  public int getPage(String str) {
    System.out.println("STR:" + str + "&&&&" + recordCount);
    if (str == null) {
      str = "0";
    }
    int Page = Integer.parseInt(str);
    if (Page < 1) {
      Page = 1;
    } else {
      if (((Page - 1) * pagesize + 1) > recordCount) {
        Page = maxPage;
      }
    }
    return Page;
  }

  public String printCtrl(int Page, String path, String sign) {
    String strHtml =
        "<table width='370'  border='0' cellspacing='0' cellpadding='0'><tr> <td height='24' align='right'>当前页数：["
            + Page + "/" + maxPage + "]&nbsp;&nbsp;";
    try {
      if (Page > 1) {
        strHtml = strHtml + "<a href='" + path + "?Page=1&type=" + sign + "'>第一页</a>";
        strHtml =
            strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page - 1) + "&type=" + sign
                + "'>上一页</a>";
      }
      if (Page < maxPage) {
        strHtml =
            strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page + 1) + "&type=" + sign
                + "'>下一页</a>&nbsp;&nbsp;　<a href='" + path + "?Page=" + maxPage + "&type=" + sign
                + "'>最后一页&nbsp;</a>";
      }
      strHtml = strHtml + "</td></tr></table>";
    } catch (Exception e) {
      e.printStackTrace();

    }
    return strHtml;
  }

  public String printCtrl(int Page, String path, Integer id) {
    String strHtml =
        "<table width='370'  border='0' cellspacing='0' cellpadding='0'><tr> <td height='24' align='right'>当前页数：["
            + Page + "/" + maxPage + "]&nbsp;&nbsp;";
    try {
      if (Page > 1) {
        strHtml = strHtml + "<a href='" + path + "?Page=1&id=" + id + "'>第一页</a>";
        strHtml =
            strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page - 1) + "&id=" + id
                + "'>上一页</a>";
      }
      if (Page < maxPage) {
        strHtml =
            strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page + 1) + "&id=" + id
                + "'>下一页</a>&nbsp;&nbsp;　<a href='" + path + "?Page=" + maxPage + "&id=" + id
                + "'>最后一页&nbsp;</a>";
      }
      strHtml = strHtml + "</td></tr></table>";
    } catch (Exception e) {
      e.printStackTrace();

    }
    return strHtml;
  }

  public String printCtrl(int Page, String path) {
    /**
     * <a href="#" title="First Page">&laquo; 首页</a><a href="#" title="Previous Page">&laquo;
     * 上一页</a> <a href="#" class="number" title="1">1</a> <a href="#"
     * title="Next Page">下一页&raquo;</a><a href="#" title="Last Page">最后 &raquo;</a>
     */

    String strHtml = "当前页数：[" + Page + "/" + maxPage + "]&nbsp;&nbsp;";
    try {
      if (Page > 1) {
        strHtml = strHtml + "<a href='" + path + "?Page=1'>第一页</a>";
        strHtml = strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page - 1) + "'>上一页</a>";
      }
      if (Page < maxPage) {
        strHtml =
            strHtml + "&nbsp;&nbsp;<a href='" + path + "?Page=" + (Page + 1)
                + "'>下一页</a>&nbsp;&nbsp;　<a href='" + path + "?Page=" + maxPage
                + "'>最后一页&nbsp;</a>";
      }
      strHtml = strHtml;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return strHtml;
  }
}
