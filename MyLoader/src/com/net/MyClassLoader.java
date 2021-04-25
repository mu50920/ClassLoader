package com.net;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * @program: Loader
 * @description: 自定义ClassLoader
 * @author: Mr.Yang
 * @create: 2021-04-24 14:20
 **/
public class MyClassLoader extends SecureClassLoader {
    private  String classPath;
    public  MyClassLoader (String classPath)
    {
        this.classPath = classPath;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte []b ;
        ByteArrayBuffer bf = new ByteArrayBuffer();
        String filePath = this.classPath + name.replace(".","\\").concat(".class");
        FileInputStream fls;
        int code;
        try {

            fls = new FileInputStream(new File(filePath));

            while((code = fls.read())!=-1)
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

}
