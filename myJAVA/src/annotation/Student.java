package annotation;

//使用注解限定学生的名字不能超过6个字符，年龄范围在18-60

public class Student {
    @NameConstraint(maxLength = 6)
    public String name;
    @AgeConstraint(minAge = 18,maxAge = 60)
    public int age;

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "名字："+name+"年龄："+age;
    }
}
