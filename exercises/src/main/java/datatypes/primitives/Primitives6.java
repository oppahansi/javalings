package datatypes.primitives;

/**
 * Primitives6.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives6 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'double' variable named 'x' with the value '21.8'

    // Create a 'double' variable named 'y' with the value '7.4'

    // Fix this
    double sum = x + y;
    double rest = x - y;
    double div = x / y;
    double prod = x * y;

    /* Modify above */
    System.out.println("x + y = " + sum);
    System.out.println("x - y = " + rest);
    System.out.println("x / y = " + div);
    System.out.println("x * y = " + prod);
    System.out.println("""
                
        Some floating point operations can result in numbers
        with strange decimal places.
                
        You have to keep that in mind when doing calculations.
        """);
  }
}
