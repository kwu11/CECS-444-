public class Token {
    final private int ID;
    final private String Meaning;
    
    public Token(String m) {
        ID = 0;
        Meaning = m;
    }
    
    public Token(int id, String meaning) {
        ID = id;
        Meaning = meaning;
    }
    
    public String getMeaning() {
        return Meaning;
    }
    
    public int getID() {
        return ID;
    }
    
}