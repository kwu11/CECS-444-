/**
 *  This interface includes methods to test a character for specific tokens such as punctuation,
 *  delimiters (paired and unpaired), math operators, etc.
 */
interface ct {
    // Tests a pair of chars for 'comment' token
    public static boolean isComment (final char c1, final char c2) {
        return c1 == '/' && c2 == '/';
    }

    // Tests a string for 'identifier' form
    public static boolean isIdentifier(final String str) {
        if (str.length() > 0) {
            char t = str.charAt(0);
            if (t == '_' || Character.isLetter(t)) {
                for (int j = 1; j < str.length(); j++) {
                    char next = str.charAt(j);
                    if ( !(next == '_' || Character.isLetter(next) || Character.isDigit(next)) )
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    // Tests a char for a valid float char
    public static  boolean isFloatForm (final char c) {
        return Character.isDigit(c) || c == '.';
    }

    // Tests a char for a non-alphanumeric symbol in the A4 lexicon
    public static boolean isOp (final char c) {
        return (isDelimiter(c) || isOtherPunct(c) || isPairedDelimiter(c) || isPartialOp(c));
    }

    // Tests a char for symbols that can make up two-symbol operators
    public static boolean isPartialOp(final char c) {
        return (c == '-' || c == '=' || c == '!' || c == '<' || c == '>');
    }

    // Tests a pair of chars for two-symbol operators in the lexicon
    public static boolean isMultiChar(final char c1, final char c2) {
        switch (c1) {
            case '-': return c2 == '>';
            case '=': return c2 == '=';
            case '!': return c2 == '=';
            case '<': return (c2 == '=' || c2 == '<');
            case '>': return (c2 == '=' || c2 == '>');
            default: return false;
        }
    }
    
    // Tests a char for operator symbols
    public static boolean isDelimiter(final char c) {
        return (c == ',' || c == ';');
    }

    // Tests a char for other punctuation
    public static boolean isOtherPunct(final char c) {
        return (c == '*' || c == '^' || c == ':' || c == '.' || c == '='
                    || c == '-' || c == '+' || c == '/');
    }

    // Tests a char for paired delimiters
    public static boolean isPairedDelimiter(final char c) {
        return (   c == '<' || c == '>'
                || c == '(' || c == ')'
                || c == '{' || c == '}'
                || c == '[' || c == ']');
    }
}