import java.util.*;
import java.io.*;

public class LexTester {
    public static void main(String[] args) {
        try {
            Lexer l = new Lexer("new 2.txt");
        }              
        catch (FileNotFoundException f) {
            System.out.println("Error - file not found.");
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