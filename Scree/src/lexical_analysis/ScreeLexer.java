package lexical_analysis;

import constants.Constants;
import exception.ParseException;
import lexical_analysis.token.*;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法分析器
 */
public class ScreeLexer
{
    public static final String REGEX
            = "\\s*((//.*)|" + "(" + Constants.IDENTIFIER_REGEX + ")|"
            + "(" + Constants.INTEGER_REGEX + ")|"+ "(" + Constants.STRING_REGEX + ")|"
            + Constants.SYMBOL_REGEX + ")?";

    private Pattern REGEX_PATTERN = Pattern.compile(REGEX);

    private LineNumberReader reader;

    /**
     * 是否还有内容可以读取
     */
    private boolean hasMore;

    /**
     * 已经加载的词法单元队列
     */
    private ArrayList<Token> tokensQueue = new ArrayList<>();


    public ScreeLexer(Reader reader)
    {
        this.reader = new LineNumberReader(reader);
        hasMore = true;
    }


    /**
     * 读取一个词法单元，并将其从词法单元队列中移除
     * @return 读取的词法单元
     */
    public Token readAToken() throws ParseException
    {
        if(loadTokensIntoQueue(0))
        {
            return tokensQueue.remove(0);
        }
        else
        {
            return Token.EOF;
        }
    }


    /**
     * 预读一个词法单元，不将其从词法单元队列中移除
     * @param position 词法单元的预读位置
     * @return 预读取的词法单元
     * @throws ParseException
     */
    public Token peekAToken(int position) throws ParseException
    {
        if(loadTokensIntoQueue(position))
        {
            return tokensQueue.get(position);
        }
        else
        {
            return Token.EOF;
        }
    }


    /**
     *  加载更多的词法单元至词法单元队列中
     * @param position
     * @return
     */
    public boolean loadTokensIntoQueue(int position) throws ParseException
    {
        while(position >= tokensQueue.size())
        {
            if(hasMore)
            {
                readLine();
            }
            else
            {
                return false;
            }
        }

        return true;
    }


    /**
     * 读取一行源代码，从中解析并获取词法单元
     * @throws ParseException
     */
    private void readLine() throws ParseException
    {
        String line;

        try
        {
            line = reader.readLine();
        }
        catch (IOException e)
        {
            throw new ParseException(e);
        }

        if(line == null)
        {
            hasMore = false;
            return;
        }

        int lineNumber = reader.getLineNumber();

        Matcher matcher = REGEX_PATTERN.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);

        int curPosition = 0;
        int endPosition = line.length();

        while (curPosition < endPosition)
        {
            matcher.region(curPosition, endPosition);

            if(matcher.lookingAt())
            {
                addTokenToQueue(lineNumber, matcher);
                curPosition = matcher.end();
            }
            else
            {
                throw new ParseException("Bad token at line " + lineNumber + ".");
            }
        }

        tokensQueue.add(new KeywordToken(lineNumber, Token.EOL));
    }


    /**
     * 将词法单元添加入词法单元队列
     * @param lineNumber
     * @param matcher
     */
    private void addTokenToQueue(int lineNumber, Matcher matcher)
    {
        String matchedString = matcher.group(1);

        // 不是一个空行
        if(matchedString != null)
        {
            // 不是注释
            if(matcher.group(2) == null)
            {
                if(matcher.group(3) != null)
                {
                    // 如果是关键字
                    if(Constants.KEYWORDS.contains(matchedString))
                    {
                        tokensQueue.add(new KeywordToken(lineNumber, matchedString));
                    }
                    // 如果是标识符
                    else
                    {
                        tokensQueue.add(new IdentifierToken(lineNumber, matchedString));
                    }
                }
                else if(matcher.group(4) != null)
                {
                    tokensQueue.add(new IntegerToken(lineNumber, Integer.parseInt(matchedString)));
                }
                else if(matcher.group(5) != null)
                {
                    tokensQueue.add(new StringToken(lineNumber, toStringLiteral(matchedString)));
                }
                else
                {
                    tokensQueue.add(new SymbolToken(lineNumber, matchedString));
                }
            }
        }
    }


    /**
     * 将字符串转换为字符串常量，如：
     * 1、"This is a \"Zhou\". " -> This is a "Zhou".
     * 2、"Hello \\Bob." -> Hello \Bob.
     * 3、"Bye!\n" -> Bye!换行
     * @param str 待转化的字符串
     * @return 转换得到的字符串常量
     */
    private String toStringLiteral(String str)
    {
        StringBuilder sb = new StringBuilder();

        int length = str.length();

        for(int i = 0; i < length; i++)
        {
            char c1 = str.charAt(i);

            if(c1 == '\\' && i + 1 < length)
            {
                char c2 = str.charAt(i + 1);

                if(c2 == '"' || c2 == '\\')
                {
                    c1 = str.charAt(++i);
                }
                else if(c2 == 'n')
                {
                    c1 = '\n';
                    ++i;
                }
            }

            sb.append(c1);
        }

        return sb.toString();
    }



}
