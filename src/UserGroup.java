import javax.swing.tree.TreeNode;

import java.util.*;

/**
 * Component class of TwitterTree
 * @author johnnylu
 *
 */
public class UserGroup implements TwitterTree {

	private String uniqueID;
	private List<TwitterTree> children;
	private long creationTime;
	
	public UserGroup(String groupID) {
		uniqueID = groupID;
		children = new ArrayList<>();
		creationTime = System.currentTimeMillis();
	}

	public String toString() {
		return uniqueID;
	}

	@Override
	public User getUser(String user) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getUser(user) != null) {
				return (User) children.get(i);
			}
		}
		return null;
	}

	@Override
	public long printCreationTime() {
		return creationTime;
	}

	public String getID() {
		return uniqueID;
	}

	public void add(TwitterTree child) {
		children.add(child);
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return null;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		for (TwitterTree tree: children) {
			tree.accept(visitor);
		}
	}

	@Override
	public boolean contains(Visitor visitor) {
		if (visitor instanceof UserGroup) {
			return this.toString().equals(visitor.toString());
		}
		return false;
	}

}
