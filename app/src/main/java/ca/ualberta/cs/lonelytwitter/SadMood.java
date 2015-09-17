package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by vamaraju on 9/16/15.
 */
public class SadMood extends Mood {

    public SadMood() {
        super();
    }

    public SadMood(Date date) {
        super(date);
    }

    public String printout() {
        return "Sad :(";
    }

}
