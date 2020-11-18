import javax.swing.tree.TreeNode;

/**
 * Composite class to manage User and UserGroup
 */
public interface TwitterTree extends TreeNode {
    User getUser(String user);

    long printCreationTime();

    /**
     * Accept visitor object
     *
     * @param visitor User or UserGroup
     */
    void accept(Visitor visitor);

    boolean contains(Visitor visitor);
}
