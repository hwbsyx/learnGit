import com.sun.jdi.PathSearchingVirtualMachine;

//Lambda表达式与匿名内部类相似之处
@FunctionalInterface
interface Displayable{
    void display();
    default int add(int a,int b){
        return a+b;
    }
}


public class LambdaAndInner {
    private int age = 12;
    private static String name  = "疯狂软件教育中心";
    public void test(){
        var book = "疯狂Java讲义";
        //Lambda表达式写法
       /* Displayable dis = () ->{
            //访问"effectively final"的局部变量
            System.out.println("book的局部变量为"+ book);
            //访问外部类的实例变量和类变量
            System.out.println("外部类的age变量为"+ age);
            System.out.println("外部类的name变量为"+ name);

        };*/

//        匿名内部类写法
        Displayable dis = new Displayable(){
            public void display(){
                System.out.println("book的局部变量为"+ book);
                System.out.println("外部类的age变量为"+ age);
                System.out.println("外部类的name变量为"+ name);
                System.out.println(add(3,5));
            }

        };

        dis.display();
        //调用dis对象从接口中继承的方法
        System.out.println(dis.add(3,5));
    }

    public static void main(String[] args) {
        var lambda = new LambdaAndInner();
        lambda.test();
    }
}
