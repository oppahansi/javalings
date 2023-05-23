package datatypes.primitives;

import java.text.NumberFormat;

/**
 * Primitives3.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives3 {

  public static void main(String[] args) {
    /* Modify below */

    // Create an 'int' variable named 'x' with the value '900_000_000'

    // Create an 'int' variable named 'y' with the value '700_000_000'

    // Fix this
    int sum = x + y;
    int rest = x - y;
    int div = x / y;
    int prod = x * y;

    /* Modify above */
    var nf = NumberFormat.getInstance();
    nf.setGroupingUsed(true);

    System.out.println("x + y = " + nf.format(sum));
    System.out.println("x - y = " + nf.format(rest));
    System.out.println("x / y = " + nf.format(div) + ", did you expect this?");
    System.out.println("x * y = " + nf.format(prod) + ", did you expect this?");
  }
}
