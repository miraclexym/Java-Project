package assignment05;

public class VarArgsExample {
    public static void printStrings(String... strings) {
        System.out.println("Received Strings:");
        for (String str : strings) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        // ���÷��������ݶ��ŷָ���String�б�
        printStrings("apple", "banana", "cherry");
        
        // Ҳ���Դ���һ��String����
        String[] fruits = {"orange", "grape", "kiwi"};
        printStrings(fruits);
    }
}
