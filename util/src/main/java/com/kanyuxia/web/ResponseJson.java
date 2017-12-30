package com.kanyuxia.web;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseJson<T> implements Serializable {

  private static final long serialVersionUID = -4710710232881759614L;

  private static final ResponseJson SUCCESS = new ResponseJson();

  private boolean success = true;

  private String message = "操作成功";

  private T data;

  public ResponseJson(T data) {
    this.data = data;
  }

  public ResponseJson(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public static ResponseJson ok() {
    return SUCCESS;
  }

  public static <T> ResponseJson<T> ok(T data) {
    return new ResponseJson<>(data);
  }

  public static ResponseJson fail() {
    return new ResponseJson(false, "操作失败");
  }

  public static ResponseJson fail(String message) {
    return new ResponseJson<>(false, message);
  }
}
