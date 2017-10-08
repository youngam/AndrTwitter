package misiulia.alex.dev.andrtwitter;

import static java.util.Arrays.asList;

import java.util.List;
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
import android.view.Window;
import android.widget.EditText;

public class UserInfoActivity extends BaseActivity {
    private static String CHUCK_NAME = "Chuck Jokes";
    private static String CHUCK_NIK = "@chuckjokes";

    public static final Random RANDOM = new Random();


    private RecyclerView mRecyclerView;
    private FloatingActionButton mCreateTweetFab;
    private TweetAdapter mTweetAdapter;

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

        requestUserInfo();
    }

    private void showCreateTweetDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_tweet_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText contentEditText = dialogView.findViewById(R.id.tweet_content_edit_text);

        dialogBuilder.setTitle("What's on your mind?");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String content = contentEditText.getText().toString();
                Tweet newTweet = new Tweet(CHUCK_NAME, CHUCK_NIK, "5 sec", content, false);
                mTweetAdapter.addTweet(newTweet);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void requestUserInfo() {
        new AsyncTask<Void, Void, List<Tweet>>() {
            @Override
            protected List<Tweet> doInBackground(Void... voids) {
                return asList(
                        getNorrisTweet("When Chuck Norris was born he drove his mom home from the hospital"),
                        getNorrisTweet("Chuck Norris has a diary. It's called the Guinness Book of World Records."),
                        getNorrisTweet("Chuck Norris doesn't worry about high gas prices. His vehicles run on fear."),
                        getNorrisTweet("Chuck Norris can kill two stones with one bird."),
                        getNorrisTweet("When a zombie apocalypse starts, Chuck Norris doesn't try to survive. The zombies do."),
                        getNorrisTweet("There once was a street called Chuck Norris, but the name was changed for" +
                                " public safety because nobody crosses Chuck Norris and lives."),
                        getNorrisTweet("It is considered a great accomplishment to go down Niagara Falls in a wooden barrel. " +
                                "Chuck Norris can go up Niagara Falls in a cardboard box..")

                );
            }

            @Override
            protected void onPostExecute(List<Tweet> tweets) {
                super.onPostExecute(tweets);
                mTweetAdapter.setItems(tweets);
            }
        }.execute();
    }

    private Tweet getNorrisTweet(String content) {
        return new Tweet(CHUCK_NAME, CHUCK_NIK, RANDOM.nextInt(12) + " h", content);
    }
}
