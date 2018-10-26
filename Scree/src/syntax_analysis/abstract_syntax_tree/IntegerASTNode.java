package syntax_analysis.abstract_syntax_tree;

import lexical_analysis.token.Token;

public class IntegerASTNode extends ASTLeafNode
{
    public IntegerASTNode(Token token)
    {
        super(token);
    }

    public int getIntegerValue()
    {
        return Integer.parseInt(token.getText());
    }

}
