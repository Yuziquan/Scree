package exception;

/*
 * @program: Scree
 * @description:
 * @author: WuchangI
 * @create: 2018-10-24-19-57
 **/

import syntax_analysis.ASTree;

public class ScreeException extends RuntimeException
{
    public ScreeException(String message)
    {
        super(message);
    }

    public ScreeException(String message, ASTree asTree)
    {
        super(message + "" + asTree);
    }
}
