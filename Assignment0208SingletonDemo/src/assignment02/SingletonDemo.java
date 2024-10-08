package assignment02;

public class SingletonDemo {
    private static int instanceCount = 0;

    public SingletonDemo() {
        instanceCount++;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public static void main(String[] args) {
        SingletonDemo obj1 = new SingletonDemo();
        SingletonDemo obj2 = new SingletonDemo();
        SingletonDemo obj3 = new SingletonDemo();

        System.out.println("Number of instances created: " + SingletonDemo.getInstanceCount());
    }
}

