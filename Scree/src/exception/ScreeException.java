package exception;

import syntax_analysis.abstract_syntax_tree.ASTNode;

public class ScreeException extends RuntimeException
{
    public ScreeException(String message)
    {
        super(message);
    }

    public ScreeException(String message, ASTNode asTree)
    {
        super(message + "" + asTree);
    }
}
