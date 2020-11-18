import java.util.ArrayList;
import java.util.Collections;

public class PositiveTotal implements Visitor {
    private final String[] positiveWords = {"good", "great", "excellent"};
    private ArrayList<String> positiveMessages;

    public PositiveTotal() {
        positiveMessages = new ArrayList<>();
    }

    @Override
    public void visit(UserGroup visitor) {

    }

    /**
     * Add each newsFeed message into message
     *
     * @param visitor
     */
    @Override
    public void visit(User visitor) {
        positiveMessages = Collections.list(visitor.getFeed().elements());
    }

    /**
     * Calculate the percentage of positive words with comparison to POSITIVE_WORDS list
     *
     * @return Positive percentage of words
     */
    public float getTotal() {
        float count = 0f;
        int countWords = 0;
        for (int i = 0; i < positiveMessages.size(); i++) {
            /**
             * Check if any words from news feed match list of positive words
             */
            for (int j = 0; j < positiveWords.length; j++) {
                if (positiveMessages.get(i).toLowerCase().contains(positiveWords[j])) {
                    count++;
                }
            }
            // Get word count separated by white space
            countWords += positiveMessages.get(i).split("\\s+").length;
        }
        return (count / countWords) * 100;
    }
}
