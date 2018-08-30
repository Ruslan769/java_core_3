package com.mka.lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class People {
    public String name;
    private int age;

    public People() {

    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @TestAnnot
    private void say() {
        System.out.println("Hello!");
    }

    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ReflectionApp {

    static Class clazz = new ArrayList<>().getClass();

    public static void main(String[] args) {

        String name = clazz.getName();
        String simpleName = clazz.getSimpleName();
        String canonicalName = clazz.getCanonicalName();

        peopleMethod();

        /*printHead();
        printConstructors();
        printFields();
        printMethods();
        printFooter();*/
    }

    private static void call() {
        // method 1
        final String str = new String();
        final Class clazz = str.getClass();

        // method 2
        final Class clazz2 = String.class;

        // method 3
        Class clazz3 = null;
        try {
            clazz3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clazz3);
    }

    static void peopleField() {
        People people = new People("Saniya", 45);
        Class clazz2 = people.getClass();
        try {
            Field f = clazz2.getDeclaredField("age");
            f.setAccessible(true); // доступ к приватному полю
            f.setInt(people, 35); // изменить значение поля
            System.out.println(f.get(people));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void peopleInstance() {
        Class clazz2 = People.class;

        try {
            // без аргументов
            People people = (People) clazz2.getConstructor().newInstance(); // создаем экземпляр класса
            // с аргументами
            People people2 = (People) clazz2.getConstructor(String.class, int.class).newInstance("Vasia", 58); // создаем экземпляр класса
            System.out.println(people2);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static void peopleMethod() {
        Class clazz2 = People.class;

        try {
            People people = (People) clazz2.getConstructor(String.class, int.class).newInstance("Vasia", 54); // создаем экземпляр класса
            Method m1 = clazz2.getDeclaredMethod("getAge"); // без аргументов
            System.out.println(m1.invoke(people));
            Method m2 = clazz2.getDeclaredMethod("sum", int.class, int.class); // с аргументами
            System.out.println(m2.invoke(people, 2, 9));
            Method m3 = clazz2.getDeclaredMethod("say");
           /* m3.setAccessible(true);
            m3.invoke(people);*/
            Annotation an = m3.getAnnotation(TestAnnot.class);
            System.out.println(an);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void printHead() {
        System.out.println("class " + clazz.getSimpleName() + " {");
    }

    private static void printFooter() {
        System.out.println("}");
    }

    private static void printConstructors() {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("    " + Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() +
            " (" + Arrays.toString(constructor.getParameters()) + ")");
        }
        System.out.println("");
    }

    private static void printFields() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("    " + Modifier.toString(field.getModifiers()) + " " + field.getType() + " " + field.getName());
        }
        System.out.println("");
    }

    private static void printMethods() {
        Method[] methods = clazz.getDeclaredMethods(); // получить и приватные методы
        for (Method method : methods) {
            System.out.println("    " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " + method.getName());
        }
    }
}
