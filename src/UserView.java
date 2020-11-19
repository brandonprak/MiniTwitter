import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class UserView extends JFrame {
    private User user;
    private JButton followUser;
    private JButton postTweet;
    private JTextArea textAreaUser;
    private JTextArea textAreaTweet;
    private JScrollPane scrollPaneFollowing;
    private JScrollPane scrollPaneFeed;
    private JList<User> listFollowing;
    private JList<String> listFeed;
    private JPanel contentPane;

    /**
     * Initializes UI components for UserView
     */
    public UserView(User user) {
        setBounds(100, 100, 380, 600);
        contentPane = new JPanel(new GridLayout(3, 1));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        this.user = user;
        textAreaUser = new JTextArea();
        followUser = new JButton("Follow User");
        scrollPaneFollowing = new JScrollPane();
        textAreaTweet = new JTextArea();
        postTweet = new JButton("Post Tweet");
        listFollowing = new JList<>();
        printCreationTime();
        initComponents();
        initFollowUser();
        initPostTweet();
    }

    private void initPostTweet() {
        postTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postTweetActionPerformed(e);
            }
        });

    }

    private void printCreationTime() {
        Date date = new Date(user.printCreationTime());
        System.out.println("Creation Time: " + date);
    }

    protected void postTweetActionPerformed(ActionEvent e) {
        user.postTweet(textAreaTweet.getText());
        textAreaTweet.setText("");
        listFeed.setModel(user.getFeed());
    }

    private void initFollowUser() {
        followUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                followUserActionPerformed(e);
            }
        });
    }

    protected void followUserActionPerformed(ActionEvent e) {
        user.addObserver(new User(textAreaUser.getText()));
        textAreaUser.setText("");
    }

    /**
     * Initializes Java Swing components for UserView
     */
    public void initComponents() {
        JLabel lblCurrentlyFollowing = new JLabel("Currently Following");
        JLabel lblTweetMessage = new JLabel("Tweet Message");
        JLabel lblNewsFeed = new JLabel("News Feed");
        DefaultListModel<String> userNewsFeed = user.getFeed();

        /**
         * Retrieves newsFeed if it exists, otherwise creates a new one
         */
        if (userNewsFeed != null) {
            listFeed = new JList(userNewsFeed);
        } else {
            listFeed = new JList<>();
        }
        scrollPaneFeed = new JScrollPane(listFeed);

        /**
         * UI layout and alignment
         */
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblNewsFeed)
                                                .addContainerGap())
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(lblTweetMessage)
                                                        .addContainerGap())
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblCurrentlyFollowing)
                                                                .addContainerGap())
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(scrollPaneFeed, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                                        .addContainerGap())
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(textAreaTweet, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(postTweet)
                                                                                .addContainerGap())
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(scrollPaneFollowing, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addComponent(textAreaUser, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                                .addComponent(followUser, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
                                                                                .addContainerGap(10, Short.MAX_VALUE))))))))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textAreaUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(followUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(10)
                                .addComponent(lblCurrentlyFollowing)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPaneFollowing, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(lblTweetMessage)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(textAreaTweet)
                                                .addGap(10)
                                                .addComponent(lblNewsFeed)
                                                .addGap(10))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(postTweet, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                .addComponent(scrollPaneFeed, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
        );
        scrollPaneFeed.setViewportView(listFeed);
        scrollPaneFollowing.setViewportView(listFollowing);
        getContentPane().setLayout(groupLayout);
    }
}
