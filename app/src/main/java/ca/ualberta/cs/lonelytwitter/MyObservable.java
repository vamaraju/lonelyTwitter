package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by vamaraju on 10/7/15.
 */
public interface MyObservable {
    void addObserver(MyObserver o);
    void notifyObservers();


}

