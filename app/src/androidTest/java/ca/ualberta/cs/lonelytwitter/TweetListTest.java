package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;


/**
 * Created by vamaraju on 9/30/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2  implements MyObserver{
    private boolean wasNotified = false;
    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    // public something myHelperForTesting()


    // you need test as the prefix; only methods that have test as a prefix will run!!!!
    // note: tets methods and tests in general are not guaranteed to run in any order
    // when you write tests, assume everything is finished (it is a finished product)
    // it is not like when you test normally, line by line or whatever
    public void testRemoveTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addTweet(tweet);
        tweetList.removeTweet(tweet);

        // assert will test your test case.
        assertFalse(tweetList.hasTweet(tweet));

    }

    public void testHasTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testAddTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));

        try {
            tweetList.addTweet(tweet);
            fail("AddTweet IllegalArgumentException Test Failed!");
        }
        catch(IllegalArgumentException e) {
            // caught the exception, meaning it was actually thrown! test succeeded
        }


    }

    public void testGetCount() {
        TweetList tweetList = new TweetList();
        assertTrue(tweetList.getCount() == 0);
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addTweet(tweet);
        assertTrue(tweetList.getCount() == 1);

        Tweet tweet2 = new NormalTweet("hihihihi2");
        tweetList.addTweet(tweet2);
        assertTrue(tweetList.getCount() == 2);

        tweetList.removeTweet(tweet);
        assertTrue(tweetList.getCount() == 1);
    }

    public void testGetTweets() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        Tweet tweet2 = new NormalTweet("hihihihi2");
        Tweet tweet3 = new NormalTweet("hihihihi3");
        tweetList.addTweet(tweet);
        tweetList.addTweet(tweet2);
        tweetList.addTweet(tweet3);

        ArrayList<Tweet> sortedTweets = tweetList.getTweets();
        assertTrue((sortedTweets.get(0) == tweet) && (sortedTweets.get(1) == tweet2) && (sortedTweets.get(2) == tweet3));
    }


    public void testGetTweetType() {

    }

    public void testTweetListChanged() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addObserver(this);
        wasNotified = false;
        assertFalse(wasNotified);
        tweetList.addTweet(tweet);
        assertTrue(wasNotified);


    }

    public void myNotify() {
        wasNotified = true;
    }

}