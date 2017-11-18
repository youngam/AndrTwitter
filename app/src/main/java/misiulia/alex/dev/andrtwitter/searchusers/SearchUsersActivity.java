package misiulia.alex.dev.andrtwitter.searchusers;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;
import static java.util.Objects.requireNonNull;
import static misiulia.alex.dev.andrtwitter.UserInfoActivity.USER_ID;

import java.util.Collection;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import misiulia.alex.dev.andrtwitter.BaseActivity;
import misiulia.alex.dev.andrtwitter.R;
import misiulia.alex.dev.andrtwitter.UserInfoActivity;
import misiulia.alex.dev.andrtwitter.entity.User;
import misiulia.alex.dev.andrtwitter.network.HttpClient;
import misiulia.alex.dev.andrtwitter.utils.ViewUtils;

public class SearchUsersActivity extends BaseActivity {
    private Toolbar mToolbar;
    private EditText mQueryEditText;
    private Button mSearchButton;
    private RecyclerView mUsersRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;


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
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                readUsers(mQueryEditText.getText().toString(), true);
            }
        });

        mQueryEditText = mToolbar.findViewById(R.id.query_edit_text);
        mSearchButton = mToolbar.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        mQueryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
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

        mUsersRecyclerView.addItemDecoration(ViewUtils.getRvItemDecoration(this, VERTICAL));

        mHttpClient = new HttpClient();
    }

    private void performSearch() {
        mQueryEditText.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(mQueryEditText.getWindowToken(), 0);

        if (mQueryEditText.getText().toString().isEmpty()) {
            Toast.makeText(SearchUsersActivity.this, R.string.not_enough_symbols_msg, Toast.LENGTH_SHORT).show();
        } else {
            mUserAdapter.clearItems();
            readUsers(mQueryEditText.getText().toString());
        }
    }

    private void showLoading(boolean isLoading) {
        mSwipeRefreshLayout.setRefreshing(isLoading);
    }

        private void readUsers(final String query) {
        readUsers(query, false);
    }

            private void readUsers(final String query, final boolean isSwipeRefresh) {

        new AsyncTask<Void, Void, Collection<User>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showLoading(true);
            }

            @Override
            protected Collection<User> doInBackground(Void... voids) {
                return mHttpClient.readUsers(query);
            }

            @Override
            protected void onPostExecute(Collection<User> users) {
                super.onPostExecute(users);
                if(isSwipeRefresh) mUserAdapter.clearItems();

                showLoading(false);
                mUserAdapter.setItems(users);
            }
        }.execute();
    }
}
