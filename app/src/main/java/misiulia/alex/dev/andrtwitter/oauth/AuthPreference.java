package misiulia.alex.dev.andrtwitter.oauth;

import com.twitter.sdk.android.core.TwitterSession;

public class AuthPreference {
    public TwitterSession mTwitterSession;

    private static AuthPreference sInstance;

    public static AuthPreference getInstance() {
        if(sInstance == null) {
            sInstance = new AuthPreference();
        }
        return sInstance;
    }

    private AuthPreference() {

    }



    public TwitterSession getTwitterSession() {
        return mTwitterSession;
    }

    public void setTwitterSession(TwitterSession twitterSession) {
        mTwitterSession = twitterSession;
    }

}
