package lexical_analysis.token;

/**
 * 符号词法单元（包括标点符号、运算符）
 */
public class SymbolToken extends Token
{
    private String symbol;

    public SymbolToken(int lineNumber, String symbol)
    {
        super(lineNumber);
        this.symbol = symbol;
    }

    @Override
    public boolean isSymbol()
    {
        return true;
    }

    @Override
    public String getText()
    {
        return "symbol:" + symbol;
    }
}
