package com.spring.aop.module;


import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserManagerImpl{
 
	public String before() {
		System.out.println("---------UserManagerImpl.before()--------");
		return "before";
	}
	public String around() {
		System.out.println("---------UserManagerImpl.around()--------");
		return "around";
	}

	public String after(int userId) {
		if (userId<0){
			throw new RuntimeException();
		}
		System.out.println("---------UserManagerImpl.after()--------");
		return "after";
	}
	public String afterReturning(int userId) {
		if (userId<0){
			throw new RuntimeException();
		}
		System.out.println("---------UserManagerImpl.afterReturning()--------");
		return "afterReturning";
	}


	public String afterThrowing(int userId){
		if (userId<0){
			throw new RuntimeException();
		}
		System.out.println("---------UserManagerImpl.afterThrowing()--------");
		return "afterThrowing";
	}

	@Pointcut("execution(* com.spring.aop.module.UserManagerImpl.before(..))")
	public void beforeExecution(){};
	@Pointcut("execution(* com.spring.aop.module.UserManagerImpl.around(..))")
	public void aroundExecution(){};
	@Pointcut("execution(* com.spring.aop.module.UserManagerImpl.after(..))")
	public void afterExecution(){};
	@Pointcut("execution(* com.spring.aop.module.UserManagerImpl.afterReturning(..))")
	public void afterReturningExecution(){};
	@Pointcut("execution(* com.spring.aop.module.UserManagerImpl.afterThrowing(..))")
	public void afterThrowingExecution(){};
}