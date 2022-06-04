package com.company;

import java.lang.reflect.Array;

public class Main4 {

    private static final int ARRAY_SIZE = 5;

    /* Console
     *
     * This method for the 4th Task.
     * It allows to create arrays and matrices
     * of different types, extend it and print.
     *
     *
     * To see how it works just run the main()
     * */

    public static void main(String[] args) {

        //Test 1
        final int ASCII_SHIFT = 65;
        String name = "java.lang.String";
        System.out.print("Ім'я класу: " + name + "\n");
        Object ar1 = createArray(name, 3);
        for (int i = 0; i < Array.getLength(ar1); i++) Array.set(ar1, i, Character.toString(i + ASCII_SHIFT));
        System.out.println(print(ar1));
        System.out.println("\n\nРозширення масиву...");
        ar1 = extend(ar1);
        System.out.println(print(ar1));

        //Test 2
        name = "int";
        System.out.print("\nІм'я класу: " + name + "\n");
        Object ar2 = createArray(name, 5);
        for (int i = 0; i < Array.getLength(ar2); i++) Array.set(ar2, i, i);
        System.out.println(print(ar2));
        System.out.println("\n\nРозширення масиву...");
        ar2 = extend(ar2);
        System.out.println(print(ar2));

        //Test 3
        name = "java.lang.String";
        System.out.print("\nІм'я класу: " + name + "\n");
        Object ar3 = createMatrix(name, 6, 5);
        Array.set(Array.get(ar3, 0), 0, "Я");
        Array.set(Array.get(ar3, 0), 1, "помню");
        Array.set(Array.get(ar3, 0), 2, "чудное");
        Array.set(Array.get(ar3, 0), 3, "мгновенье:");
        Array.set(Array.get(ar3, 0), 4, "");
        Array.set(Array.get(ar3, 1), 0, "Передо");
        Array.set(Array.get(ar3, 1), 1, "мной");
        Array.set(Array.get(ar3, 1), 2, "явилась");
        Array.set(Array.get(ar3, 1), 3, "ты,");
        Array.set(Array.get(ar3, 1), 4, "");
        Array.set(Array.get(ar3, 2), 0, "Как");
        Array.set(Array.get(ar3, 2), 1, "мимолётное");
        Array.set(Array.get(ar3, 2), 2, "видение,");
        Array.set(Array.get(ar3, 2), 3, "");
        Array.set(Array.get(ar3, 2), 4, "");
        Array.set(Array.get(ar3, 3), 0, "Как");
        Array.set(Array.get(ar3, 3), 1, "гений");
        Array.set(Array.get(ar3, 3), 2, "чистой");
        Array.set(Array.get(ar3, 3), 3, "красоты!");
        Array.set(Array.get(ar3, 3), 4, "");
        Array.set(Array.get(ar3, 4), 0, "__");
        Array.set(Array.get(ar3, 4), 1, "__");
        Array.set(Array.get(ar3, 4), 2, "__");
        Array.set(Array.get(ar3, 4), 3, "__");
        Array.set(Array.get(ar3, 4), 4, "__");
        System.out.println(print(ar3));
        System.out.println("\n\nРозширення матриці...");
        ar3 =extend(ar3);
        System.out.println(print(ar3));
    }

    //Works only with arrays and matrices (3- and more dimensional arrays aren`t supported)
    public static Object extend(Object ar) {
        Object new_array;
        if (!ar.getClass().getComponentType().isArray()) {
            new_array = createArray(ar.getClass().getComponentType().getCanonicalName(), Array.getLength(ar) + ARRAY_SIZE);
            System.arraycopy(ar, 0, new_array, 0, Array.getLength(ar));
            return new_array;
        } else {
            ar=addCols(ar);
            ar=addRows(ar);
            return ar;
        }
    }

    private static String printArray(Object ar) {
        StringBuilder res = new StringBuilder();
        res.append("{");
        for (int i = 0; i < Array.getLength(ar) - 1; i++)
            if (Array.get(ar, i) != null) res.append(Array.get(ar, i).toString()).append("; ");
            else res.append("null").append("; ");
        if (Array.get(ar, Array.getLength(ar) - 1) != null)
            res.append(Array.get(ar, Array.getLength(ar) - 1).toString()).append("}");
        else res.append("null").append("}");
        return res.toString();
    }

    public static Object createArray(String str, int size) {
        try {
            switch (str) {
                default -> {
                    return Array.newInstance(Class.forName(str), size);
                }
                case "int" -> {
                    return new int[size];
                }
                case "byte" -> {
                    return new byte[size];
                }
                case "short" -> {
                    return new short[size];
                }
                case "long" -> {
                    return new long[size];
                }
                case "double" -> {
                    return new double[size];
                }
                case "float" -> {
                    return new float[size];
                }
                case "char" -> {
                    return new char[size];
                }
                case "boolean" -> {
                    return new boolean[size];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Works only with arrays and matrices (3- and more dimensional arrays aren`t supported)
    public static String print(Object o) {
        StringBuilder res = new StringBuilder();
        if (Array.get(o, 0).getClass().isArray()) {
            int i = Array.getLength(o);
            int j = Array.getLength(Array.get(o, 0));
            res.append(o.getClass().getComponentType().
                    getComponentType().toGenericString()).
                    append("[").append(i).append("][").append(j).append("]:\n{");
            for (int a = 0; a < Array.getLength(o) - 1; a++)
                res.append(printArray(Array.get(o, a))).append(";\n");
            return res.append(printArray(Array.get(o, Array.getLength(o) - 1))).append("}").toString();
        } else return res.append(o.getClass().getComponentType().toGenericString()).
                append("[").append(Array.getLength(o)).append("]: ").
                append(printArray(o)).toString();
    }

    public static Object createMatrix(String str, int rows, int cols) {
        try {
            switch (str) {
                case "int" -> {
                    return new int[rows][cols];
                }
                case "byte" -> {
                    return new byte[rows][cols];
                }
                case "short" -> {
                    return new short[rows][cols];
                }
                case "long" -> {
                    return new long[rows][cols];
                }
                case "double" -> {
                    return new double[rows][cols];
                }
                case "float" -> {
                    return new float[rows][cols];
                }
                case "char" -> {
                    return new char[rows][cols];
                }
                case "boolean" -> {
                    return new boolean[rows][cols];
                }
                default -> {
                    Object obj = Array.newInstance(createArray(str, rows).getClass(), rows);
                    for (int i = 0; i < rows; i++)
                        Array.set(obj, i, createArray(str, cols));
                    return obj;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object addCols(Object o) {
        Object new_array;
        int size = Array.getLength(Array.get(o, 0)) + ARRAY_SIZE;
        new_array = createMatrix(o.getClass().getComponentType().getComponentType().getCanonicalName(),
                Array.getLength(o), size);
        for(int i=0; i<Array.getLength(new_array); i++){
            System.arraycopy(Array.get(o,i), 0,
                    Array.get(new_array,i), 0, Array.getLength(Array.get(o,i)));
        }
        return new_array;
    }

    public static Object addRows(Object o) {
        Object new_array;
        int size = Array.getLength(o) + ARRAY_SIZE;
        new_array = createMatrix(o.getClass().getComponentType().getComponentType().getCanonicalName(),
                size, Array.getLength(Array.get(o, 0)));
        for (int i = 0; i < Array.getLength(o); i++)
            System.arraycopy(Array.get(o, i), 0,
                    Array.get(new_array, i), 0, Array.getLength(Array.get(new_array, i)));
        return new_array;
    }
}
