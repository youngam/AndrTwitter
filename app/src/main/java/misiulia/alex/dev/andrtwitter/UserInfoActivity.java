package misiulia.alex.dev.andrtwitter;

import static java.util.Arrays.asList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class UserInfoActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private TweetAdapter mTweetAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity_layout);

        mRecyclerView = findViewById(R.id.tweets_recycler_view);

        // for smooth scroll inside NestedScrollView
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        mTweetAdapter = new TweetAdapter();
        mRecyclerView.setAdapter(mTweetAdapter);
        mTweetAdapter.setItems(asList(
                new Tweet(), new Tweet(), new Tweet(),
                new Tweet(), new Tweet(), new Tweet(),
                new Tweet(), new Tweet(), new Tweet()
        ));
    }
}
