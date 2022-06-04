package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {

    Object obj;

    Handler(Object o) {
        obj = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Метод: " + method.toGenericString());
        System.out.println("Параметри: ");
        if (args.length != 0)
            for (Object arg : args)
                System.out.println("\tТип: " + arg.getClass().getSimpleName() + "\tЗначення: " + arg.toString());
        else System.out.println("нема");
        long start = System.nanoTime();
        Object res = method.invoke(obj, args);
        System.out.println("Час проксі: " + (System.nanoTime() - start) + "ns");
        return res;
    }
}
