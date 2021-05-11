package com.java.concurrence.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.junit.Test;

/**
 * 创建线程的方式
 */
public class CreateThread {

  /**
   * 方式一： 使用 Thread 类来创建线程
   */
  @Test
  public void createThreadCase1() {
    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println(Thread.currentThread().getName() + "线程运行中: " + i);
        }
      }
    }.start();
  }


  /**
   * 方式二： 使用 Runnable 接口来创建线程
   */
  @Test
  public void createThreadCase2() {
    Runnable runnable = new Runnable() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println(Thread.currentThread().getName() + "线程运行中: " + i);
        }
      }
    };
    new Thread(runnable).start();
  }


  /**
   *  方式三： 使用 Future 来创建线程
   *
   *  该种方式可以获取到返回值，也可以抛出异常
   */
  @Test
  public void createThreadCase3() throws ExecutionException, InterruptedException {
    FutureTask<String> runnableTask = new FutureTask<String>(new Runnable() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          try {
            Thread.sleep((long) (Math.random() * 10000));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + "线程运行中: " + i);
        }
      }
    }, "Runnable运行结束");

    FutureTask<String> callableTask = new FutureTask<String>(new Callable<String>() {
      public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
          Thread.sleep((long) (Math.random() * 10000));
          System.out.println(Thread.currentThread().getName() + "线程运行中: " + i);
        }
        return "Callable运行结束";
      }
    });


    new Thread(runnableTask).start();
    String runnableThreadName = runnableTask.get();
    System.out.println( runnableThreadName + "运行结束");

    System.out.println();

    new Thread(callableTask).start();
    String callableThreadName = callableTask.get();
    System.out.println( callableThreadName + "运行结束");
  }
}
