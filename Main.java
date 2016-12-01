import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by E on 6/21/15.
 */
public class Main {

    static public void main(String arr[]) {
        TreeMap<String, MathExpression> exprs;
        exprs = readIn();
        Display(exprs);
        System.out.println("\nThere is/are " + numberOfSimpleEx(exprs) + " simple binary expression(s)");

    }

    static void Display(TreeMap<String, MathExpression> exprs)
    {
        for(Map.Entry<String,MathExpression> entry : exprs.entrySet()) {
            String key = entry.getKey();
            MathExpression value = entry.getValue();

            if (value == null)
            {
                System.out.println(key);
            }
            else
            {
                System.out.println(key + " = " + value.evaluate());
            }
        }
    }

    static int numberOfSimpleEx(TreeMap<String, MathExpression> exprs)
    {
        int countBinary = 0;

        for(Map.Entry<String,MathExpression> entry : exprs.entrySet()) {
            String key = entry.getKey();
            MathExpression value = entry.getValue();

            if (value != null)
            {
                if (value instanceof MathBinaryExpression ) {
                    MathBinaryExpression express = (MathBinaryExpression)value;
                    if (express.rightExpression instanceof MathValue && express.leftExpression instanceof MathValue)
                    {
                        countBinary++;
                    }
                }
            }
        }

        return countBinary;
    }



    static TreeMap<String, MathExpression> readIn() {

        File findFile = new File("HW7-TestInput2.txt");

        TreeMap<String, MathExpression> exprs = new TreeMap<String, MathExpression>();

        try {
            Scanner inFile = new Scanner(findFile);
            String romString = "";

            while (inFile.hasNext()) {
                romString = inFile.nextLine();
                StringTokenizer strTok = new StringTokenizer(romString);

                MathExpression tempExprs = makeExpression(strTok);

                if(tempExprs == null)
                {
                    exprs.put("( Invalid Expression )",null);
                }
                else
                {
                    exprs.put(tempExprs.toString(),tempExprs);
                }
            }

            inFile.close();

        } catch (FileNotFoundException e) {
            return exprs;
        }

        return exprs;

    }

    static MathExpression makeExpression(StringTokenizer strT)
    {
        MathExpression left;
        MathExpression right;

        while (strT.hasMoreTokens())
        {
            String nextT = strT.nextToken();

            if (nextT.length() == 1 && "+-/*".indexOf(nextT) >= 0)
            {
                left = makeExpression(strT);
                right = makeExpression(strT);

                if (left == null || right == null )
                {
                    return null;
                }

                String exSwitch = nextT;

                switch (exSwitch.charAt(0))
                {
                    case '+':
                        return new AddExpression(left,right);
                    case '-':
                        return new SubExpression(left,right);
                    case '*':
                        return new MultExpression(left,right);
                    case '/':
                        return new DivExpression(left,right);
                    default:
                        System.out.println("ERROR");

                }
            }
            else
            {
                return new MathValue(Double.parseDouble(nextT));
            }

        }
        return null;
    }

}

 class MyComp implements Comparator<String> {
    @Override
    public int compare(String leftInt, String rightInt) {
        return leftInt.compareTo(rightInt);
    }
}


/*
TestInput 1

==

( ( 78.0 - 901.0 ) x ( 234.0 + 56.0 ) ) = -238670.0
( 123.456 - 7.8 ) = 115.656
( 3.0 + 2.0 ) = 5.0
( 9.8 / ( 12.3 + 7.6 ) ) = 0.492462311557789
1928.3746 = 1928.3746

There is/are 2 simple binary expression(s)

==


TestInput 2

==

( ( 10.0 - 3.0 ) x ( 500.0 + 20.0 ) ) = 3640.0
( ( 55.0 x 6.0 ) - 77.0 ) = 253.0
( 33.0 / 444.0 ) = 0.07432432432432433
( 8888.0 + ( 99.0 / 33.0 ) ) = 8891.0
( Invalid Expression )
11111.0 = 11111.0

There is/are 1 simple binary expression(s)

==
 */
