package com.company;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

public class Main5 {

    /* Console
    *
    * This method for the 5th Task.
    * It shows proxies features and ability
    * to invokes method of the object without
    * direct using of this object.
    *
    * To see how it works just run the main()
    * */
    public static void main(String[]args){

        //Test1 (invokes method: int sum(int, int) from class MyClass)
        int a=23, b=77;
        MyClass cls=new MyClass(15,27);

        Proxy proxy= (Proxy) Proxy.newProxyInstance(cls.getClass().getClassLoader(),
                cls.getClass().getInterfaces(), new Handler(cls));

        System.out.println("Проксі клас: "+proxy.getClass().getName());
        System.out.println("Проксі конструктори: ");
        for(Constructor c:proxy.getClass().getDeclaredConstructors())
            System.out.println("\t"+c.toGenericString());
        System.out.println("Результат проксі: "+((Summable) proxy).sum(a,b));
        System.out.println();

        System.out.println("Параметри для безпосереднього застосування методу:" +
                "\n\tтип: int\tзначення: "+a+"\n\tтип: int\tзначення: "+b);
        long start=System.nanoTime();
        System.out.println("Результат: "+cls.sum(a,b));
        long end=System.nanoTime();
        System.out.println("Безпосередній час: "+(end-start)+"ns");

        //Test2 (invokes another method: double sum(double, double) from class MyClass)
        System.out.println("\nTest2");
        double c=23.2, d=73.00001;
        System.out.println("Результат проксі: "+((Summable) proxy).sum(c,d));
        System.out.println();

        System.out.println("Параметри для безпосереднього застосування методу:" +
                "\n\tтип: double\tзначення: "+c+"\n\tтип: double\tзначення: "+d);
        start=System.nanoTime();
        System.out.println("Результат: "+cls.sum(c,d));
        end=System.nanoTime();
        System.out.println("Безпосередній час: "+(end-start)+"ns");
    }
}
