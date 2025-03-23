public class OpNode extends MathNode
{
    private MathNode left;
    private MathNode right;
    private char operator;

    public OpNode(MathNode l, char o, MathNode r)
    {
        left = l;
        right = r;
        operator = o;
    }

    public int evaluate()
    {
        int leftValue = left.evaluate();
        int rightValue = right.evaluate();

        switch(operator)
        {
            case '+':
            {return leftValue + rightValue;}

            case '-':
            {return leftValue - rightValue;}

            case '*':
            {return leftValue * rightValue;}

            case '/':
            {return leftValue / rightValue;}

            default:
            {return -99999;}//this doesnt work for every problem within the domain of algebra  
        }
    }
}
