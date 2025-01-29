package fibonacci;

import java.util.Scanner;

public class FibonacciCalculator {

    // Rekursiv funktion til at beregne Fibonacci-tal
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1; // Base cases
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // Rekursiv kald
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Indtast Fibonacci-indeks (n): ");
        int n = scanner.nextInt();
        
        int result = fibonacci(n);
        System.out.println("Fibonacci nummer " + n + " er " + result);
        
        scanner.close();
    }
}
