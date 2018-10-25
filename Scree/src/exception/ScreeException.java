package exception;

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
