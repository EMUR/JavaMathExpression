//Homework 7
//Eyad Murshid
// 06/22/2015
// intelliJ  -> Mac OS X 10.10
// This applcation works exactly as intended, it checks inputs, and it run just as it should ! :D


public interface MathExpression  {

    abstract double evaluate();

}

class MathValue implements MathExpression
{
    private double doubValue;

    public MathValue(double v)
    {
        this.doubValue = v;
    }

    @Override
    public double evaluate()
    {
        return  doubValue;
    }

    @Override
    public String toString()
    {
        return String.valueOf(doubValue);
    }


}

abstract class MathBinaryExpression implements MathExpression
{
    protected MathExpression leftExpression;
    protected MathExpression rightExpression;

    public MathBinaryExpression(MathExpression left, MathExpression right)
    {
        if(right != null)
        {
            this.rightExpression = right;
        }
        else
        {
            this.rightExpression = new MathValue(0);
        }

        if(left != null)
        {
            this.leftExpression = left;
        }
        else
        {
            this.leftExpression = new MathValue(0);
        }

    }

    MathExpression getRightExpression()
    {
        return this.rightExpression;
    }

    MathExpression getLeftExpression()
    {
        return this.leftExpression;
    }


}


class AddExpression extends MathBinaryExpression
{
    public AddExpression(MathExpression left, MathExpression right)
    {
        super(left,right);
    }

    @Override
    public double evaluate() {
        return rightExpression.evaluate() + leftExpression.evaluate();
    }

    @Override
    public String toString()
    {
        return String.valueOf("( " + leftExpression.toString() + " + " + rightExpression.toString() + " )");
    }

}

class SubExpression extends MathBinaryExpression
{
    public SubExpression(MathExpression left, MathExpression right)
    {
        super(left,right);
    }

    @Override
    public double evaluate() {
        return leftExpression.evaluate() - rightExpression.evaluate();
    }

    @Override
    public String toString()
    {
        return String.valueOf("( " + leftExpression.toString() + " - " + rightExpression.toString() + " )");
    }
}

class MultExpression extends MathBinaryExpression
{
    public MultExpression(MathExpression left, MathExpression right)
    {
        super(left,right);
    }

    @Override
    public double evaluate() {
        return leftExpression.evaluate() * rightExpression.evaluate();
    }

    @Override
    public String toString()
    {
        return String.valueOf("( " + leftExpression.toString() + " x " + rightExpression.toString() + " )");
    }



}

class DivExpression extends MathBinaryExpression
{
    public DivExpression(MathExpression left, MathExpression right)
    {
        super(left,right);
    }

    @Override
    public double evaluate() {
        return leftExpression.evaluate() / rightExpression.evaluate();
    }

    @Override
    public String toString()
    {
        return String.valueOf("( " + leftExpression.toString() + " / " + rightExpression.toString() + " )");
    }
}