package com.company;

import java.lang.reflect.*;
import java.util.*;

public class Main {

    /* Console
     *
     * This method for the 1st Task.
     *
     * To see how it works just run the main()
     * and enter the full name of the type.
     *
     * E.g.
     * java.lang.String -- correct input
     * int -- correct input
     * java.lang.SuppressWarnings -- correct input
     * java.lang.Iterable -- correct input
     * java.lang.NullPointerException -- correct input
     * String -- wrong input
     * lang.Iterable -- wrong input
     * Iterable.class -- wrong input
     * java.lang.int -- wrong input
     *
     * If you want to enter an array, you need to add "[L" before type and ";" after it.
     * Don`t use square brackets.
     * For primitive types use:
     *
[B -- correct input for byte[]
[S -- correct input for short[]
[I -- correct input for int[]
[J -- correct input for long[]
[F -- correct input for float[]
[D -- correct input for double[]
[C -- correct input for char[]
[Z -- correct input for boolean[]
* (for 3-dimensional or more arrays just add before
* the expression appropriate number of "[": e.g. [[B --> byte[][])
     *
     * [Ljava.lang.String; -- correct input
     * java.lang.String[] -- wrong input
     * String[] -- wrong input
     * [Lint; -- wrong input
     * (for 3-dimensional or more arrays just add before
     * the expression appropriate number of "[": e.g. [[Ljava.lang.String; --> String[][])
     * */

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
//        System.out.println(byte[][].class.getName());
//        System.out.println(short[].class.getName());
//        System.out.println(int[].class.getName());
//        System.out.println(long[].class.getName());
//        System.out.println(float[].class.getName());
//        System.out.println(double[].class.getName());
//        System.out.println(char[].class.getName());
//        System.out.println(boolean[][][].class.getName());
//        System.out.println(String[][].class.getName());
        System.out.print("Введіть повне ім'я класу: ");
        String input = in.next();
        System.out.println(getInfo(input));
    }
    public static String getInfo(String str) {
        StringBuilder res = new StringBuilder();
        switch (str) {
            case "int" -> res.append("Primitive integer type: int\nRange:[-2147483648; 2147483647]\nSize: 32 bits");
            case "short" -> res.append("Primitive integer type: short\nRange:[-32768; 32767]\nSize: 16 bits");
            case "byte" -> res.append("Primitive integer type: byte\nRange:[-128; 127]\nSize: 8 bits");
            case "long" -> res.append("Primitive integer type: long\nRange:[-9223372036854775808L; 9223372036854775807L]\nSize: 64 bits");
            case "char" -> res.append("Primitive character type: char\nRange: symbols from UTF-16\nSize: 16 bits");
            case "double" -> res.append("Primitive floating point type: double\nRange:[4.9e-324; 1.7e+308]\nSize: 64 bits");
            case "float" -> res.append("Primitive floating point type: float\nRange:[1.4e-45f; 3.4e+38f]\nSize: 32 bits");
            case "boolean" -> res.append("Primitive logic type: boolean\nRange:[true, false]\nSize: 32 bits (8 bits)");
            default -> {
                try {
                    if (Class.forName(str).isInterface() || Class.forName(str).isAnnotation()
                            || isWrapper(str)||isNotUsualClass(str)) {
                        Class o = Class.forName(str);
                        res.append("Пакет: ").append(o.getPackageName()).append("\n");
                        res.append("Кількість модифікаторів:").append(o.getModifiers()).append("\n");
                        res.append("Ім'я: ").append(o.getName()).append("\n");
                        try {
                            res.append("Суперклас: ").append(o.getSuperclass().getName()).append("\n");
                        } catch (NullPointerException e){
                            res.append("порожньо\n");
                        }
                        res.append("Інтерфейси:\n");
                        for (Class i : o.getInterfaces()) {
                            res.append("\t").append(i.toGenericString()).append("\n");
                        }
                        res.append("\nПоля:\n");
                        for (Field f : o.getDeclaredFields()) {
                            res.append("\t").append(f.toGenericString()).append("\n");
                        }
                        res.append("\nКонструктори:\n");
                        for (Constructor c : o.getDeclaredConstructors()) {
                            res.append("\t").append(c.toGenericString()).append("\n");
                        }
                        res.append("\nМетоди:\n");
                        for (Method m : o.getDeclaredMethods()) {
                            res.append("\t").append(m.toGenericString()).append("\n");
                        }
                    } else {
                        Object o = Class.forName(str).getDeclaredConstructor().newInstance();
                        res.append("Пакет: ").append(o.getClass().getPackageName()).append("\n");
                        res.append("Кількість модифікаторів:").append(o.getClass().getModifiers()).append("\n");
                        res.append("Ім'я: ").append(o.getClass().toGenericString()).append("\n");
                        res.append("Суперклас: ").append(o.getClass().getSuperclass().toGenericString()).append("\n");
                        res.append("Інтерфейси:\n");
                        for (Class i : o.getClass().getInterfaces()) {
                            res.append("\t").append(i.toGenericString()).append("\n");
                        }
                        res.append("\nПоля:\n");
                        for (Field f : o.getClass().getDeclaredFields()) {
                            res.append("\t").append(f.toGenericString()).append("\n");
                        }
                        res.append("\nКонструктори:\n");
                        for (Constructor c : o.getClass().getDeclaredConstructors()) {
                            res.append("\t").append(c.toGenericString()).append("\n");
                        }
                        res.append("\nМетоди:\n");
                        for (Method m : o.getClass().getDeclaredMethods()) {
                            res.append("\t").append(m.toGenericString()).append("\n");
                        }
                    }
                } catch (Exception e) {
                    res.append("Схоже я не знаю такого класу. Можливо ви допустили помилку?\nБудь ласка, повторіть!");
                }
            }
        }
        return res.toString();
    }

    static boolean isWrapper(String str){
        switch (str){
            case "java.lang.Double", "java.lang.Integer", "java.lang.Byte", "java.lang.Short", "java.lang.Float", "java.lang.Long", "java.lang.Boolean", "java.lang.Character"->{
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    static boolean isNotUsualClass(String str){
        try {
            Class.forName(str).getDeclaredConstructor().newInstance();
            return false;
        } catch (Exception e){
            return true;
        }
    }
}

