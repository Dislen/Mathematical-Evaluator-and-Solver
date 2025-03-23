public class Parser 
{
    private TokenType[] tokens;
    private int index = 0;//since the entire class is recursive i need to track the current index here
    
    public Parser(TokenType[] tokens)
    {
        this.tokens = tokens;
    }
    
    public TokenType currentToken() 
    {
        return tokens[index];
    }
    
    public void check(Token expected) 
    {
        if(currentToken().getType() == expected) 
        {
            index++;
        } 
        else if(currentToken().getType() == Token.EOS)//detects EOS 
        {
            return;
        } 
        else 
        {
            throw new RuntimeException("Syntax Error: Expected " + expected + " but found " + currentToken());
        }
    }

    public MathNode expression() 
    {
        MathNode left = term();

        while(currentToken().getType() == Token.PLUS || currentToken().getType() == Token.MINUS)
        {
            char operator = currentToken().getLexeme().charAt(0);
            check(currentToken().getType());
            MathNode right = term();
            left = new OpNode(left, operator, right);
        }

        return left;
    }
    
    //expressionRec() used to be here but we no longer need it

    public MathNode term() 
    {
        MathNode left = factor();

        while(currentToken().getType() == Token.MULTIPLY || currentToken().getType() == Token.DIVIDE)
        {
            char operator = currentToken().getLexeme().charAt(0);
            check(currentToken().getType());
            MathNode right = factor();
            left = new OpNode(left, operator, right);
        }

        return left;
    }
    
    //termRec() used to be here but we no longer need it

    public MathNode factor() 
    {
        if(currentToken().getType() == Token.LEFTPARENTHESES) 
        {
            check(Token.LEFTPARENTHESES);
            MathNode exp = expression();
            check(Token.RIGHTPARENTHESES);
            return exp;
        } 
        else if(currentToken().getType() == Token.INT) 
        {
            int value = Integer.parseInt(currentToken().getLexeme());
            check(Token.INT);
            return new NumNode(value);
        } 
        else if(currentToken().getType() == Token.MINUS)//incase the value is negative
        {
            check(Token.MINUS);
            MathNode inside = factor();
            return new OpNode(new NumNode(0), '-', inside);
        }
        else 
        {
            throw new RuntimeException("Syntax Error: Unexpected token " + currentToken());
        }
    }
}









