/**
 *  This program takes user input to specify which file to search for tokens in the A4 language.
 *  The tokens are displayed as output, along with the number of tokens.
 */

import java.util.*;
import java.io.*;

public class LexTester {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the name of the file to tokenize:\n   > ");
            String program = input.nextLine();

            Lexer l = new Lexer();
            l.tokenize(program);
            l.displayTokens();
            System.out.printf("\n%s contains: %d tokens.\n", program, l.getTokens().size());
        }
        catch (FileNotFoundException f) { System.out.println("Error - file not found."); }
        catch (NullPointerException a) { System.out.println("Error - Null pointer."); }
        catch (ArrayIndexOutOfBoundsException a) { System.out.println("Error - Array index is out of bounds."); }
        finally {
            System.out.println("\nTerminating.");
        }
        return;
    }
}