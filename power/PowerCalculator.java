package power;
import java.util.Scanner;

public class PowerCalculator {
      // Rekursiv funktion til at beregne base^exponent
      public static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;   // Base case: ethvert tal opløftet til 0 er 1
        } else {
            return base * power(base, exponent - 1);
        }
    } 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

         // Brugerinput
        System.out.print("Indtast base: ");
        int base = scanner.nextInt();
        
        System.out.print("Indtast exponent: ");
        int exponent = scanner.nextInt();
        
        // Beregning og output
        int result = power(base, exponent);
        System.out.println(base + "^" + exponent + " = " + result);
        
        scanner.close();
      
    }
        
    
}