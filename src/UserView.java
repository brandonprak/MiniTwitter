import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
	 * Initialize UI components for UserView
	 */
	public UserView(User user) {
		setBounds(100, 100, 400, 600);
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
	 * Initialize Java Swing components for UserView
	 */
	public void initComponents() {
		JLabel lblCurrentlyFollowing = new JLabel("Currently Following");
		JLabel lblTweetMessage = new JLabel("Tweet Message");
		JLabel lblNewsFeed = new JLabel("News Feed");
		DefaultListModel<String> userNewsFeed = user.getFeed();

		/**
		 * Retrieve newsFeed if exists, otherwise create new
		 */
		if (userNewsFeed != null) {
			listFeed = new JList(userNewsFeed);
		} else {
			listFeed = new JList<>();
		}
		scrollPaneFeed = new JScrollPane(listFeed);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
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
										.addComponent(scrollPaneFeed, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textAreaTweet, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(postTweet)
											.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPaneFollowing, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(textAreaUser, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(followUser, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
											.addContainerGap(17, Short.MAX_VALUE))))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textAreaUser, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(followUser))
					.addGap(5)
					.addComponent(lblCurrentlyFollowing)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneFollowing, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblTweetMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textAreaTweet)
							.addGap(8)
							.addComponent(lblNewsFeed)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(postTweet)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(scrollPaneFeed, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		scrollPaneFeed.setViewportView(listFeed);
		scrollPaneFollowing.setViewportView(listFollowing);
		
		getContentPane().setLayout(groupLayout);
	}
}
