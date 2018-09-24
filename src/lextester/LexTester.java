import java.util.*;
import java.io.*;

public class LexTester {
    public static void main(String[] args) {
        try {
            String program = "program3.txt";
            Lexer l = new Lexer("lexicon.txt");
            l.tokenize(program);
            l.displayTokens();
            System.out.printf("\n%s contains: %d tokens.\n", program, l.getTokens().size());
        }
        catch (IllegalStateException ise) { System.out.println(ise.getMessage()); }
        catch (FileNotFoundException f) { System.out.println("Error - file not found."); }
        catch (NullPointerException a) { System.out.println("Error - Null pointer."); }
        catch (ArrayIndexOutOfBoundsException a) { System.out.println("Error - Array index is out of bounds."); }
        finally {
            System.out.println("\nTerminating.");
            return;
        }
    }
}