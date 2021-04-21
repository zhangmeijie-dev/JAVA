package collection.queue;

import com.google.gson.Gson;

import java.util.PriorityQueue;

/**
 * 优先级队列
 */
public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue queue = new PriorityQueue();
		queue.add(new Student("A",13));
		queue.add(new Student("B",14));
		queue.add(new Student("C",14));
		queue.add(new Student("D",13));
		queue.add(new Student("E",15));
		Gson gson = new Gson();
		System.out.println(gson.toJson(queue));
	}


	private static class Student implements Comparable<Student>{
		/**
		 *  排序功能
		 *  > 0 表示 stu 大于当前对象
		 *  = 0 表示等于当前对象
		 *  < 0 表示 stu 小于当前对象
		 */
		public int compareTo(Student stu) {
			int age = stu.getAge();
			String name = stu.getName();

			int value = age - this.age;
			if (value> 0) {
				return -1;
			}else if (value<0){
				return 1;
			}else {
				int i = name.compareTo(this.name);
				if (i>0) {
					return -1;
				}else {
					return 1;
				}
			}
		}

		private String name;
		private int age;
		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}


	}
}
