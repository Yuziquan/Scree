package syntax_analysis.abstract_syntax_tree;

import java.util.Iterator;

/**
 * 抽象语法树的抽象节点类
 */
public abstract class ASTNode
{
    public abstract ASTNode getChildNode(int i);
    public abstract int getNumOfChildNodes();
    public abstract Iterator<ASTNode> getChildNodesIterator();
    public abstract String getLocation();
}
