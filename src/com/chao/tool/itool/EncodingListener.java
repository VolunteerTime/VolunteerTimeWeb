package com.chao.tool.itool;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class EncodingListener
 * 
 */
@WebListener
public class EncodingListener implements ServletRequestListener {

  /**
   * Default constructor.
   */
  public EncodingListener() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
   */
  public void requestDestroyed(ServletRequestEvent arg0) {
    // TODO Auto-generated method stub
  }

  /**
   * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
   */
  public void requestInitialized(ServletRequestEvent arg0) {
    try {
      ((HttpServletRequest) arg0.getServletRequest()).setCharacterEncoding("utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

}
