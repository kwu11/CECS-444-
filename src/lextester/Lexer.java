/**
 *  Author: Kyle Wu
 *  This is the core of the program. The lexer tokenizes a given file and outputs the tokens and
 *  the number of tokens in the file.
 */

import java.util.*;
import java.io.*;

public class Lexer implements ct {
    private HashMap<String, Token> lexicon;    // Map containing token productions
    private ArrayList<Token> tokenList;
    private static Scanner in;

    public Lexer() throws java.io.FileNotFoundException {
        String dictionary = "lexicon.txt";
        File file = new File(dictionary);
        if (!file.exists()) {
            System.out.println("Error - cannot find dictionary file: " + dictionary);
            throw new FileNotFoundException();
        }

        lexicon = new HashMap<>();
        tokenList = new ArrayList<>();
        in = new Scanner(file);

        // Initialize language dictionary
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] arr = line.split("\t");

            // Assert messages don't work
            assert arr.length == 3 : "Expected 3 token arguments, received " + arr.length + "\n";
            int id = Integer.parseInt(arr[0]);
            String meaning = arr[1];
            String key = arr[2];

            lexicon.put(key, new Token(id, meaning));
        }
        in.close();

    }

    /**
     * Processes each word in the program's code for tokens in the A4 language.
     *
     * @param program - The name of the program
     * @throws java.io.FileNotFoundException
     */
    public void tokenize(String program) throws java.io.FileNotFoundException {
        File file = new File(program);
        if (!file.exists()) {
            System.out.println("Error - cannot find file: " + program);
            throw new FileNotFoundException();
        }

        in = new Scanner(new File(program));
        int lineNum = 0;

        System.out.println("\nFound file: " + program);

        while (in.hasNextLine()) {
            final char[] line = in.nextLine().toCharArray();
            final int EOL = line.length-1;
            String builtWord = "";

            lineNum++;
            // Read each char
            for (int i = 0; i < line.length; i++) {
                char c = line[i];

                /**
                 *  Test cases
                 */
                // Strings
                if (c == '"') {
                    String string = "";    // The string enclosed in quotations
                    i++; // advance past double quote mark

                    while (i < line.length && line[i] != '"')
                        string += line[i++];
                    if (line[i] == '"')
                        tokenList.add(new StringToken(string, lineNum));
                    else {
                        System.out.println("Error - Matching  \"  not found on line:" + lineNum);
                        return;
                    }
                }

                // Spaces and tabs
                else if (c == ' ' || c == '\t') {
                    if (lexicon.containsKey(builtWord))
                        tokenList.add(new Token(lexicon.get(builtWord).getID(), builtWord, lineNum));
                    else if (ct.isIdentifier(builtWord))
                        tokenList.add(new Token(2, builtWord, lineNum));

                    builtWord = "";
                    continue;
                }

                // Punctuation or other symbols
                else if (ct.isOp(c)) {
                    // Tokenize keyword or identifier first (e.g., 'print' in "print()")
                    if (lexicon.containsKey(builtWord))
                        tokenList.add(new Token(lexicon.get(builtWord).getID(), builtWord, lineNum));
                    else if (ct.isIdentifier(builtWord))
                        tokenList.add(new Token(2, builtWord, lineNum));

                        String operator = Character.toString(c);
                        // Test multi-symbol (MS) operators (e.g. '<' of "<=")
                        if (i+1 <= EOL) {
                            char nextChar = line[i+1];

                            // Distinguish divide symbol from comment
                            if (ct.isComment(c, nextChar)) {
                                i++;
                                builtWord = "";
                                break;
                            }
                            else if (ct.isPartialOp(c)) 
                                if (ct.isMultiChar(c, nextChar)) {
                                    i++;
                                    operator += Character.toString(nextChar);
                                }
                        }
                        if (lexicon.containsKey(operator))
                            tokenList.add(new Token(lexicon.get(operator).getID(), operator, lineNum));

                    builtWord = "";
                    continue;
                }

                // Floats and integers
                else if (Character.isDigit(c) && builtWord == "") {
                    String number = "";
                    boolean hasDec = false;

                    while (i < line.length && ct.isFloatForm(line[i])) {
                        if (line[i] == '.') {
                            if (hasDec) {
                                System.out.println("Error - invalid '.' found");
                                return;
                            }
                            else hasDec = true;
                        }
                        number += line[i];
                        i++;
                    }
                    i--; // decrement because the char causing the exit condition is needed

                    if (number.contains("."))
                        tokenList.add( new FloatToken(Float.parseFloat(number), lineNum) );
                    else
                        tokenList.add( new IntToken(Integer.parseInt(number), lineNum) );
                    continue;
                }
                else builtWord += c;
            }
            // End-of-line words/identifiers
            if (lexicon.containsKey(builtWord))
                tokenList.add(new Token(lexicon.get(builtWord).getID(), builtWord, lineNum));
            else if (ct.isIdentifier(builtWord))
                tokenList.add(new Token(2, builtWord, lineNum));
        }
        tokenList.add(new Token(0, "", lineNum));
        System.out.println("\n----Program tokenized----\n");
        in.close();
        return;
    }

    /**
     * Returns the tokens in the analyzed file.
     * @return  the list of tokens
     */
    public ArrayList<Token> getTokens() { return tokenList; }

    /**
     * Displays the tokens in the list as formatted text.
     */
    public void displayTokens() { for (Token t : tokenList) System.out.println(t.toString()); }

    /**
     *  Closes any resources used by the class.
     */
    public void close() { in.close(); }
}