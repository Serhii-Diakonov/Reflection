package com.company;

import java.awt.*;

public class Main3 {

    /* Console
     *
     * This method for the 3rd Task.
     * It invokes a certain method of the object
     * (if possible)
     *
     * To see how it works just run the main()
     * */

    public static void main(String[] args) {
        //Test 0
        System.out.println("\nTest 0\nСтворення об'єкту...");
        Object obj1=new Point();
        System.out.println("Тип: Point\t Значення: none");
        String name1 = "distance";
        System.out.print("Ім'я методу: " + name1);
        System.out.print("\nПараметри: 12., 12., 22., 22.");
        try {
            System.out.println("\nРезультат методу: " + useMethod(obj1, name1, 12.,12.,22.,22.));
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        }

//        Test 1
        System.out.println("\nTest 1\nСтворення об'єкту...");
        MyClass obj2 = new MyClass(12.1, 81.98);
        System.out.println("Тип: MyClass\t Значення: a=12.1, b=81.98");
        String name = "sum";
        System.out.print("Ім'я методу: " + name);
        System.out.print("\nПараметри: null");
        try {
            System.out.println("\nРезультат методу: " + useMethod(obj2, name, null));
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        }

        //Test 2
        System.out.println("\nTest 2");
        System.out.println("Тип: MyClass\t Значення: a=12.1, b=81.98");
        name = "sum";
        System.out.print("Ім'я методу: " + name);
        System.out.print("\nПараметри: 69, 1");
        try {
            System.out.println("\nРезультат методу: " + useMethod(obj2, name, 69, 1));
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        }

        //Test 3
        System.out.println("\nTest 3");
        System.out.println("Тип: MyClass\t Значення: a=12.1, b=81.98");
        name = "sum";
        System.out.print("Ім'я методу: " + name);
        System.out.print("\nПараметри: 80.3, 20.1212");
        try {
            System.out.println("\nРезультат методу: " + useMethod(obj2, name, 80.3, 20.1212));
        } catch (FunctionNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String useMethod(Object o, String name, Object... params) throws FunctionNotFoundException {
        Class[] types = null;
        if (params != null) {
            types = new Class[params.length];
            int i = 0;
            for (Object obj : params) {
                if (obj instanceof Integer) types[i++] = int.class;
                else if (obj instanceof Byte) types[i++] = byte.class;
                else if (obj instanceof Short) types[i++] = short.class;
                else if (obj instanceof Long) types[i++] = long.class;
                else if (obj instanceof Double) types[i++] = double.class;
                else if (obj instanceof Float) types[i++] = float.class;
                else if (obj instanceof Character) types[i++] = char.class;
                else if (obj instanceof Boolean) types[i++] = boolean.class;
                else types[i++] = obj.getClass();
            }
        }
        try {
            return o.getClass().getMethod(name, types).invoke(o, params).toString();
        } catch (Exception e) {
            throw new FunctionNotFoundException();
        }
    }
}
