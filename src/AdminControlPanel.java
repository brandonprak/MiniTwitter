import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Main UI view
 */
public class AdminControlPanel extends JFrame {
    private static AdminControlPanel INSTANCE;
    private JPanel contentPane;
    private JTextArea userID;
    private JTextArea groupID;
    private JButton addUser;
    private JButton addGroup;
    private JButton openUserView;
    private JButton showUserTotal;
    private JButton showGroupTotal;
    private JButton showMessageTotal;
    private JButton showPositivePercentage;
    private JButton validateID;
    private JButton lastUpdatedUser;
    private JScrollPane scrollPane;
    private JTree tree;
    private UserGroup root;
    private DefaultTreeModel defaultTree;
    private Set<User> users;
    private Set<UserGroup> groups;

    /**
     * Creates the frame
     */
    public AdminControlPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 410);
        contentPane = new JPanel(new GridLayout(1, 2));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        users = new HashSet<>();
        groups = new HashSet<>();
        initComponents();
    }

    /**
     * Initializes singleton
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

    /**
     * Initializes Java Swing components for UI
     */
    public void initComponents() {
        JFrame frame = new JFrame("Admin Control Panel");
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

        validateID = new JButton("Validate IDs");
        validateID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { validateIDActionPerformed(e); }
        });

        /**
         * UI layout and alignment
         */
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup()
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                        .createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(showUserTotal, GroupLayout.PREFERRED_SIZE, 200,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(showMessageTotal, GroupLayout.PREFERRED_SIZE, 200,
                                        GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(showPositivePercentage, GroupLayout.PREFERRED_SIZE, 200,
                                        Short.MAX_VALUE)
                                .addComponent(showGroupTotal, GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)))
                        .addComponent(openUserView, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup().addGroup(
                                gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(groupID)
                                        .addComponent(userID, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(addGroup, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(addUser, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(addUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(addGroup)
                        .addComponent(groupID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(20).addComponent(openUserView).addGap(165)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(showUserTotal)
                        .addComponent(showGroupTotal, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(showMessageTotal)
                        .addComponent(showPositivePercentage, GroupLayout.PREFERRED_SIZE, 26,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(gl_contentPane.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)))
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE).addContainerGap()));
        scrollPane.setColumnHeaderView(tree);
        contentPane.setLayout(gl_contentPane);
    }

    /**
     * Provides functionality for buttons
     * @param e
     */
    protected void addUserButtonActionPerformed(ActionEvent e) {
        TreeNode selected = (TreeNode) tree.getLastSelectedPathComponent();
        if (userID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a user.");
        } else if (users.contains(userID.getText())) {
            JOptionPane.showMessageDialog(null, "User already exists.");
        } else if (selected == null) {
            JOptionPane.showMessageDialog(null, "Please select a group to add this user to.");
        } else {
            if (selected instanceof UserGroup) {
                User newUser = new User(userID.getText());
                users.add(newUser);
                ((UserGroup) selected).add(newUser);
                updateTree();
                userID.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a group to add user to.");
            }
        }
    }

    protected void addGroupButtonActionPerformed(ActionEvent e) {
        if (groupID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a group.");
        } else if (users.contains(groupID.getText())) {
            JOptionPane.showMessageDialog(null, "Group already exists.");
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
            JOptionPane.showMessageDialog(null, "Please select a user.");
        } else if (selected instanceof UserGroup) {
            JOptionPane.showMessageDialog(null, "Please select a user, not a group.");
        } else {
            new UserView((User) selected).setVisible(true);
        }
    }

    protected void showUserTotalActionPerformed(ActionEvent e) {
        TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
        if (current != null) {
            Visitor visitor = new UserTotal();
            ((TwitterTree) current).accept(visitor);
            JOptionPane.showMessageDialog(null, ((UserTotal) visitor).getTotal());
        } else {
            JOptionPane.showMessageDialog(null, "There are no users.");
        }
    }

    protected void showGroupTotalActionPerformed(ActionEvent e) {
        TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
        if (current != null) {
            Visitor visitor = new GroupTotal();
            ((TwitterTree) current).accept(visitor);
            JOptionPane.showMessageDialog(null, ((GroupTotal) visitor).getTotal());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a group.");
        }
    }

    protected void showMessageTotalActionPerformed(ActionEvent e) {
        TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
        if (current != null) {
            Visitor visitor = new MessageTotal();
            ((TwitterTree) current).accept(visitor);
            JOptionPane.showMessageDialog(null, ((MessageTotal) visitor).getTotal());
        } else {
            JOptionPane.showMessageDialog(null, "There are no messages for this user.");
        }
    }

    protected void showPercentageTotalActionPerformed(ActionEvent e) {
        TreeNode current = (TreeNode) tree.getLastSelectedPathComponent();
        if (current != null) {
            Visitor visitor = new PositiveTotal();
            ((TwitterTree) current).accept(visitor);
            JOptionPane.showMessageDialog(null, ((PositiveTotal) visitor).getTotal());
        } else {
            JOptionPane.showMessageDialog(null, "There are no percentages available for this user.");
        }
    }

    /**
     * Allows checking for duplicate user/group IDs
     * @param e
     */
    protected void validateIDActionPerformed(ActionEvent e) {
        ValidateVisitor validVisitor = new ValidateVisitor();
        root.accept(validVisitor);
        if (validVisitor.isValid()) {
            JOptionPane.showMessageDialog(null, "There are no duplicate IDs.");
        } else {
            JOptionPane.showMessageDialog(null, "There are no duplicate IDs or IDs with spaces.");
        }
    }

    /**
     * Refreshes the tree and expands nodes
     */
    private void updateTree() {
        defaultTree.reload(root);
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
    }
}
