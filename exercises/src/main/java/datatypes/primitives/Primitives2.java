package datatypes.primitives;

/**
 * Primitives2.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives2 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'short' variable named 'x' with the value '12_500'

    // Create a 'short' variable named 'y' with the value '4_000'

    // Fix this
    short sum = x + y;
    short rest = x - y;
    short div = x / y;
    short prod = x * y;

    /* Modify above */
    System.out.println("x + y = " + sum);
    System.out.println("x - y = " + rest);
    System.out.println("x / y = " + div + ", did you expect this?");
    System.out.println("x * y = " + prod + ", did you expect this?");
  }
}