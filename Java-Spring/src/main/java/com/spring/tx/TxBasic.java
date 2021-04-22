package com.spring.tx;

import com.spring.tx.service.TxService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TxBasic {
  /**
   * spring   ---  xml
   */
  @Test
  public void method01() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/tx/SpringTXXML.xml");
    TxService txService = (TxService) context.getBean("txService");
    txService.testTxAop(4);
    context.close();
  }

  /**
   * spring   ---  注解
   */
  @Test
  public void method02() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/tx/SpringTXAnnotation.xml");
    TxService txService = context.getBean(TxService.class);
    txService.testTxAop(5);
    context.close();
  }


}