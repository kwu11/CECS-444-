// Character Tester (ct) Interface
interface ct {

    public static boolean isComment (final char c1, final char c2) {
        return c1 == '/' && c2 == '/';
    }

    public static boolean isIdentifier(final String str) {
        if (str.length() > 0) {
            char t = str.charAt(0);
            if (t == '_' || Character.isLetter(t)) {
                for (int j = 1; j < str.length(); j++) {
                    char next = str.charAt(j);
                    if (!(next == '_' || Character.isLetter(next) || Character.isDigit(next)))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    public static  boolean isFloatForm (final char c) {
        return Character.isDigit(c) || c == '.';
    }

    public static boolean isOp (final char c) {
        return (isDelimiter(c) || isOtherPunct(c) || isPairedDelimiter(c) || isPartialOp(c));
    }

    public static boolean isPartialOp(final char c) {
        return (c == '-' || c == '=' || c == '!' || c == '<' || c == '>');
    }

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
    
    public static boolean isSingleOp(final char c) {
        return (isDelimiter(c) || isOtherPunct(c) || isPairedDelimiter(c));
    }
    
    // Tests for operator symbols
    public static boolean isDelimiter(final char c) {
        return (c == ',' || c == ';');
    }
    
    public static boolean isOtherPunct(final char c) {
        return (c == '*' || c == '^' || c == ':' || c == '.' || c == '='
                    || c == '-' || c == '+' || c == '/');
    }
    
    public static boolean isPairedDelimiter(final char c) {
        return (   c == '<' || c == '>'
                || c == '(' || c == ')'
                || c == '{' || c == '}'
                || c == '[' || c == ']');
    }
}