public interface Visitor {
    /**
     * Allows for visitor objects
     * @param visitor Visiting User or UserGroup
     */
    public void visit(UserGroup visitor);

    public void visit(User visitor);
}
