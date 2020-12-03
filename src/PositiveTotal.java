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

    @Override
    public void visit(User visitor) {
        positiveMessages = Collections.list(visitor.getFeed().elements());
    }

    /**
     * Calculates the percentage of positive words with comparison to POSITIVE_WORDS list
     * @return Positive percentage of words
     */
    public float getTotal() {
        float count = 0f;
        int countWords = 0;
        for (int i = 0; i < positiveMessages.size(); i++) {
            /**
             * Checks if any words from newsFeed match list of positive words
             */
            for (int j = 0; j < positiveWords.length; j++) {
                if (positiveMessages.get(i).toLowerCase().contains(positiveWords[j])) {
                    count++;
                }
            }
            countWords += positiveMessages.get(i).split("\\s+").length;
        }
        return (count / countWords) * 100;
    }
}
