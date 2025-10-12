package com.itheima.branch;

/**
 * @author zzl
 * @date 2025年10月12日 20:34
 */
public class ForEachObjectArrayDemo {
    public static void main(String[] args) {
        Student[] students={
                new Student("Alice",18),
                new Student("Bob",20),
                new Student("Charlie",19)
        };

        // for-each 遍历自定义对象数组
        System.out.println("for-each 遍历学生数组：");
        for (Object student:students) {
            System.out.println(student);
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}