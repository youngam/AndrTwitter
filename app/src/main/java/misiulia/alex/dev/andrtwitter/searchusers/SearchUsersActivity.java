package misiulia.alex.dev.andrtwitter.searchusers;

import static misiulia.alex.dev.andrtwitter.UserInfoActivity.USER_ID;

import java.util.Collection;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import misiulia.alex.dev.andrtwitter.BaseActivity;
import misiulia.alex.dev.andrtwitter.R;
import misiulia.alex.dev.andrtwitter.UserInfoActivity;
import misiulia.alex.dev.andrtwitter.entity.User;
import misiulia.alex.dev.andrtwitter.network.HttpClient;

public class SearchUsersActivity extends BaseActivity {
    private Toolbar mToolbar;
    private EditText mQueryEditText;
    private Button mSearchButton;
    private RecyclerView mUsersRecyclerView;
    private UserAdapter mUserAdapter;
    private HttpClient mHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_users_layout);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mQueryEditText = mToolbar.findViewById(R.id.query_edit_text);
        mSearchButton = mToolbar.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUsers(mQueryEditText.getText().toString());
            }
        });


        mUsersRecyclerView = findViewById(R.id.users_recycler_view);
        mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(SearchUsersActivity.this, UserInfoActivity.class);
                intent.putExtra(USER_ID, user.getId());
                startActivity(intent);
            }
        });
        mUsersRecyclerView.setAdapter(mUserAdapter);

        mHttpClient = new HttpClient();
    }

    private void readUsers(final String query) {
        new AsyncTask<Void, Void, Collection<User>>() {
            @Override
            protected Collection<User> doInBackground(Void... voids) {
                return mHttpClient.readUsers(query);
            }

            @Override
            protected void onPostExecute(Collection<User> users) {
                super.onPostExecute(users);
                mUserAdapter.setItems(users);
            }
        }.execute();
    }
}
