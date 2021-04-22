package com.spring.aop;

import com.spring.aop.module.UserManagerImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 *  AOP名词：
 *    连接点 joinPoint ：连接点是一个应用执行过程中能够插入一个切面的点。
 *    通知 advice ：切面也需要完成工作。在 AOP 术语中，切面的工作被称为通知。
 *    切点 pointCut：如果通知定义了“什么”和“何时”。那么切点就定义了“何处”。切点会匹配通知所要织入的一个或者多个连接点。
 *    切面 aspect：切面是通知和切点的集合 ，通知和切点共同定义了切面的全部功能——它是什么，在何时何处完成其功能。
 *    织入 weaving： 织入是将切面应用到目标对象来创建的代理对象过程。
 */
public class AOP {
    /**
     *  spring   ---  xml配置
     */
    @Test
    public void method01(){
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/springAOPXML.xml");
      UserManagerImpl userManager = (UserManagerImpl)context.getBean("userManager");

      userManager.before();
      System.out.println();
      System.out.println();

      userManager.around();
      System.out.println();
      System.out.println();

      userManager.after(1);
      System.out.println();
      System.out.println();

      userManager.afterReturning(1);
      System.out.println();
      System.out.println();

      userManager.afterThrowing(1);
      System.out.println();
      System.out.println();

      context.close();
    }

  /**
   *  spring   ---  注解配置
   */
  @Test
  public void method02(){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/aop/springAOPAnnotation.xml");
    UserManagerImpl userManager = (UserManagerImpl)context.getBean("userManagerImpl");

    userManager.before();
    System.out.println();
    System.out.println();

    userManager.around();
    System.out.println();
    System.out.println();

    userManager.after(1);
    System.out.println();
    System.out.println();

    userManager.afterReturning(1);
    System.out.println();
    System.out.println();

    userManager.afterThrowing(1);
    System.out.println();
    System.out.println();

    context.close();
  }



}