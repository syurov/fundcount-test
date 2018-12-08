package com.easy.fundcount.app;

import com.easy.fundcount.app.config.SpringContext;
import com.easy.fundcount.frames.MainFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Application {

  /**
   * Main application
   * @param args
   */
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);


    MainFrame MainFrame = (MainFrame) context.getBean("MainFrame");
    MainFrame.init();
  }
}
