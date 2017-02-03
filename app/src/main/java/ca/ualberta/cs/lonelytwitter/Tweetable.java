package ca.ualberta.cs.lonelytwitter;

/**
 * Created by me7 on 1/19/17.
 */
/**
 *This is class for checking whether the tweet is Tweetable
 *
 * @throws TweetTooLongException if this tweet is too long
 *
 */
public interface Tweetable {
    public String getMessage();
    public void setMessage(String string) throws TweetTooLongException;


}
