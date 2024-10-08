package assignment03;

public class StringComparator {
    public static void compareStrings(String str1, String str2) {
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 != str2: " + (str1 != str2));
        System.out.println("str1.equals(str2): " + str1.equals(str2));
    }

    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        String str4 = "World";

        System.out.println("Comparing str1 and str2:");
        compareStrings(str1, str2);

        System.out.println("\nComparing str1 and str3:");
        compareStrings(str1, str3);

        System.out.println("\nComparing str1 and str4:");
        compareStrings(str1, str4);
    }
}

