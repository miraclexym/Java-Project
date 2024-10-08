package assignment05;

public class VarArgsExample {
    public static void printStrings(String... strings) {
        System.out.println("Received Strings:");
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        // 调用方法并传递逗号分隔的String列表
        printStrings("apple", "banana", "cherry");
        
        // 也可以传递一个String数组
        String[] fruits = {"orange", "grape", "kiwi"};
        printStrings(fruits);
    }
}
