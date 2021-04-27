package com.java.collection.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 用户自定义列加载器
 */
public class CustomClassLoader extends ClassLoader {
  private String path = "C:\\Users\\刘志航\\Desktop\\IDEA_Workspaces\\JAVA\\Java-Basic\\src\\main\\java\\com\\java\\collection\\classLoader\\";    // 加载器 加载路径

  private String name;                    //类加载器名称

  private final String filetype = ".class"; //文件类型


  public CustomClassLoader(String name) {
    // TODO Auto-generated constructor stub
    super();
    this.name = name;
  }

  public CustomClassLoader(ClassLoader parent, String name) {
    super(parent);
    this.name = name;
  }

  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {
    // TODO Auto-generated method stub
    byte[] b = loadClassData(name);
    return defineClass(name, b, 0, b.length);
  }

  private byte[] loadClassData(String name) {
    byte[] data = null;
    InputStream in = null;
    name = name.replace('.', '/');
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      in = new FileInputStream(new File(path + name + filetype));
      int len = 0;
      while (-1 != (len = in.read())) {
        out.write(len);
      }
      data = out.toByteArray();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        in.close();
        out.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return data;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.name;
  }
}
