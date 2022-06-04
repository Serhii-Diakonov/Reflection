package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Scanner;

public class Main2 {

    /* Console
     *
     * This method for the 2nd Task.
     * It invokes a certain method of the object without parameters
     * (if possible)
     *
     * To see how it works just run the main(),
     * then enter method`s name.
     * Don`t use modifiers or brackets,
     * just method`s name!
     * E.g.
     * toString -- correct input
     * public toString -- wrong input
     * toString() -- wrong input
     * String toString -- wrong input
     * */

    static Scanner in=new Scanner(System.in);

    public static void main(String[] args){

        //Test1
        System.out.println("\nTest 1\nСтворення об'єкту...");
        double obj1=Double.NaN;
        System.out.println(getObjInfo(obj1));
        System.out.print("\nВведіть ім'я методу, що НЕМАЄ ПАРАМЕТРІВ ( наприклад toString ): ");
        System.out.println(useMethod(obj1, in.next()));

        //Test2
        System.out.println("\nTest 2\nСтворення об'єкту...");
        Object obj2=new Date();
        System.out.println(getObjInfo(obj2));
        System.out.print("\nВведіть ім'я методу, що НЕМАЄ ПАРАМЕТРІВ ( наприклад toString ): ");
        System.out.println(useMethod(obj2, in.next()));
    }

    public static String getObjInfo(Object obj){
        StringBuilder res=new StringBuilder();
        try{
            res.append("Поля:\n");
            for(Field f: obj.getClass().getDeclaredFields()){
                f.setAccessible(true);
                res.append("\t").append(f.toGenericString()).append(": ");
                if(f.get(obj)!=null)res.append(f.get(obj).toString()).append("\n");
            }
            res.append("Методи:\n");
            for(Method m: obj.getClass().getDeclaredMethods()){
                res.append("\t").append(m.toGenericString()).append("\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    public static String useMethod(Object obj, String method_name){
        StringBuilder res=new StringBuilder();
        try{
            return res.append("Результат методу ").append(method_name).
                    append(": ").append(obj.getClass().getMethod(method_name).invoke(obj)).toString();
        }catch (Exception e){
            res.append("Схоже я не знаю такого методу. Можливо ви допустили помилку?\nБудь ласка, повторіть!");
        }
        return "";
    }
}
