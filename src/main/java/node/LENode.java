package node;

/**
 * @author Rabo
 */
public class LENode extends OperationNode {

    public LENode(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Node simplify() {
        left = left.simplify();
        right = right.simplify();

        if (left instanceof IntNode && right instanceof IntNode) {
            Integer a = ((IntNode) left).getValue();
            Integer b = ((IntNode) right).getValue();
            return new BoolNode(a <= b);
        }

        if (left instanceof StringNode && right instanceof StringNode) {
            String a = ((StringNode) left).getValue();
            String b = ((StringNode) right).getValue();
            return new BoolNode(a.compareTo(b) <= 0);
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LENode)) return false;

        LENode leNode = (LENode) o;

        return left.equals(leNode.left) && right.equals(leNode.right);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
