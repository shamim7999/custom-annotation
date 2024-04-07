package com.custom.annotation.demo.models;

import com.custom.annotation.demo.annots.Init;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    @Init(max = 3)
    private void initNames() {
        int maxLength = getMaxLength();
        System.out.println("Max: " + maxLength);
        this.firstName = this.firstName.substring(0, maxLength).toUpperCase()
                + this.firstName.substring(maxLength);
        this.lastName = this.lastName.substring(0, maxLength).toUpperCase()
                + this.lastName.substring(maxLength);
    }
    private int getMaxLength() {
        try {
            Method method = getClass().getDeclaredMethod("initNames");
            if (method.isAnnotationPresent(Init.class)) {
                return method.getDeclaredAnnotation(Init.class).max();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Integer.MAX_VALUE; // default value if annotation is not present
    }
    public void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }

        for(Method field : clazz.getDeclaredMethods()) {
            System.out.println("Field: "+field);
        }
     }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
