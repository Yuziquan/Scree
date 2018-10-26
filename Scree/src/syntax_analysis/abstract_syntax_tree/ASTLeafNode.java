package syntax_analysis.abstract_syntax_tree;

import lexical_analysis.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 抽象语法树的叶节点
 */
public class ASTLeafNode extends ASTNode
{
    private ArrayList<ASTNode> emptyChildNodeList = new ArrayList<ASTNode>();
    protected Token token;

    public ASTLeafNode(Token token)
    {
        this.token = token;
    }

    @Override
    public ASTNode getChildNode(int i)
    {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int getNumOfChildNodes()
    {
        return 0;
    }

    @Override
    public Iterator<ASTNode> getChildNodesIterator()
    {
        return emptyChildNodeList.iterator();
    }

    @Override
    public String getLocation()
    {
        return "at line " + token.getLineNumber();
    }

    @Override
    public String toString()
    {
        return token.getText();
    }

    public Token getToken()
    {
        return token;
    }
}
