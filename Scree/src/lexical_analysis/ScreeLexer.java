package lexical_analysis;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-20-07
 **/

import exception.ParseException;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreeLexer
{
    public static final String REGEX
            = "\\s*((//.*)|" +
            "([0-9]+)|" +
            "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")|" +
            "[a-zA-Z_][a-zA-Z0-9_]*|" +
            "==|<=|>=|&&|\\|\\||\\p{Punct})?";

    public static final Pattern REGEX_PATTERN = Pattern.compile(REGEX);

    public LineNumberReader reader;

    // 还有内容可以读取
    private boolean hasMore;

    private ArrayList<Token> tokensQueue = new ArrayList<>();


    private ScreeLexer(Reader reader)
    {
        this.reader = new LineNumberReader(reader);
        hasMore = true;
    }


    /**
     * 从每一行源代码中获取词法单元
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

        tokensQueue.add(new IdToken(lineNumber, Token.EOL));
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
                    tokensQueue.add(new NumToken(lineNumber, Integer.parseInt(matchedString)));
                }
                else if(matcher.group(4) != null)
                {
                    tokensQueue.add(new StrToken(lineNumber, toStringLiteral(matchedString)));
                }
                else
                {
                    tokensQueue.add(new IdToken(lineNumber, matchedString));
                }
            }
        }
    }


    /**
     * 将字符串转换为字符串常量
     * @param str
     * @return
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
     * 预读一个词法单元
     * @param position 词法单元的预读位置
     * @return
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
     * 读取一个词法单元
     * @return
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
}
