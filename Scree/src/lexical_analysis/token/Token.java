package lexical_analysis.token;

/**
 * 词法单元Token
 */
public class Token
{
    /**
     * 一个表示换行的字符串(会得到“\n”这样的两个字符，而不是一个换行)
     */
    public static final String EOL = "\\n";

    /**
     * 程序结束标志
     */
    public static final Token EOF = new Token(-1);

    /**
     * Token所处位置的行号
     */
    private int lineNumber;


    protected Token(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    /**
     * 是否为标识符
     * @return
     */
    public boolean isIdentifier()
    {
        return false;
    }

    /**
     * 是否为关键字
     * @return
     */
    public boolean isKeyword()
    {
        return false;
    }

    /**
     * 是否为整数值
     * @return
     */
    public boolean isInteger()
    {
        return false;
    }

    /**
     * 是否为字符串
     * @return
     */
    public boolean isString()
    {
        return false;
    }

    /**
     * 是否为符号（包括标点符号、运算符）
     * @return
     */
    public boolean isSymbol()
    {
        return false;
    }

    /**
     * 获取词法单元字面量文本
     * @return
     */
    public String getText()
    {
        return "";
    }

}
