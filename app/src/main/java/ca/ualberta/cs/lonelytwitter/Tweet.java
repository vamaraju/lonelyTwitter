package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by vamaraju on 9/16/15.
 */
public abstract class Tweet implements Tweetable {
    private String text;
    protected Date date; // allows for any subclass of Tweet to edit/use/see this attribute
    private ArrayList<Mood> moods;

    public Tweet(String tweet, Date date) {
        this.setText(tweet);
        this.setDate(date);
    }

    public Tweet(String tweet) {
        this.text = tweet;
        this.date = new Date(); // the current date
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        if (text.length() <= 140) {
            this.text = text;
        }
        else {
            throw new IllegalArgumentException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract Boolean isImportant();

    public void addMood(Mood mood) {
        this.moods.add(mood);
    }
}

