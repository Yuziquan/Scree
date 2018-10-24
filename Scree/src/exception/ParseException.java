package exception;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-21-03
 **/

import lexical_analysis.Token;

import java.io.IOException;

public class ParseException extends Exception
{
    public ParseException(Token token)
    {
        this("", token);
    }

    public ParseException(String message, Token token)
    {
        super("Syntax error around " + getLocation(token) + ". " + message);
    }


    public ParseException(IOException e)
    {
        super(e);
    }

    public ParseException(String message)
    {
        super(message);
    }


    private static String getLocation(Token token)
    {
        if(token == Token.EOF)
        {
            return "the last line";
        }
        else
        {
            return "\"" + token.getText() + "\" at line " + token.getLineNumber();
        }
    }
}
