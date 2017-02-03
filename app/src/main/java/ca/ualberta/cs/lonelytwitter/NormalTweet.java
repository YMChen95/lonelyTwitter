package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by me7 on 1/19/17.
 */
/**
 *This is class for the Normal tweet and it is extends from tweet class
 * return a Boolean true/false for important/normal tweets
 */
public class NormalTweet extends Tweet{
    public NormalTweet(String message) {
        super(message);
    }

    public NormalTweet(Date date, String message) {
        super(date, message);
    }
    @Override
    public Boolean isImportant(){
        return false;
    }
}
