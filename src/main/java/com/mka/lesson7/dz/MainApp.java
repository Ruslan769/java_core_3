package com.mka.lesson7.dz;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class MainApp {

    public static void main(String[] args) {
        start(TestimApp.class);
    }

    static void start(@NotNull Class clazz) {
        TreeMap<Priority, Method> map = new TreeMap<>();
        Method mBefore = null;
        Method mAfter = null;

        Method[] methods = clazz.getMethods();
        Priority priority = null;
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (mBefore == null) {
                    mBefore = method;
                } else {
                    throw new RuntimeException("Annotation BeforeSuite is");
                }
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (mAfter == null) {
                    mAfter = method;
                } else {
                    throw new RuntimeException("Annotation AfterSuite is");
                }
            }
            if (method.getAnnotation(Test.class) != null) {
                priority = new Priority(method.getAnnotation(Test.class).priority(), method.getName());
                map.put(priority, method);
            }
        }

        try {
            Object object = clazz.getConstructor().newInstance();
            mBefore.invoke(object);
            for (Map.Entry<Priority, Method> mapEntry : map.entrySet()) {
                mapEntry.getValue().invoke(object);
            }
            mAfter.invoke(object);
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
}