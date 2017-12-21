package com.kanyuxia.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 提供response输出的实用方法集
 *
 * @author TaoYu
 */
@Slf4j
public final class ResponseUtils {

  private ResponseUtils() {
  }

  /**
   * @param obj 对象
   * @Describe 后台数据以json方式输出到前台
   */
  public static void writeJson(HttpServletResponse response, Object obj) throws IOException {
    response.setContentType("application/json");
    writer(response, obj);
  }

  /**
   * ajax跨域请求jsonP
   *
   * @param request 请求
   * @param response 响应
   * @param obj 对象
   */
  public static void writeJsonP(HttpServletRequest request, HttpServletResponse response, Object obj) throws IOException {
    String callback = request.getParameter("callback");
    response.setContentType("application/json");
    response.setHeader("Cache-Control", "no-store");
    response.setCharacterEncoding("UTF-8");
    try (PrintWriter out = response.getWriter()) {
      String jsonString = JsonUtils.toJsonString(obj);
      if (StringUtils.isEmpty(callback)) {
        out.write(jsonString);
      } else {
        out.write(callback + "(" + jsonString + ")");
      }
      out.flush();
    }
  }

  /**
   * 输出html
   *
   * @param response 响应
   * @param obj 对象
   */
  public static void writeHtml(HttpServletResponse response, Object obj) throws IOException {
    response.setContentType("text/html");
    writer(response, obj);

  }

  /**
   * 一般用于输出Image图片
   *
   * @param response 响应
   * @param byteArray 字节数组
   */
  public static void writeImage(HttpServletResponse response, byte[] byteArray) throws IOException {
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpg");
    try (OutputStream out = response.getOutputStream()) {
      out.write(byteArray);
      out.flush();
    }
  }

  /**
   * 一般用于输出Gif图片
   *
   * @param response 响应
   * @param byteArray 字节数组
   */
  public static void writeGif(HttpServletResponse response, byte[] byteArray) throws IOException {
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/gif");
    try (ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
        OutputStream out = response.getOutputStream()) {
      BufferedImage img = ImageIO.read(in);
      ImageIO.write(img, "gif", out);
    }
  }

  /**
   * 内部的通用输出
   *
   * @param response 响应
   * @param obj 输出对象
   */
  private static void writer(HttpServletResponse response, Object obj) throws IOException {
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setCharacterEncoding("UTF-8");
    try (PrintWriter out = response.getWriter()) {
      out.print(JsonUtils.toJsonString(obj));
      out.flush();
    }
  }

  /**
   * 允许 JS 跨域设置
   *
   * @param response 响应
   */
  public static void allowJsCrossDomain(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    response.setHeader("Access-Control-Max-Age", "3600");
  }
}
