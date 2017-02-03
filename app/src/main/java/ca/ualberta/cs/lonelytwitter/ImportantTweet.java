package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by me7 on 1/19/17.
 */

/**
 *This is class for the important tweet and it is extends from tweet class
 */
public class ImportantTweet extends Tweet {
    public ImportantTweet(String message) {
        super(message);
    }

    public ImportantTweet(Date date, String message) {
        super(date, message);
    }
    @Override
    public Boolean isImportant(){
        return true;
    }
}
