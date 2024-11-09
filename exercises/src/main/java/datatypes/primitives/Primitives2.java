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
        short sum = (short) (x + y);
        short rest = (short) (x - y);
        short div = (short) (x / y);
        short prod = (short) (x * y);

        /* Modify above */
        System.out.println("""
            x + y = """ + sum + """
            x - y = """ + rest + """
            x / y = """ + div + ", did you expect this?" + """
            x * y = """ + prod + ", did you expect this?"
        """);
    }
}