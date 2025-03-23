public class NumNode extends MathNode
{
    int numValue;

    public NumNode(int numV)
    {
        numValue = numV;
    }

    public int evaluate()
    {
        return numValue;
    }
}
