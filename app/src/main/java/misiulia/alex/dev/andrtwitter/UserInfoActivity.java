package misiulia.alex.dev.andrtwitter;

import java.util.Random;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import misiulia.alex.dev.andrtwitter.entity.Profile;
import misiulia.alex.dev.andrtwitter.network.HttpClient;

public class UserInfoActivity extends BaseActivity {
    private static String CHUCK_NAME = "AndroidLearning";
    private static String CHUCK_NIK = "@it_pro_learning";
    public static String USER_ID = "UserId";
    private int mHoursCounter = 20;

    public static final Random RANDOM = new Random();


    private RecyclerView mRecyclerView;
    private FloatingActionButton mCreateTweetFab;
    private TweetAdapter mTweetAdapter;

    private HttpClient mHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity_layout);

        mRecyclerView = findViewById(R.id.tweets_recycler_view);
        mCreateTweetFab = findViewById(R.id.add_tweet_fab);
        mCreateTweetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateTweetDialog();
            }
        });

        // for smooth scroll inside NestedScrollView
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        mTweetAdapter = new TweetAdapter();
        mRecyclerView.setAdapter(mTweetAdapter);

        mHttpClient = new HttpClient();

        Long userId = getIntent().getLongExtra(USER_ID, -1);

        if(userId  != -1) {
            requestUserInfo(userId);
        }
        else {
            Toast.makeText(this, "User id isn't passed to screen", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void showCreateTweetDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_tweet_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText contentEditText = dialogView.findViewById(R.id.tweet_content_edit_text);

        dialogBuilder.setTitle("Что происходит?");
        dialogBuilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String content = contentEditText.getText().toString();
                Tweet newTweet = new Tweet(CHUCK_NAME, CHUCK_NIK, "5 sec", content, false);
                mTweetAdapter.addTweet(newTweet);
            }
        });
        dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void readProfiles() {

    }


    private void displayProfile(Profile profile) {

    }

    static class ReadProfileTask extends AsyncTask<Long, Void, Profile> {
        private UserInfoActivity mUserInfoActivity;

        public ReadProfileTask(UserInfoActivity userInfoActivity) {
            mUserInfoActivity = userInfoActivity;
        }

        @Override
        protected Profile doInBackground(Long... ids) {
            return mUserInfoActivity.mHttpClient.readProfile(ids[0]);
        }

        @Override
        protected void onPostExecute(Profile profile) {
            super.onPostExecute(profile);
            mUserInfoActivity.displayProfile(profile);
        }

    }
    private void requestUserInfo(long userId) {
        new ReadProfileTask(this).execute(userId);
    }

    private Tweet getTweet(String content) {
        return new Tweet(CHUCK_NAME, CHUCK_NIK, mHoursCounter-- + " h", content);
    }
}
