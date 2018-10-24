package lexical_analysis;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-21-18
 **/

public class IdToken extends Token
{
    private String identifier;

    public IdToken(int lineNumber, String identifier)
    {
        super(lineNumber);
        this.identifier = identifier;
    }

    @Override
    public boolean isIdentifier()
    {
        return true;
    }

    @Override
    public String getText()
    {
        return identifier;
    }
}
