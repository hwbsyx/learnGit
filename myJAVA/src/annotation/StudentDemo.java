package annotation;

import java.lang.reflect.InvocationTargetException;

public class StudentDemo {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Student stu = StuFactory.newInstance("hhhhhhhhhh", 66);
        System.out.println(stu);
    }
}
