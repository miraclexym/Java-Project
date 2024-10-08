package assignment04;

public class Fibonacci {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Fibonacci <count>");
            return;
        }
        int count = Integer.parseInt(args[0]);
        if (count <= 0) {
            System.out.println("Please provide a positive count.");
            return;
        }
        printFibonacciSeries(count);
    }
    public static void printFibonacciSeries(int count) {
        int first = 1;
        int second = 1;
        System.out.println("Fibonacci Series with " + count + " elements:");
        if (count >= 1) {
            System.out.print(first + " ");
        }
        if (count >= 2) {
            System.out.print(second + " ");
        }
        for (int i = 3; i <= count; i++) {
            int next = first + second;
            System.out.print(next + " ");
            first = second;
            second = next;
        }
    }
}

