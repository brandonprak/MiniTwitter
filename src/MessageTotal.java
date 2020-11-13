import java.util.*;

public class MessageTotal implements Visitor {
	private List<String> message;
	
	public MessageTotal() {
		message = new ArrayList<>();
	}
	
	@Override
	public void visit(UserGroup visitor) {

	}

	@Override
	public void visit(User visitor) {
		for (int i = 0; i < visitor.getFeed().size(); i++) {
			message.add(visitor.getFeed().get(i));
		}
		
	}
	
	/**
	 * @return Total number of messages
	 */
	public int getTotal() {
		return message.size();
	}

}
