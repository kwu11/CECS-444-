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
        catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
        }
        catch (FileNotFoundException f) {
            System.out.println("Error - file not found.");
        }
        catch (NullPointerException a) {
            System.out.println("Error - Null pointer");
        }
        catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Here");
        }
        finally {
            System.out.println("\nTerminating.");
            return;
        }
        
        
        
//        int count = 0;
//        
//        System.out.println("Testing if string contains digits:");
//        for (int i = 0; i < s.length(); i++) {
//            if (CharTester.isDigit(s.charAt(i))) 
//                count++;
//        }
//        System.out.println("String contains " + count + " digits.");
//        count = 0;
//        
//        
//        System.out.println("Testing if string contains delimiters:");
//        for (int i = 0; i < s.length(); i++) {
//            if (CharTester.isDelimiter(s.charAt(i))) 
//                count++;
//        }
//        System.out.println("String contains " + count + " delimiters.");
//        count = 0;
//        
//        System.out.println("Testing if string contains paired delimiters:");
//        for (int i = 0; i < s.length(); i++) {
//            if (CharTester.isPairedDelimiter(s.charAt(i))) 
//                count++;
//        }
//        System.out.println("String contains " + count + " paired delimiters.");
//        count = 0;
//        
//        System.out.println("Testing if string contains other punctuation:");
//        for (int i = 0; i < s.length(); i++) {
//            if (CharTester.isOtherPunct(s.charAt(i))) 
//                count++;
//        }
//        System.out.println("String contains " + count + " other punctuation.");
//        count = 0;
    }
}