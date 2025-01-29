package power;
import java.util.Scanner;

public class PowerOptimized {
    public static int powerOptimized(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        int halfPower = powerOptimized(base, exponent / 2);
        
        if (exponent % 2 == 0) {
            return halfPower * halfPower;  // Hvis exponent er lige
        } else {
            return base * halfPower * halfPower;  // Hvis exponent er ulige
        }
    }
    public static double powerWithNegative(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent < 0) {
            return 1.0 / powerWithNegative(base, -exponent);
        }
        return base * powerWithNegative(base, exponent - 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

         // Brugerinput
        System.out.print("Indtast base: ");
        int base = scanner.nextInt();
        
        System.out.print("Indtast exponent: ");
        int exponent = scanner.nextInt();
        
        // Beregning og output
        int result = powerOptimized(base, exponent);
        System.out.println(base + "^" + exponent + " = " + result);
        
        scanner.close();
      
    }    

   
}
        
    
