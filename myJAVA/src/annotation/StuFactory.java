package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


//注解处理器
public class StuFactory {
    public static <student> Student newInstance(String name, int age) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<Student> clazz = Student.class;
        Field name1 = clazz.getDeclaredField("name");
        if (name1.isAnnotationPresent(NameConstraint.class)){
            NameConstraint annotation = name1.getAnnotation(NameConstraint.class);
            int maxLength = annotation.maxLength();
            if (name == null || name.length()>maxLength){
                throw new IllegalArgumentException("name参数非法:"+name);
            }
        }
        Field age1 = clazz.getDeclaredField("age");
        if (age1.isAnnotationPresent(AgeConstraint.class)){
            AgeConstraint annotation = age1.getAnnotation(AgeConstraint.class);
            int minAge = annotation.minAge();
            int maxAge = annotation.maxAge();
            if (age < minAge || age > maxAge){
                throw new IllegalArgumentException("age参数非法："+age);
            }
        }
//      name合法
        Constructor<Student> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(name,age);

    }
}
