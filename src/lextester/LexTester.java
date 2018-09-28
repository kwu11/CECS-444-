/**
 *  Author: Kyle Wu
 *  This program takes user input to specify which file to search for tokens in the A4 language.
 *  The tokens are displayed as output, along with the number of tokens in the program.
 */

import java.util.*;
import java.io.*;

public class LexTester {
    public static void main(String[] args) {
        try {
            String program;
            if (args.length > 0) {
                for (String arg : args) {
                    program = arg;

                    Lexer l = new Lexer();
                    l.tokenize(program);
                    l.displayTokens();
                    System.out.printf("\n%s contains: %d tokens.\n", program, l.getTokens().size());
                    System.out.println("________________________________________________");
                }
            }

            else {
                Lexer l = new Lexer();

                Scanner input = new Scanner(System.in);
                System.out.print("Enter the name of the file to tokenize:\n> ");
                program = input.nextLine();
                input.close();
                
                l.tokenize(program);
                l.displayTokens();
                System.out.printf("\n%s contains: %d tokens.\n", program, l.getTokens().size());
                System.out.println("________________________________________________");
            }

        }
        catch (FileNotFoundException f) {}
        catch (NullPointerException a) { System.out.println("Error - Null pointer."); }
        catch (ArrayIndexOutOfBoundsException a) { System.out.println("Error - Array index is out of bounds."); }
        finally {
            System.out.println("\nTerminating.");
        }
        return;
    }
}