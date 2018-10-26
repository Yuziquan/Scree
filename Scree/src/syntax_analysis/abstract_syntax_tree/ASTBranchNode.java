package syntax_analysis.abstract_syntax_tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 抽象语法树的非叶节点（带有子分支的节点）
 */
public class ASTBranchNode extends ASTNode
{
    /**
     * 子节点的集合
     */
    protected ArrayList<ASTNode> childNodeList;

    public ASTBranchNode(ArrayList<ASTNode> childNodeList)
    {
        this.childNodeList = childNodeList;
    }


    @Override
    public ASTNode getChildNode(int i)
    {
        return childNodeList.get(i);
    }

    @Override
    public int getNumOfChildNodes()
    {
        return childNodeList.size();
    }

    @Override
    public Iterator<ASTNode> getChildNodesIterator()
    {
        return childNodeList.iterator();
    }

    @Override
    public String getLocation()
    {
        for(ASTNode node : childNodeList)
        {
            String location = node.getLocation();
            if(location != null)
            {
                return location;
            }
        }

        return null;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String separator = " ";

        boolean isFirst = true;

        for(ASTNode node : childNodeList)
        {
            if(isFirst)
            {
                isFirst = false;
            }
            else
            {
                sb.append(separator);
            }

            sb.append(node.toString());
        }

        sb.append(")");

        return sb.toString();
    }
}
