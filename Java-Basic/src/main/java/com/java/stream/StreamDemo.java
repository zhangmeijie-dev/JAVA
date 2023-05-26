package com.java.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.Test;

public class StreamDemo {
  List<Map<String, Object>> list = new ArrayList<>();
  {
    Map<String, Object> map5 = new HashMap<>();
    map5.put("name","张五");
    map5.put("age",5);
    map5.put("pubdate","2018-05-26");
    map5.put("sex","男");
    list.add(map5);

    Map<String, Object> map4 = new HashMap<>();
    map4.put("name","张三");
    map4.put("age",4);
    map4.put("pubdate","2019-05-26");
    map4.put("sex","女");
    list.add(map4);

    Map<String, Object> map3 = new HashMap<>();
    map3.put("name","张三");
    map3.put("age",3);
    map3.put("pubdate","2020-05-26");
    map3.put("sex","女");
    list.add(map3);

    Map<String, Object> map2 = new HashMap<>();
    map2.put("name","张一");
    map2.put("age",2);
    map2.put("pubdate","2021-05-26");
    map2.put("sex","男");
    list.add(map2);

    Map<String, Object> map1 = new HashMap<>();
    map1.put("name","张一");
    map1.put("age",1);
    map1.put("pubdate","2022-05-26");
    map1.put("sex","女");
    list.add(map1);

  }


  @Test
  public void groupTest1(){
    Map<String, List<Map<String, Object>>> pubdateGroup = list.stream()
        .collect(Collectors.groupingBy(map -> (String)map.get("pubdate")));
  }

  @Test
  public void groupTest2(){
    Map<String, Map<String, List<Map<String, Object>>>> collect1 = list.stream()
        .collect(Collectors.groupingBy(
            map -> (String) map.get("name")
            , Collectors.groupingBy(map -> (String) map.get("sex"))
        ));

    Map<String, Map<String, Map<Integer, List<Map<String, Object>>>>> collect2 = list.stream()
        .collect(Collectors.groupingBy(
            map -> (String) map.get("name")
            ,Collectors.groupingBy(
                map -> (String) map.get("sex")
                ,Collectors.groupingBy(map -> (Integer) map.get("age"))
            )
        ));
  }

  @Test
  public void groupTest3(){
    // 分组key在集合中不能重复，不然会报异常
    Map<String, Map<String, Object>> name = list.stream()
        .collect(Collectors.toMap(map -> (String) map.get("pubdate"), Function.identity()));
    // 可以重复
    Map<String, Map<String, Object>> name1 = list.stream()
        .collect(Collectors.toMap(map -> (String) map.get("name"), Function.identity(),(oldValue,newValue) -> oldValue));
  }

  @Test
  public void filterTest1(){
    List<Map<String, Object>> _list = list.stream()
        .filter(map-> ((Integer)map.get("age")).compareTo(3)>0)
        .collect(Collectors.toList());
  }

  @Test
  public void mapTest1(){
    List<Integer> ages = list.stream()
        .map(map -> ((Integer) map.get("age")))
        .distinct()
        .collect(Collectors.toList());
  }

  @Test
  public void sortTest1(){
    // 排序，空值放最后
    String direc = "desc";
    List<Map<String, Object>> collect = list.stream()
        .sorted((map1, map2) -> {
          Object age1 = map1.get("age");
          Object age2 = map2.get("age");

          if (age1 == null) {
            if (age2 == null)
              return 0;
            return 1;
          }
          if (age2 == null)
            return -1;

          if (direc.equalsIgnoreCase("DESC")) {
            return new BigDecimal(age2.toString()).compareTo(new BigDecimal(age1.toString()));
          }
          return new BigDecimal(age1.toString()).compareTo(new BigDecimal(age2.toString()));
        })
        .collect(Collectors.toList());
  }

}
