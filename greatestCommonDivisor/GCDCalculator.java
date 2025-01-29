package greatestCommonDivisor;
import java.util.Scanner;

public class GCDCalculator {

    // Rekursiv funktion til at beregne GCD
    public static int gcd(int a, int b) {
        if (a == b) {
            return a; // Base case
        }
        if (a > b) {
            return gcd(a - b, b); // Rekursivt kald hvis a > b
        }
        return gcd(a, b - a); // Rekursivt kald hvis a < b
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Brugerinput
        System.out.print("Indtast første tal: ");
        int a = scanner.nextInt();
        
        System.out.print("Indtast andet tal: ");
        int b = scanner.nextInt();
        
        // Beregning og output
        int result = gcd(a, b);
        System.out.println("GCD(" + a + ", " + b + ") = " + result);
        
        scanner.close();
    }
}
