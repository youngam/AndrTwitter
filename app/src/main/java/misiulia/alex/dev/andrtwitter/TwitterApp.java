package misiulia.alex.dev.andrtwitter;

import android.app.Application;
import android.content.Context;

import com.twitter.sdk.android.core.Twitter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TwitterApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }

}
