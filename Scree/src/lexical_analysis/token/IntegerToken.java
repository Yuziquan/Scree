package lexical_analysis.token;

/**
 * 整数值词法单元
 */
public class IntegerToken extends Token
{
    private int integerValue;

    public IntegerToken(int lineNumber, int integerValue)
    {
        super(lineNumber);
        this.integerValue = integerValue;
    }

    @Override
    public boolean isInteger()
    {
        return true;
    }

    @Override
    public String getText()
    {
        return Integer.toString(integerValue);
    }

    public int getIntegerValue()
    {
        return integerValue;
    }
}
