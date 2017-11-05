package misiulia.alex.dev.andrtwitter;

import java.util.Collection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import misiulia.alex.dev.andrtwitter.entity.Tweet;
import misiulia.alex.dev.andrtwitter.entity.User;
import misiulia.alex.dev.andrtwitter.network.HttpClient;

public class UserInfoActivity extends BaseActivity {
    public static String USER_ID = "UserId";

    private ImageView mProfileImageView;
    private TextView mNameTextView;
    private TextView mNickTextView;
    private TextView mDescriptionTextView;
    private TextView mLocationTextView;
    private TextView mUrlTextView;
    private TextView mFollowingTextView;
    private TextView mFollowersTextView;


    private RecyclerView mRecyclerView;
    private FloatingActionButton mCreateTweetFab;
    private TweetAdapter mTweetAdapter;

    private HttpClient mHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity_layout);
        initView();
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

    private void initView() {
        mProfileImageView = findViewById(R.id.profile_image_view);
        mNameTextView = findViewById(R.id.user_name_text_view);
        mNickTextView = findViewById(R.id.user_nick_text_view);
        mDescriptionTextView = findViewById(R.id.description_text_view);
        mLocationTextView = findViewById(R.id.location_text_view);
        mUrlTextView = findViewById(R.id.url_text_view);
        mFollowersTextView = findViewById(R.id.followers_count_text_view);
        mFollowingTextView = findViewById(R.id.following_count_text_view);

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


    }

    private void showCreateTweetDialog() {
/*        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_tweet_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText contentEditText = dialogView.findViewById(R.id.tweet_content_edit_text);

        dialogBuilder.setTitle("Что происходит?");
        dialogBuilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String content = contentEditText.getText().toString();
                misiulia.alex.dev.andrtwitter.Tweet newTweet = new misiulia.alex.dev.andrtwitter.Tweet("Temp", "temp", "5 sec", content, false);
                mTweetAdapter.addTweet(newTweet);
            }
        });
        dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();*/
    }

    private void displayProfile(User user) {
        Picasso.with(this).load(user.getProfileImageUrl()).into(mProfileImageView);
        mNameTextView.setText(user.getName());
        mNickTextView.setText(user.getNickNameFormatted());
        mLocationTextView.setText(user.getLocation());
        mUrlTextView.setText(user.getUrl());
        mDescriptionTextView.setText(user.getDescription());
        mFollowersTextView.setText(String.valueOf(user.getFollowersCount()));
        mFollowingTextView.setText(String.valueOf(user.getFavouritesCount()));
    }

    static class ReadProfileTask extends AsyncTask<Long, Void, User> {
        private UserInfoActivity mUserInfoActivity;
        private Collection<Tweet> mTweets;

        public ReadProfileTask(UserInfoActivity userInfoActivity) {
            mUserInfoActivity = userInfoActivity;
        }

        @Override
        protected User doInBackground(Long... ids) {
            Long id = ids[0];
            mTweets = mUserInfoActivity.mHttpClient.readTweets(id);
            return mUserInfoActivity.mHttpClient.readProfile(id);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            mUserInfoActivity.displayProfile(user);
            mUserInfoActivity.displayTweets(mTweets);
        }

    }

    private void displayTweets(Collection<Tweet> tweets) {
        mTweetAdapter.setItems(tweets);
    }

    private void requestUserInfo(long userId) {
        new ReadProfileTask(this).execute(userId);
    }
}
