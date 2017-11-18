package misiulia.alex.dev.andrtwitter;

import java.util.Collection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import misiulia.alex.dev.andrtwitter.entity.Tweet;
import misiulia.alex.dev.andrtwitter.entity.User;
import misiulia.alex.dev.andrtwitter.network.HttpClient;
import misiulia.alex.dev.andrtwitter.searchusers.SearchUsersActivity;
import misiulia.alex.dev.andrtwitter.utils.ViewUtils;

public class UserInfoActivity extends BaseActivity {
    public static String USER_ID = "UserId";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageView mProfileImageView;
    private TextView mNameTextView;
    private TextView mNickTextView;
    private TextView mDescriptionTextView;
    private TextView mLocationTextView;
    private TextView mUrlTextView;
    private TextView mFollowingTextView;
    private TextView mFollowersTextView;

    private TextView mFollowersHintTextView;
    private TextView mFollowingHintTextView;

    private Toolbar mToolbar;


    private RecyclerView mRecyclerView;
//    private FloatingActionButton mCreateTweetFab;
    private TweetAdapter mTweetAdapter;

    private HttpClient mHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity_layout);

        Long userId = getIntent().getLongExtra(USER_ID, -1);

        if(userId  != -1) {
            mHttpClient = new HttpClient();
            initView(userId);
            requestUserInfo(userId, false);
        }
        else {
            Toast.makeText(this, "User id isn't passed to screen", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, SearchUsersActivity.class));
        } else {
            throw new IllegalArgumentException("Unknown item " + item.getItemId());
        }
        return true;
    }

    private void initView(final long userId) {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestUserInfo(userId, true);
            }
        });

        mProfileImageView = findViewById(R.id.profile_image_view);
        mNameTextView = findViewById(R.id.user_name_text_view);
        mNickTextView = findViewById(R.id.user_nick_text_view);
        mDescriptionTextView = findViewById(R.id.description_text_view);
        mLocationTextView = findViewById(R.id.location_text_view);
        mUrlTextView = findViewById(R.id.url_text_view);
        mFollowersTextView = findViewById(R.id.followers_count_text_view);
        mFollowingTextView = findViewById(R.id.following_count_text_view);

        mFollowingHintTextView = findViewById(R.id.following_hint_text_view);
        mFollowersHintTextView = findViewById(R.id.followers_hint_text_view);

        mRecyclerView = findViewById(R.id.tweets_recycler_view);
        /*mCreateTweetFab = findViewById(R.id.add_tweet_fab);
        mCreateTweetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateTweetDialog();
            }
        });*/

        // for smooth scroll inside NestedScrollView
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(ViewUtils.getRvItemDecoration(this, RecyclerView.VERTICAL));
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
        mToolbar.setTitle(user.getName());

        Picasso.with(this).load(user.getFullImageUrl()).into(mProfileImageView);
        mNameTextView.setText(user.getName());
        mNickTextView.setText(user.getNickNameFormatted());
        mLocationTextView.setText(user.getLocation());
        mUrlTextView.setText(user.getUrl());
        mDescriptionTextView.setText(user.getDescription());

        mFollowersTextView.setText(String.valueOf(user.getFollowersCount()));
        String followersHint = getQuantityString(R.plurals.followers_count, user.getFollowersCount());
        mFollowersHintTextView.setText(followersHint);

        mFollowingTextView.setText(String.valueOf(user.getFavouritesCount()));
        String followingHint = getQuantityString(R.plurals.following_count, user.getFavouritesCount());
        mFollowingHintTextView.setText(followingHint);
    }

    private String getQuantityString(int resId, int quantity) {
        return getResources().getQuantityString(resId, quantity);
    }

    private void displayTweets(Collection<Tweet> tweets) {
        mTweetAdapter.setItems(tweets);
    }


    public void showLoading(boolean isLoading) {
        mSwipeRefreshLayout.setRefreshing(isLoading);
    }
    // FIXME in extended course
    @SuppressLint("StaticFieldLeak")
    private void requestUserInfo(final long userId, final boolean isSwipeRefresh) {
        new AsyncTask<Void, Void, User>() {
            Collection<Tweet> tweets;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showLoading(true);
            }

            @Override
            protected User doInBackground (Void...voids){
                tweets = mHttpClient.readTweets(userId);
                return mHttpClient.readUserInfo(userId);
            }

            @Override
            protected void onPostExecute (User user){
                super.onPostExecute(user);

                if(isSwipeRefresh) mTweetAdapter.clearItems();

                showLoading(false);
                displayProfile(user);
                displayTweets(tweets);
            }
        }.execute();
    }
}
