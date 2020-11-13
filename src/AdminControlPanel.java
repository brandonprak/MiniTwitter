import java.awt.event.*;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.*;

/**
 * Main UI view
 */
public class AdminControlPanel extends JFrame {
	private JPanel contentPane;
	private static AdminControlPanel INSTANCE;
	private JTextArea userID;
	private JTextArea groupID;
	private JButton addUser;
	private JButton addGroup;
	private JButton openUserView;
	private JButton showUserTotal;
	private JButton showGroupTotal;
	private JButton showMessageTotal;
	private JButton showPositivePercentage;
	private JScrollPane scrollPane;
	private JTree tree;
	private UserGroup root;
	private DefaultTreeModel defaultTree;
	private Set<User> users;
	private Set<UserGroup> groups;

	/**
	 * Create the frame.
	 */
	public AdminControlPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 650);
		contentPane = new JPanel(new GridLayout(1, 2));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		users = new HashSet<>();
		groups = new HashSet<>();
		initComponents();
	}

	/**
	 * Initialize Java Swing components in UI
	 */
	public void initComponents() {
		JFrame frame = new JFrame("Control Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		root = new UserGroup("root");
		defaultTree = new DefaultTreeModel(root);
		tree = new JTree(defaultTree);
		tree.setRootVisible(true);
		updateTree();

		scrollPane = new JScrollPane(tree);
		groupID = new JTextArea();
		userID = new JTextArea();
		addUser = new JButton("Add User");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserButtonActionPerformed(e);
			}
		});

		addGroup = new JButton("Add Group");
		addGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGroupButtonActionPerformed(e);
			}
		});

		openUserView = new JButton("Open User View");
		openUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUserViewActionPerformed(e);
			}
		});

		showUserTotal = new JButton("Show User Total");
		showUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUserTotalActionPerformed(e);
			}
		});

		showGroupTotal = new JButton("Show Group Total");
		showGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGroupTotalActionPerformed(e);
			}
		});

		showMessageTotal = new JButton("Show Message Total");
		showMessageTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMessageTotalActionPerformed(e);
			}
		});

		showPositivePercentage = new JButton("Show Positive Percentage");
		showPositivePercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPercentageTotalActionPerformed(e);
			}
		});

		// Align JPanel window
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(showUserTotal)
								.addComponent(showMessageTotal, GroupLayout.PREFERRED_SIZE, 155,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(showPositivePercentage, GroupLayout.PREFERRED_SIZE, 200,
										Short.MAX_VALUE)
								.addComponent(showGroupTotal, GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)))
						.addComponent(openUserView, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(
								gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(groupID)
										.addComponent(userID, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(addGroup, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(addUser, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))
				.addContainerGap()));

		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(20)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addUser, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(userID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(addGroup)
						.addComponent(groupID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(openUserView).addGap(365)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(showUserTotal)
						.addComponent(showGroupTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(showMessageTotal)
						.addComponent(showPositivePercentage, GroupLayout.PREFERRED_SIZE, 29,
								GroupLayout.PREFERRED_SIZE))
				.addGap(50)
				.addGroup(gl_contentPane.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
				.addGap(32))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE).addContainerGap()));

		scrollPane.setColumnHeaderView(tree);
		contentPane.setLayout(gl_contentPane);
	}

	protected void showPercentageTotalActionPerformed(ActionEvent e) {
		TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
		if (current != null) {
			Visitor visitor = new PositiveTotal();
			((TwitterTree) current).accept(visitor);
			JOptionPane.showMessageDialog(null, ((PositiveTotal) visitor).getTotal());
		} else {
			JOptionPane.showMessageDialog(null, "There are no percentages available for this user");
		}
	}

	protected void showMessageTotalActionPerformed(ActionEvent e) {
		TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
		if (current != null) {
			Visitor visitor = new MessageTotal();
			((TwitterTree) current).accept(visitor);
			JOptionPane.showMessageDialog(null, ((MessageTotal) visitor).getTotal());
		} else {
			JOptionPane.showMessageDialog(null, "There are no messages for this user");
		}
	}

	protected void showGroupTotalActionPerformed(ActionEvent e) {
		TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
		if (current != null) {
			Visitor visitor = new GroupTotal();
			((TwitterTree) current).accept(visitor);
			JOptionPane.showMessageDialog(null, ((GroupTotal) visitor).getTotal());
		} else {
			JOptionPane.showMessageDialog(null, "Please select a group");
		}
	}

	protected void showUserTotalActionPerformed(ActionEvent e) {
		TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
		if (current != null) {
			Visitor visitor = new UserTotal();
			((TwitterTree) current).accept(visitor);
			JOptionPane.showMessageDialog(null, ((UserTotal) visitor).getTotal());
		} else {
			JOptionPane.showMessageDialog(null, "There are no users");
		}

	}

	/**
	 * Initialize Singleton
	 * @return
	 */
	public static AdminControlPanel getInstance() {
		if (INSTANCE == null) {
			synchronized (AdminControlPanel.class) {
				if (INSTANCE == null) {
					try {
						INSTANCE = new AdminControlPanel();
						INSTANCE.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return INSTANCE;
	}

	protected void addUserButtonActionPerformed(ActionEvent e) {
		TreeNode selected = (TreeNode) tree.getLastSelectedPathComponent();
		if (userID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a user to add");
		} else if (users.contains(userID.getText())) {
			JOptionPane.showMessageDialog(null, "User ID already exists");
		} else if (selected == null) {
			JOptionPane.showMessageDialog(null, "Please select a group to add user to");
		} else {
			if (selected instanceof UserGroup) {
				User newUser = new User(userID.getText());
				users.add(newUser);
				((UserGroup) selected).add(newUser);
				updateTree();
				userID.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Please select a group to add user to");
			}
		}
	}

	protected void addGroupButtonActionPerformed(ActionEvent e) {
		if (groupID.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter a group to add");
		} else if (users.contains(groupID.getText())) {
			JOptionPane.showMessageDialog(null, "Group ID already exists");
		} else {
			TreeNode selected = (TreeNode) tree.getLastSelectedPathComponent();
			if (selected == null) {
				root.add(new UserGroup(groupID.getText()));
			} else {
				UserGroup newUserGroup = new UserGroup(groupID.getText());
				groups.add(newUserGroup);
				((UserGroup) selected).add(newUserGroup);
				updateTree();
				groupID.setText("");
			}
		}
	}

	protected void openUserViewActionPerformed(ActionEvent e) {
		TreeNode selected = (TreeNode) tree.getLastSelectedPathComponent();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Please select a user");
		} else if (selected instanceof UserGroup) {
			JOptionPane.showMessageDialog(null, "Please select a user, not a group");
		} else {
			new UserView((User) selected).setVisible(true);
		}
	}

	/**
	 * Refresh tree and expand nodes
	 */
	private void updateTree() {
		defaultTree.reload(root);
		for (int i = 0; i < tree.getRowCount(); i++)
			tree.expandRow(i);
	}
}