package lexical_analysis.token;

/**
 * 标识符词法单元
 */
public class IdentifierToken extends Token
{
    private String identifier;

    public IdentifierToken(int lineNumber, String identifier)
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
        return "identifier:" + identifier;
    }
}
