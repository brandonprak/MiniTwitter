import java.util.*;

public class ValidateVisitor implements Visitor {
    String s = " ";
    private boolean isValid = true;
    private Map<String, Boolean> visitedUsers;

    public ValidateVisitor() {
        visitedUsers = new HashMap<>();
    }

    /**
     * Marks if a visitor has already been visited
     * @param visitor Visiting User or UserGroup
     */
    @Override
    public void visit(UserGroup visitor) {
        if (visitor.toString().contains(s)) {
            isValid = false;
        }
        visitedUsers.put(visitor.toString(), isValid);
    }

    @Override
    public void visit(User visitor) {
        if (visitor.toString().contains(s) || visitedUsers.containsKey(visitor.toString())) {
            isValid = false;
        }
        visitedUsers.put(visitor.toString(), isValid);
    }

    /**
     * Checks for duplicates IDs
     * @return
     */
    public boolean isValid() {
        if (visitedUsers.containsValue(false)) {
            return false;
        }
        return true;
    }
}
