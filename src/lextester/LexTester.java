/**
 *  Author: Kyle Wu
 *  This program takes user input to specify which file to search for tokens in the A4 language.
 *  The output is the tokens identified in string form, the number of tokens in the file, and
 *  a text file containing the same list of string-form tokens.
 */

import java.util.*;
import java.io.*;

public class LexTester {
    /**
     *  Outputs a text file containing the tokens for a given list of tokens
     * @param tl            The array list of tokens
     * @param fileName      The name of the output file
     * @throws FileNotFoundException
     */
    public static void createTokenFile(ArrayList<Token> tl, String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter( String.format(".\\Output Token Files\\%s", fileName) );
        for (Token t : tl)
            writer.println(t.toString());
        writer.close();
    }

    public static void main(String[] args) {
        try {
            String program;
            if (args.length > 0) {
                int count = 1;

                for (String arg : args) {
                    Lexer l = new Lexer();
                    program = arg;

                    l.tokenize(program);
                    createTokenFile(l.getTokens(), String.format("tokens%d.txt", count));
                    l.displayTokens();
                    System.out.printf("\n%s contains: %d tokens.\n", program, l.getTokens().size());
                    System.out.println("________________________________________________");

                    count++;
                }
            }

            else {
                Lexer l = new Lexer();
                Scanner input = new Scanner(System.in);

                System.out.print("Enter the name of the file to tokenize:\n> ");
                program = input.nextLine();
                input.close();

                l.tokenize(program);
                createTokenFile(l.getTokens(), "tokens.txt");
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