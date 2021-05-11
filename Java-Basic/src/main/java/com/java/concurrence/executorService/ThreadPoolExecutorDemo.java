package com.java.concurrence.executorService;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * JAVA 中常见的线程池有： 固定大小的线程池：FixThreadPool 缓存线程池：CacheThreadPool 单线程线程池：SingleThreadPool
 * 延迟线程池：ScheduledThreadPool
 *
 * 前三种线程池都是基于ThreadPoolExecutor 实现的，只有延迟线程池比较特殊
 */
public class ThreadPoolExecutorDemo {

  // 线程池任务
  private Callable callable = () -> {
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep((long) (Math.random() * 10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "：" + i);
    }
    return Thread.currentThread().getName();
  };


  //TODO 创建一个固定大小的线程池
  @Test
  public void fixThreadPoolDemo() throws InterruptedException, ExecutionException {

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
        5, 5, // 核心线程数 5     最大线程数 5
        0, TimeUnit.SECONDS, // 非核心线程空闲回收的时间
        new LinkedBlockingQueue<Runnable>(), // 任务等待队列
        Executors.defaultThreadFactory(), // 创建线程的工厂
        new AbortPolicy() // 拒绝任务执行器
    );

    for (int i = 0; i < 10; i++) {
      executorService.submit(callable);
      threadPoolExecutor.submit(callable);
    }

    closePool(executorService);
    closePool(threadPoolExecutor);
  }


  //TODO 创建一个缓存线程池
  @Test
  public void cacheThreadPoolDemo() throws InterruptedException {

    ExecutorService executorService = Executors.newCachedThreadPool();

    ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
        0, Integer.MAX_VALUE, // 核心线程数 0    最大线程数 Integer.MAX_VALUE
        60, TimeUnit.SECONDS, // 非核心线程空闲回收的时间
        new SynchronousQueue<Runnable>(), // 任务等待队列
        Executors.defaultThreadFactory(), // 创建线程的工厂
        new AbortPolicy() // 拒绝任务执行器
    );

    for (int i = 0; i < 100; i++) {
      executorService.submit(callable);
      threadPoolExecutor.submit(callable);
    }

    closePool(executorService);
    closePool(threadPoolExecutor);
  }

  //TODO 创建一个单线程池
  @Test
  public void singleThreadPoolDemo() throws InterruptedException {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
        1, 1, // 核心线程数 1    最大线程数 1
        0, TimeUnit.SECONDS, // 非核心线程空闲回收的时间
        new LinkedBlockingQueue<Runnable>(), // 任务等待队列
        Executors.defaultThreadFactory(), // 创建线程的工厂
        new AbortPolicy() // 拒绝任务执行器
    );

    for (int i = 0; i < 10; i++) {
      executorService.submit(callable);
      threadPoolExecutor.submit(callable);
    }

    closePool(executorService);
    closePool(threadPoolExecutor);
  }

  //TODO 创建一个延迟线程池
  @Test
  public void scheduledThreadPoolDemo() throws InterruptedException {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    ScheduledExecutorService threadPoolExecutor = new ScheduledThreadPoolExecutor(
        5, // 核心线程数 5
        Executors.defaultThreadFactory(), // 创建线程的工厂
        new AbortPolicy() // 拒绝任务执行器
    );

    for (int i = 0; i < 10; i++) {
      ScheduledFuture schedule = executorService
          .schedule(callable, (long) (Math.random() * 1000), TimeUnit.MILLISECONDS);
      ScheduledFuture schedule1 = threadPoolExecutor
          .schedule(callable, (long) (Math.random() * 1000), TimeUnit.MILLISECONDS);
    }

    closePool(executorService);
    closePool(threadPoolExecutor);
  }


  //TODO 利用延迟线程池实现1小时内没10秒打印一次
  @Test
  public void scheduledThreadPoolDemo2() throws InterruptedException {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
    // 打印任务
    ScheduledFuture<?> scheduledFuture = executorService
        .scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()+"："+System.currentTimeMillis()),
            10, 10, TimeUnit.SECONDS);

    // 1小时后关关闭打印任务
    executorService.schedule(() -> scheduledFuture.cancel(true), 60 * 60, TimeUnit.SECONDS);

    // 是程序不要关闭，不然会任务还没开始就推出了
    Thread.sleep(60 * 60 * 1000);
  }


  private void closePool(ExecutorService executorService) throws InterruptedException {
    executorService.shutdown();
    if (!executorService.awaitTermination(60l, TimeUnit.SECONDS)) {
      List<Runnable> runnables = executorService.shutdownNow();
      System.out.println("executorService线程已关闭：" + runnables.size());
    } else {
      System.out.println("executorService线程已关闭");
    }
  }

}
