package com.net;

import com.net.spi.SalaryService;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @program: Loader
 * @description:
 * @author: Mr.Yang
 * @create: 2021-04-24 01:00
 **/
public class Demo {

    public static void main(String[] args) throws Exception {
        // TODO 自动生成的方法存根
        URL jarPath = new URL("file:D:\\ClassLoaderTest\\Salary.jar");
        //URLClassLoader urlclassloader = new URLClassLoader(new URL[] {jarPath});
        /* MyClassLoader myClassLoader = new MyClassLoader("D:\\ClassLoaderTest\\");*/

        double salary = 2000.00;
        MyClassLoaderToJar myClassLoaderToJar = new MyClassLoaderToJar("D:\\ClassLoaderTest\\Salary.jar");
       /* double money = 0;
        Salary salary1 = new Salary();



            money = getMoney(salary,myClassLoaderToJar);
            System.out.println("实际到手工作" + money);
            System.out.println("原本的工资"+salary1.getSalary(salary));*/

        ServiceLoader<SalaryService> load = ServiceLoader.load(SalaryService.class);
        Iterator<SalaryService> iterator = load.iterator();
        if(iterator.hasNext())
        {
            SalaryService service = iterator.next();
            System.out.println(service.getSalary(salary));
        }
        else
            System.out.println("meiypi");


    }


    public static double getMoney(double salary,ClassLoader myClassLoaderToJar) throws Exception {

        Class<?> clazz = myClassLoaderToJar.loadClass("src.com.net.Salary");
        Object obj = clazz.newInstance();
        return (double) clazz.getMethod("getSalary", double.class).invoke(obj, salary);
    }
}