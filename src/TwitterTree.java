import javax.swing.tree.TreeNode;

/**
 * Composite class that manages User and UserGroup
 */
public interface TwitterTree extends TreeNode {
    User getUser(String user);
    long printCreationTime();

    /**
     * Accepts visitor object
     * @param visitor User or UserGroup
     */
    void accept(Visitor visitor);

    boolean contains(Visitor visitor);
}
