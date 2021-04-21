package com.java.collection.queue;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *  延时队列
 */
public class DelayQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue queue = new DelayQueue();
		queue.add(new Item("Item001", 4000, TimeUnit.MILLISECONDS)); // 该对象从添加时计时 4秒 后才可获取
		queue.add(new Item("Item002", 15000, TimeUnit.MILLISECONDS)); //
		queue.add(new Item("Item003", 5000, TimeUnit.MILLISECONDS));
		queue.add(new Item("Item004", 25000, TimeUnit.MILLISECONDS));
		queue.add(new Item("Item005", 35000, TimeUnit.MILLISECONDS));

		Item poll1 = (Item)queue.poll();  // 获取不到就返回 null
		Item poll2 = (Item)queue.poll();
		Item poll3 = (Item)queue.poll();
		Item poll4 = (Item)queue.poll();

		Item take = (Item)queue.take(); // 获取不到就等待
		System.out.println(take.name);

		queue.clear();
	}

	private static class Item implements Delayed{
		private long time;
		private String name;
		public Item(String name, long time,TimeUnit unit){
			this.name =name;
			this.time = System.currentTimeMillis() + unit.toMillis(time<0?0:time);
		}
		// 用来判断当前对象是否可以获取
		public long getDelay(TimeUnit unit) {
			return time -System.currentTimeMillis();
		}
		// 用来排序
		public int compareTo(Delayed o) {
			Item o1 = (Item) o;
			long ttt =this.time- o1.time;
			if (ttt<=0) {
				return -1;
			}
			return 1;
		}
	}
}
