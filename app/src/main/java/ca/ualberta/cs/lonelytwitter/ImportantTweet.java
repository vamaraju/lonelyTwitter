package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by vamaraju on 9/16/15.
 */
public class ImportantTweet extends Tweet {
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date);
        this.setText(tweet.toUpperCase());
        this.date = date;
    }

    public ImportantTweet(String tweet) {
        super(tweet);
    }

    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    @Override
    public String getText() {
        // the method will override with or without the annotation IF IT IS SPELLED CORRECTLY and the arguments match. the @override merely indicates this directly (and ensures that the method signature matches).
        return "!!!" + super.getText();
    }
}
