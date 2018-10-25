package lexical_analysis.test;


import exception.ParseException;
import lexical_analysis.ScreeLexer;
import lexical_analysis.token.Token;

public class ScreeLexerRunner
{
    public static void main(String[] args) throws ParseException
    {
        ScreeLexer screeLexer = new ScreeLexer(new CodeDialog());

        for(Token t; (t = screeLexer.readAToken()) != Token.EOF; )
        {
            System.out.println("=> " + t.getText());
        }
    }
}
