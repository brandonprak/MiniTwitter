import java.util.*;
import javax.swing.*;
import javax.swing.tree.TreeNode;

/**
 * Component class for TwitterTree
 */
public class User extends Observable implements Observer, TwitterTree {
	private String uniqueID;
	private Set<User> followers;
	private Set<User> following;
	private DefaultListModel<String> newsFeed;
	private long creationTime;
	private long lastUpdateTime;
	
	public User(String userID) {
		uniqueID = userID;
		followers = new HashSet<>();
		following = new HashSet<>();
		newsFeed = new DefaultListModel<>();
		creationTime = System.currentTimeMillis();
		lastUpdateTime = 0;
	}
	
	/**
	 * Implements observer pattern
	 * @param observer
	 */
	public void addObserver(User observer) {
		super.addObserver(observer);
		followers.add(observer);
		observer.following.add(this);
	}

	/** 
	 * Reaches out to observer objects and notifies state change
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("Update called.");
			newsFeed.add(0, (String) arg);
		}
	}

	@Override
	public User getUser(String user) {
		if (uniqueID.equals(user)) {
			return this;
		}
		return null;
	}

	/**
	 * Returns the time when a user is created
	 * @return
	 */
	@Override
	public long printCreationTime() {
		return creationTime;
	}

	/**
	 * Adds tweets to newsFeed and prints the last updated time to console
	 * @param tweet
	 */
	public void postTweet(String tweet) {
		lastUpdateTime = System.currentTimeMillis();
		Date updateTime = new Date(lastUpdateTime);
		System.out.println(updateTime + ": " + tweet);
		newsFeed.addElement(tweet);
		setChanged();
		notifyObservers(tweet);
		System.out.println("Observers notified.");
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public String toString() {
		return uniqueID;
	}

	public DefaultListModel<String> getFeed() {
		return newsFeed;
	}
	
	public Set<User> getFollowing() {
		return following;
	}
	
	public Set<User> getFollowers() {
		return followers;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return this;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return null;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean contains(Visitor visitor) {
		if (visitor instanceof User) {
			return this.toString().equals(visitor.toString());
		}
		return false;
	}
}
