package lexical_analysis;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-19-43
 **/

import exception.ScreeException;

/**
 * 词法单元Token
 */
public class Token
{
    // 一个表示换行的字符串(会得到“\n”这样的两个字符，而不是一个换行)
    public static final String EOL = "\\n";

    // 程序结束标志
    public static final Token EOF = new Token(-1);

    // Token所处位置的行号
    private int lineNumber;



    protected Token(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    // 是否为标识符
    public boolean isIdentifier()
    {
        return false;
    }

    // 是否为整型字面量
    public boolean isNumber()
    {
        return false;
    }

    // 是否为字符串字面量
    public boolean isString()
    {
        return false;
    }

    public int getNumber()
    {
        throw new ScreeException("Not a number token!");
    }

    // 词法单元字面量文本
    public String getText()
    {
        return "";
    }


    public static void main(String[] args)
    {
        System.out.println("Test" + '\"' + " test");
    }

}
