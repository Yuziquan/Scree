package lexical_analysis.token;

/**
 * 字符串词法单元
 */
public class StringToken extends Token
{
    private String stringLiteral;

    public StringToken(int lineNumber, String stringLiteral)
    {
        super(lineNumber);
        this.stringLiteral = stringLiteral;
    }


    @Override
    public boolean isString()
    {
        return true;
    }

    @Override
    public String getText()
    {
        return "string:" + stringLiteral;
    }
}
