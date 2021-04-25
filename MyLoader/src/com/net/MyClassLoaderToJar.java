package com.net;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureClassLoader;

/**
 * @program: Loader
 * @description: jar包加载器
 * @author: Mr.Yang
 * @create: 2021-04-24 15:01
 **/
public class MyClassLoaderToJar extends SecureClassLoader {
    private String jarPath;
    public MyClassLoaderToJar (String jarPath)
    {
        this.jarPath = jarPath;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("zoud ");
        String classPath = name.replace(".","/").concat(".class");
        byte []b ;
        ByteArrayBuffer bf = new ByteArrayBuffer();
        URL jarurl ;
        InputStream inputStream;
        int code;
        try {
               jarurl= new URL("jar:file:\\"+this.jarPath+"!/"+classPath);
            inputStream = jarurl.openStream();

            while((code = inputStream.read())!=-1)
            {
                bf.write(code);
            }
            b= bf.toByteArray();
            return this.defineClass(name,b,0,b.length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //写死
        if(name.startsWith("com.net"))
            return this.findClass(name);
        return super.loadClass(name,resolve);
       /* Class<?> c = null;
        synchronized (getClassLoadingLock(name)) {
             if(c == null)
             {
                 if(!name.contains("Object")) c = this.findClass(name);
                 if(c==null) c = super.loadClass(name,resolve);
             }
            System.out.println(c.toString());
            return c;
        }*/
    }
}
