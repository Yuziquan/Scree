package lexical_analysis;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-21-18
 **/

public class StrToken extends Token
{
    private String stringLiteral;

    public StrToken(int lineNumber, String stringLiteral)
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
        return stringLiteral;
    }
}
