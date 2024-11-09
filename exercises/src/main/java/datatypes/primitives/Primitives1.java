package datatypes.primitives;

/**
 * Primitives1.java
 * <p>
 * Goal: Make the code compile.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Primitives1 {

    public static void main(String[] args) {
        /* Modify below */

        // Create a 'byte' variable named 'x' with the value '24'

        // Create a 'byte' variable named 'y' with the value '18'

        // Fix this
        byte sum = (byte) (x + y);
        byte rest = (byte) (x - y);
        byte div = (byte) (x / y);
        byte prod = (byte) (x * y);

        /* Modify above */
        System.out.println("""
                x + y = %d
                x - y = %d
                x / y = %d, did you expect this?
                x * y = %d, did you expect this?
                """.formatted(sum, rest, div, prod));
    }

}
