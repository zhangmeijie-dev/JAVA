package collection.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * CGLib 动态代理  基于类的形式
 */
public class CglibDemo {
  @Test
  public void cglibTest() throws NoSuchMethodException {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(CglibProxyObject.class);
    enhancer.setCallback(new MyMethodInterceptor());
    CglibProxyObject cglib = (CglibProxyObject) enhancer.create();
    cglib.testPublic();
    System.out.println("--------------------");
    cglib.testProtected();
    System.out.println("--------------------");
    cglib.testDefault();
    System.out.println("--------------------");
    CglibProxyObject.testStatic(); // 私有方法 无法代理
    System.out.println("--------------------");
    cglib.testFinal(); // final修饰的方法 无法代理
    System.out.println("--------------------");
    cglib.testStatic(); // 静态方法 无法代理
    System.out.println("--------------------");
    CglibProxyObject.testStatic(); // 私有方法 无法代理
  }


  // 代理对象的回调方法
  class MyMethodInterceptor implements MethodInterceptor {
    /**
     * @param o           cglib生成的代理对象
     * @param method      被代理原方法方法
     * @param objects     方法入参
     * @param methodProxy CGLIB生成子类的代理方法
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
      System.out.println(method.getName() + "前置方法");
      Object invoke = methodProxy.invokeSuper(o, objects);
      System.out.println(method.getName() + "后置置方法");
      return invoke;
    }
  }
}

class CglibProxyObject {
  public void testPublic() {
    System.out.println("public 方法执行了。。。。。");
  }

  protected void testProtected() {
    System.out.println("protected 方法执行了。。。。。");
  }

  void testDefault() {
    System.out.println("default 方法执行了。。。。。");
  }

  //TODO 该方法无法 添加代理
  private void testPrivate() {
    System.out.println("private 方法执行了。。。。。");
  }

  //TODO 该方法无法 添加代理
  public final void testFinal() {
    System.out.println("final 方法执行了。。。。。");
  }

  //TODO 该方法无法 添加代理
  public static void testStatic() {
    System.out.println("static 方法执行了。。。。。");
  }

  public static void getPrivate() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(CglibProxyObject.class);
    enhancer.setCallback(new CglibDemo().new MyMethodInterceptor());
    CglibProxyObject o = (CglibProxyObject) enhancer.create();
    o.testPrivate();
  }

}




