package syntax_analysis.abstract_syntax_tree;

import java.util.ArrayList;

public class BinaryOperatorASTNode extends ASTBranchNode
{
    public BinaryOperatorASTNode(ArrayList<ASTNode> childNodeList)
    {
        super(childNodeList);
    }

    public ASTNode getLeftChildNode()
    {
        return childNodeList.get(0);
    }

    public ASTNode getRightChildNode()
    {
        return childNodeList.get(2);
    }

    /**
     * 获取该BinaryOperatorASTNode节点对应的运算符
     * （运算符节点为ASTLeafNode类型，且作为当前BinaryOperatorASTNode节点的第二个子节点的形式存在）
     *
     * @return 该BinaryOperatorASTNode节点对应的运算符
     */
    private String getOperator()
    {
        return ((ASTLeafNode)childNodeList.get(1)).getToken().getText();
    }

}
