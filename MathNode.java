public abstract class MathNode 
{
    abstract int evaluate();
}

//its easier to have the parser spit out one type of node (this one) than have it deal with two different types of nodes for operations and numbers