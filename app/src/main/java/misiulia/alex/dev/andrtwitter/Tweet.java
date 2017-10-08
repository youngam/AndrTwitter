package misiulia.alex.dev.andrtwitter;

import java.util.Random;

public class Tweet {
    private Random mRandom = new Random();

    String name;
    String nick;
    String dateString;
    String content;
    int likes;
    int retweets;
    int comments;

    public Tweet(String name, String nick, String dateString, String content, boolean randomStats) {
        this.name = name;
        this.nick = nick;
        this.dateString = dateString;
        this.content = content;
        if(randomStats) {
            this.likes = mRandom.nextInt(69);
            this.retweets = mRandom.nextInt(69);
            this.comments = mRandom.nextInt(69);
        }
    }

    public Tweet(String name, String nick, String dateString, String content) {
        this(name, nick, dateString, content, true);
    }
}
