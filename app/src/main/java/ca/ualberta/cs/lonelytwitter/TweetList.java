package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vamaraju on 9/30/15.
 */
public class TweetList {

    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public TweetList() {

    }

    public TweetList(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void addTweet(Tweet tweet) {
        if (tweets.contains(tweet)) {
            throw new IllegalArgumentException("Tweet is already in TweetList!");
        }
        tweets.add(tweet);
    }

    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public ArrayList<Tweet> getTweets() {
        Comparator<Tweet> dateComparator = new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                if (t1.date.before(t2.date)) {
                    return -1;
                } else if (t1.date.equals(t2.date)) {
                    return 0;
                } else if (t1.date.after(t2.date)) {
                    return 1;
                }
                return 0;
            }
        };

        ArrayList<Tweet> sortedTweets = new ArrayList<Tweet>(tweets);
        Collections.sort(sortedTweets, dateComparator);
        return sortedTweets;
    }

    public int getCount() {
        return tweets.size();
    }
}
