package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Button saveButton;
    private EditText bodyText;
    private EditText editorText;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testEditTweet() {
        // when you call getactivity, android will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        // reset the app o a known state
        activity.getTweets().clear();

        // add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("test tweet");
            }
        });
        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();


        // make sure the tweet was actually added
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("test tweet", tweet.getText());



        // ensure that the activity starts up
        // following copied from: https://developer.android.com/training/activity-testing/activity-functional-testing.html - 2015-10-14
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        // click on the tweet to edit
        activity.runOnUiThread(new Runnable () {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // test that the editor starts up with the right tweet in it to edit
        editorText = activity.getBodyText();
        assertEquals(editorText.getText(), "test tweet");

        // test that we can edit that tweet
        activity.runOnUiThread(new Runnable () {
            public void run() {
                View v = editorText.getChildAt(0);
                editorText.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // test that we can push some kind of save button for that tweet


        // test that the new modified tweet text was actually saved

        // test that the new modified tweet text is displayed on the other activity

        // clean up out activities at the end of our test; otherwise tests will hang while waiting for other activities
        receiverActivity.finish();
    }
}