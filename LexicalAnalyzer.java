import java.util.Hashtable;

class LexicalAnalyzer
{

    Hashtable<Character, Boolean> validTokens = new Hashtable<>()
    {{
        put('(', true);
        put(')', true);
        put('+', true);
        put('-', true);
        put('*', true);
        put('/', true);
        put('=', true);
        put(' ', true);
        put('0', true);
        put('1', true);
        put('2', true);
        put('3', true);
        put('4', true);
        put('5', true);
        put('6', true);
        put('7', true);
        put('8', true);
        put('9', true);
        put('$', true);
    }};

    public Boolean inputsValid(String ex)
    {
        for(Character c : ex.toCharArray())
        {
            if(validTokens.get(c) == null)
            {
                return false;
            }
        }

        return true;
    } 

    public TokenType[] fillTokens(String ex)
    {
        TokenType[] tokenBox = new TokenType[ex.length() + 1];//added in P2 to deal with EOS
        
        int i;
        for(i = 0; i < ex.length(); i++)
        {
            char currentChar = ex.charAt(i);
            TokenType token; 
    
            if(Character.isDigit(currentChar))
            {
                token = new TokenType(Token.INT, String.valueOf(currentChar)); 
                tokenBox[i] = token;
                continue;
            }
    
            switch(currentChar)
            {
                case '+': 
                {
                    token = new TokenType(Token.PLUS, "+");
                    tokenBox[i] = token; 
                    break;
                }
                case '-': 
                {
                    token = new TokenType(Token.MINUS, "-");
                    tokenBox[i] = token; 
                    break;
                }
                case '*': 
                {
                    token = new TokenType(Token.MULTIPLY, "*"); 
                    tokenBox[i] = token;
                    break;
                }
                case '/': 
                {
                    token = new TokenType(Token.DIVIDE, "/"); 
                    tokenBox[i] = token;
                    break;
                }
                case '=': 
                {
                    token = new TokenType(Token.EQUALS, "="); 
                    tokenBox[i] = token;
                    break;
                }
                case ' ': 
                {
                    break;//does nothing
                }
                case '(':
                {
                    token = new TokenType(Token.LEFTPARENTHESES, "(");
                    tokenBox[i] = token;
                    break;
                }
                case ')':
                {
                    token = new TokenType(Token.RIGHTPARENTHESES, ")");
                    tokenBox[i] = token;
                    break;
                }
                default: 
                {
                    token = new TokenType(Token.NOTVALID, String.valueOf(currentChar)); 
                    tokenBox[i] = token;
                    break;
                }
            }
        }

        tokenBox[i] = new TokenType(Token.EOS, "$");
    
        return tokenBox;
    }
    




}
