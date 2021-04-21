package com.java.collection.proxy.proxy;

import com.java.collection.proxy.component.Array;
import com.java.collection.proxy.component.NotNull;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK自带 代理 基于接口的方式
 */
public class ProxyDemmo{
	@Test
	public void listProxy(){
		List proxyList = (List) Proxy.newProxyInstance(
			this.getClass().getClassLoader(), // 类加载器 --- 只要时 可以加载该类的加载器就行
			new ArrayList().getClass().getInterfaces(), // 接口class的集合
			new InvocationHandler() {
				private ArrayList list =new ArrayList();
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					String name = method.getName();
					System.out.println(name+"：前处理");
					Object invoke = method.invoke(list, args);
					System.out.println(name+"：后处理");
					return invoke;
				}
			}
		);
		proxyList.add("123");
		proxyList.iterator();
		proxyList.clear();
	}

	@Test
	public void objectProxy(){
		B proxy = (B)Proxy.newProxyInstance(
			this.getClass().getClassLoader(),
			new Class[]{B.class},
			new InvocationHandler() {
				private final A a = new A();
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					String name = method.getName();
					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
					System.out.println(name + "：前处理");
					for (int index = 0; index < args.length; index++) {
						Annotation[] annotations = parameterAnnotations[index];// 获取当前参数上的 注解类型列表
						// 查找当前参数注解列表是否函数 指定注解
						for (int i = 0; i < annotations.length; i++) {
							Annotation annotation = annotations[i];
							Class<? extends Annotation> annotationType = annotation.annotationType();
							if (annotationType.equals(Array.class)) { // 判断是否为指定注解
								Object[] arg =(Object[]) args[index];
								for (int a = 0; a < arg.length; a++) {
									arg[a] = arg[a] + "通过注解来修改";
								}
							}else if (annotationType.equals(NotNull.class)){
								Object obj = (Object)args[index];
								if (obj==null) {
									throw new RuntimeException("参数不能为空！");
								}
							}
						}

					}
					Object invoke = method.invoke(a, args);
					System.out.println(name + "：后处理");
					return invoke;
				}
			}
		);
		proxy.menoth("1","2","321");
	}

	class A implements B{
		public void menoth(String name, String ...str) {
			System.out.println(Arrays.toString(str));
		}
	}

	interface B{
		void menoth(@NotNull String name, @NotNull @Array String... str);
	}

}