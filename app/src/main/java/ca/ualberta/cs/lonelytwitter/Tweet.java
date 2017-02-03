package ca.ualberta.cs.lonelytwitter;


import java.util.Date;


/**
 * Created by me7 on 1/19/17.
 */

/**
 * This class will set and get the date and message
 * and also this class will check if the date and message satisify the requirement
 * @throws TweetTooLongException if the length of this tweet exist 140 characters
 * @return date
 * @return message
 */
public abstract class Tweet implements Tweetable {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) throws TweetTooLongException{
        if (message.length()>140){
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    private Date date;
    private String message;

    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }
    public Tweet(Date date, String message){
        this.message = message;
        this.date=date;
    }

    public  Tweet(){
        super();
        this.date =new Date();
    }

    public abstract Boolean isImportant();

    @Override
    public String toString(){
        return date.toString()+ " | " + message;


    }
}
