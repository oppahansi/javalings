package intros;

/**
 * Intro1.java
 * <p>
 * Goal: Print 'Hello, world!' to the console.
 * <p>
 * Type 'hint' in the console to get a hint.
 */

// I AM NOT DONE

public class Intro0 {

  public static void main(String[] args) {
    System.out.println(
        """
            Hello and
                     welcome to...
                      
                             _                  _ _
                            (_)                | (_)
                             _  __ ___   ____ _| |_ _ __   __ _ ___
                            | |/ _` \\ \\ / / _` | | | '_ \\ / _` / __|
                            | | (_| |\\ V / (_| | | | | | | (_| \\__ \\
                            | |\\__,_| \\_/ \\__,_|_|_|_| |_|\\__, |___/
                           _/ |                            __/ |   \s
                          |__/                            |___/    \s
            """);

    System.out.println();

    System.out.println(
        """
           This exercise compiles successfully. The remaining exercises contain a compiler
           or logic error. The central concept behind Javalings is to fix these errors and
           solve the exercises.
                       
           The source code for all the exercises is in
           `exercises/src/main/java`.
                       
           The source code for this introduction is in
           `exercises/src/main/java/intros/Intro0.java`. Have a look!
                       
           Going forward, the source code of the exercises will always be in the success/failure output.
                       
                                           Good luck!
                       
           Javalings was inspired by rust's interactive exercises : 'rustlings'.""");
  }
}
