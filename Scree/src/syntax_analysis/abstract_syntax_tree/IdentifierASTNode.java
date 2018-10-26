package syntax_analysis.abstract_syntax_tree;

import lexical_analysis.token.Token;


public class IdentifierASTNode extends ASTLeafNode
{
    public IdentifierASTNode(Token token)
    {
        super(token);
    }

    public String getIdentifier()
    {
        return token.getText();
    }

}
