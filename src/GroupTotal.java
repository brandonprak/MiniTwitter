public class GroupTotal implements Visitor {
    private int groups;

    public GroupTotal() {
        groups = 0;
    }

    @Override
    public void visit(UserGroup visitor) {
        groups++;
    }

    @Override
    public void visit(User visitor) {
    }

    /**
     * @return Total number of all groups
     */
    public int getTotal() {
        return groups;
    }
}
