package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by vamaraju on 9/16/15.
 */
public class HappyMood extends Mood {

    public HappyMood() {
        super();
    }

    public HappyMood(Date date) {
        super(date);
    }

    public String printout() {
        return "Happy!!";
    }

}
