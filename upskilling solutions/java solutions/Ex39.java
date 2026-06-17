import java.lang.reflect.*;

class Student {

    private String name;

    public Student() {

        name = "Krishna";
    }

    public void display() {

        System.out.println(
                "Student Name: " + name
        );
    }
}

public class Ex39 {

    public static void main(String[] args) {

        try {

            Class<?> cls =
                    Class.forName("Student");

            System.out.println(
                    "Class Name: "
                            + cls.getName()
            );

            Constructor<?> constructor =
                    cls.getConstructor();

            Object obj =
                    constructor.newInstance();

            Method method =
                    cls.getMethod("display");

            method.invoke(obj);

            System.out.println(
                    "\nMethods:"
            );

            Method[] methods =
                    cls.getDeclaredMethods();

            for(Method m : methods) {

                System.out.println(
                        m.getName()
                );
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}