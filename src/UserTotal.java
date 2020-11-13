public class UserTotal implements Visitor {
	private int users;

	public UserTotal() {
		users = 0;
	}
	
	@Override
	public void visit(UserGroup visitor) {

	}

	@Override
	public void visit(User visitor) {
		users++;
	}
	
	/**
	 * 
	 * @return Total number of users in selected group
	 */
	public int getTotal() {
		return users;
	}

}
