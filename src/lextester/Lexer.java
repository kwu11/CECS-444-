import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.ArrayList;

public class Lexer implements ct {
    private HashMap<String, Token> lexicon;    // Map containing token productions
    private HashMap<String, ArrayList<Integer>> tknLocations;    // 
    private Scanner in;
    
    public Lexer(String dictionary) throws java.io.FileNotFoundException {
        lexicon = new HashMap<>();
        tknLocations = new HashMap<>();
        in = new Scanner(new FileInputStream(dictionary));

        // Initialize language dictionary and token locations map
        while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] arr = line.split("\t");

                assert arr.length == 3 : "Expected 3 token arguments, receive " + arr.length + "\n";
                    int id = Integer.parseInt(arr[0]);
                    String meaning = arr[1],
                        key = arr[2];
                
                    lexicon.put(key, new Token(id, meaning));
                    tknLocations.put(meaning, new ArrayList<>());
        }
        lexicon.put("string", new Token(5, "str"));
        tknLocations.put("str", new ArrayList<>());
        /**
         *  Handle special cases:
         *  identifiers
         *  integers
         *  floats
         *  strings
         *  float tokens (optional fields)
         *  int tokens (optional fields)
          */

        System.out.println("Map contains: " + lexicon.size() + " keys.");
        in.close();
    }
    
    // Store the token's line origin in the 'token locations' map
    private void updateTokenLocations(String keyword, int lineNr) {
        if (tknLocations.containsKey(keyword))
            tknLocations.get(keyword).add(lineNr);
    }
    
    public void doSomething(String program) throws java.io.FileNotFoundException {
        in = new Scanner(new FileInputStream(program));
        int lineNum = 0;
        
        /**
         * breaks go to next line, 
         * continue goes to next character (word because inherently whitespace delimited).
         **/
        
        while (in.hasNextLine()) {
            lineNum++;
            char[] line = in.nextLine().toCharArray();
            String builtWord = "";
            
            for (int i = 0, j = 0; i < line.length; i++) {
                char c = line[i];

                /**
                 *  URGENT:
                 *  Data structure for containing tokens needs to be redesigned,
                 *  no order, special tokens can't be accessed for their values
                 *  (strings, ints, floats, etc.)
                 */
                // Encounter a string
                if (c == '"') {
                    String str = "";

                    if (i+1 < line.length) {
                        while (++i < line.length) {
                            c = line[i];

                            if (c == '"') {
                                tknLocations.get("str").add(lineNum);
                                break;
                            }

                            str += c;
                        }
                    }
                    else {
                        System.out.printf("Error at line %d: closing double-quote " +
                                          "not found on current line")
                    }
                }

                
                else if (c == ' ') {     // whitespace "delimiter"
                    if (builtWord.contains("//"))    // Comments
                        break; 
                    
                    // check map for word
                    if (lexicon.containsKey(builtWord)) {
                        updateTokenLocations(builtWord, lineNum);
                        continue;
                    }
                    
                    // reset for next word
                    builtWord = "";
                    continue;
                }
                
                // word contains operator symbol
                else if (ct.isOp(c)) {
                    // Find actual letter builtWords in map (e.g., 'print' in "print()")
                    if (lexicon.containsKey(builtWord))
                        updateTokenLocations(builtWord, lineNum);
                    else {
                        // To-do: also handle identifiers
                    }
                    
                    // Test multi-symbol (MS) operators (e.g. '<' of "<=")
                    if (ct.isPartialOp(c)) {   
                        String operator = Character.toString(c);
                        // Test end of line
                        if (i+1 <= line.length) {
                            char nextChar = line[i+1];
                            
                            // Look ahead for possible MS operator
                            if (ct.isMultiChar(c, nextChar)) {
                                operator += Character.toString(nextChar);
                                i++;
                            }
                        }
                        updateTokenLocations(operator, lineNum);
                        continue;
                    }
                    
                    builtWord = "";
                }
                else builtWord += c;
                j = i;
            }
        }
    
            
//            String[] builtWords = in.nextLine().split(" ");
//            
//            // Read each builtWord in the line
//            for (String builtWord : builtWords) {
//                if (builtWord == " ") continue;    // extra spaces after string split
//                if (builtWord.contains("//")) break; // exit current line if comment 
//                
//                
//                // Check if whole builtWord exists in lexicon
//                if (lexicon.containsKey(builtWord)) {
//                    updateTokenLocations(builtWord, lineNum);
//                    continue;
//                }
//                if (builtWord.contains("\"")) {
//                    // Problematic with String wrapper class delimiter method
//                    containsString = true;
////                    if (
//                }
//                
//                String partialWord = "";
//                
//                // Otherwise, read the builtWord character-by-character for concatenated token builtWords  
//                for (int i = 0; i < builtWord.length(); i++) {
//                    char c = builtWord.charAt(i);
//                    String C = Character.toString(c);
//                    
//                    // Not a letter, digit, or underscore
//                    if (ct.isOp(c)) {
//                        // Find actual letter builtWords in map (e.g., 'print' in "print()")
//                        if (lexicon.containsKey(partialWord)) {
//                            // To-do: also handle identifiers
//                            updateTokenLocations(partialWord, lineNum);
//                        }
//                        
//                        if (ct.isPartialOp(c)) {    // Test multi-symbol operators (e.g. '<' of "<=")
//                            // WIP
//                            String cStr = Character.toString(c);
//                            if (i+1 < builtWord.length()) {
//                                char nextChar = builtWord.charAt(i+1); // next char in the builtWord
//                                if (ct.isMultiChar(c, nextChar)) {
//                                    updateTokenLocations(cStr + Character.toString(nextChar), lineNum);
//                                    i++;
//                                }
//                                else if (lexicon.containsKey(cStr)) { 
//                                    updateTokenLocations(cStr, lineNum);
//                                }
//                            }
//                        }
//                        else if (ct.isSingleOp(c)) {
//                            if (lexicon.containsKey(c)) 
//                                updateTokenLocations(C, lineNum);
//                            else {
////                                System.out.printf("Char '%c' is not a token in the lexicon.\n", c);
////                                To-do: error handling for invalid symbols
//                                
//                                return;
//                            }
//                        }
//                        
//                    }
//                    else {
//                        partialWord += c;
//                    }
//                } 
//            }
//        }
        in.close();
        return;
    }
}