package lexical_analysis;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-21-17
 **/

public class NumToken extends Token
{
    private int integerValue;

    public NumToken(int lineNumber, int integerValue)
    {
        super(lineNumber);
        this.integerValue = integerValue;
    }

    @Override
    public boolean isNumber()
    {
        return true;
    }

    @Override
    public int getNumber()
    {
        return integerValue;
    }

    @Override
    public String getText()
    {
        return Integer.toString(integerValue);
    }
}
