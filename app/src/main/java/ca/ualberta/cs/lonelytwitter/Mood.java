package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by vamaraju on 9/16/15.
 */
public abstract class Mood {
    private String mood;
    private Date date;

    public Mood() {
        this.setDate(new Date());
    }

    public Mood(Date date) {
        this.setDate(date);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract String printout();
}
