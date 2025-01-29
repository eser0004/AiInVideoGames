package factorial;
import java.util.Scanner;

public class FactorialCalculator {

    // Rekursiv funktion til at beregne fakultet
    public static int factorial(int n) {
        if (n == 0) {
            return 1;  // Base case: 0! = 1
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Brugerinput
        System.out.print("Indtast et naturligt tal: ");
        int n = scanner.nextInt();
        
        if (n < 0) {
            System.out.println("Fakultet er ikke defineret for negative tal.");
        } else {
            // Beregning og output
            int result = factorial(n);
            System.out.println(n + "! = " + result);
        }
        
        scanner.close();
    }
}
