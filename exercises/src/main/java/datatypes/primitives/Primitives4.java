package datatypes.primitives;

import java.text.NumberFormat;

/**
 * Primitives4.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives4 {

  public static void main(String[] args) {
    /* Modify below */

    // Create a 'long' variable named 'x' with the value '4_000_000_000_000_000_000L'

    // Create a 'long' variable named 'y' with the value '3_000_000_000_000_000_000L'

    // Fix this
    long sum = x + y;
    long rest = x - y;
    long div = x / y;
    long prod = x * y;

    /* Modify above */

    var nf = NumberFormat.getInstance();
    nf.setGroupingUsed(true);

    System.out.println("x + y = " + nf.format(sum));
    System.out.println("x - y = " + nf.format(rest));
    System.out.println("x / y = " + nf.format(div) + ", did you expect this?");
    System.out.println("x * y = " + nf.format(prod) + ", did you expect this?");
  }
}
