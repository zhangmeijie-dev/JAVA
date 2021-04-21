package com.spring.di;

import com.spring.di.pojo.DI_TestBean;
import com.spring.di.pojo.P;
import com.spring.di.pojo.Spel;
import com.spring.ioc.pojo.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DI {
    /**
     *  spring   ---  xml配置
     */
    @Test
    public void method01(){
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/di/springDIXML.xml");

      // Set方法注入
      Person person1 = (Person)context.getBean("person1");

      // 构造器方法注入
      Person person2 = (Person)context.getBean("person2");

      // P名称注入
      P p = (P)context.getBean("p");

      // sqel注入
      Spel spel = (Spel)context.getBean("spel");

      // 复杂类型注入
      DI_TestBean test01 = (DI_TestBean)context.getBean("test01");
      DI_TestBean test02 = (DI_TestBean)context.getBean("test02");

      context.close();
    }




}