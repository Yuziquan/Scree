package lexical_analysis.token;

/**
 * 关键字词法单元
 */
public class KeywordToken extends Token
{
    private String keyword;

    public KeywordToken(int lineNumber, String keyword)
    {
        super(lineNumber);
        this.keyword = keyword;
    }

    @Override
    public boolean isKeyword()
    {
        return true;
    }

    @Override
    public String getText()
    {
        return "keyword:" +keyword;
    }
}
