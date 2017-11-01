package misiulia.alex.dev.andrtwitter;

import static misiulia.alex.dev.andrtwitter.UserInfoActivity.USER_ID;

import android.content.Intent;
import android.os.Bundle;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import misiulia.alex.dev.andrtwitter.oauth.AuthPreference;

public class MainActivity extends BaseActivity {
    private TwitterLoginButton mTwitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTwitterLoginButton = findViewById(R.id.login_button);
        mTwitterLoginButton.setText(R.string.sign_in_with_twitter);
        mTwitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                AuthPreference.getInstance().setTwitterSession(result.data);
                startActivity(new Intent(MainActivity.this, UserInfoActivity.class)
                                        .putExtra(USER_ID, result.data.getUserId()));
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }
}
