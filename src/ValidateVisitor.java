package info;

import data.TwitterTree;
import data.User;
import data.UserGroup;

import java.util.*;

public class ValidateVisitor implements Visitor {
    // check for spaces
    String s = " ";
    private boolean isValid = true;
    private Map<String, Boolean> visitedUsers;

    public ValidateVisitor() {
        visitedUsers = new HashMap<>();
    }

    /**
     * Mark if visitor already visited
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
     * Check for duplicates
     * @return
     */
    public boolean isValid() {
        if (visitedUsers.containsValue(false)) {
            return false;
        }
        return true;
    }
}
