package com.kanyuxia.mockdata.bean;

import java.util.List;
import lombok.Data;

@Data
public class Teacher extends People {

  private List<People> list;

}
