// Character Tester (ct) Interface
interface ct {
    
    // check if c
    static public boolean isOp (final char c) {
        return (isDelimiter(c) || isOtherPunct(c) || isPairedDelimiter(c));
    }
    
    static public boolean isPartialOp(final char c) {
        return (c == '-' || c == '=' || c == '!' || c == '<' || c == '>');
    }
    
    static public boolean isMultiChar(final char c1, final char c2) {
        switch (c1) {
            case '-': return c2 == '>';
            case '=': return c2 == '=';
            case '!': return c2 == '=';
            case '<': return (c2 == '=' || c2 == '<');
            case '>': return (c2 == '=' || c2 == '>');
            default: return false;
        }
    }
    
    static public boolean isSingleOp(final char c) {
        return (isDelimiter(c) || isOtherPunct(c) || isPairedDelimiter(c));
    }
    
    // Tests for operator symbols
    static boolean isDelimiter(final char c) {
        return (c == ',' || c == ';');
    }
    
    static boolean isOtherPunct(final char c) {
        return (c == '*' || c == '^' || c == ':' || c == '.' || c == '=' 
                    || c == '-' || c == '+' || c == '/');
    }
    
    static boolean isPairedDelimiter(final char c) {
        return (c == '<' || c == '>' || c == '('
                    || c == ')'|| c == '{'|| c == '}');
    }
    
    static public boolean isDigit(final char c) {
        return Character.isDigit(c);
    }
    
    static public boolean isIdentifier() {
        return false;
    }
    
    static public boolean isInt() {
        return false;
    }
    
    static public boolean isFloat() {
        return false;
    }
}