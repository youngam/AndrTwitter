package misiulia.alex.dev.andrtwitter;

import static android.view.LayoutInflater.from;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import misiulia.alex.dev.andrtwitter.entity.Tweet;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {
    private List<Tweet> mTweetList = new ArrayList<>();

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.tweet_item_view, parent, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.bind(mTweetList.get(position));
    }

    public void setItems(Collection<Tweet> tweets) {
        mTweetList.addAll(tweets);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTweetList.size();
    }

    public void addTweet(Tweet newTweet) {
        mTweetList.add(0, newTweet);
        notifyItemInserted(0);
    }

    class TweetViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mNickTextView;
        private TextView mContentTextView;
        private TextView mCommentsTextView;
        private TextView mRetweetsTextView;
        private TextView mLikesTextView;

        public TweetViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.author_name_text_view);
            mNickTextView = itemView.findViewById(R.id.author_nick_text_view);
            mContentTextView = itemView.findViewById(R.id.tweet_content_text_view);
            mCommentsTextView = itemView.findViewById(R.id.comments_text_view);
            mRetweetsTextView = itemView.findViewById(R.id.retweets_text_view);
            mLikesTextView = itemView.findViewById(R.id.likes_text_view);
        }

        public void bind(Tweet tweet) {
            mNameTextView.setText(tweet.getUser().getName());
            mNickTextView.setText(tweet.getCreatedAt());
            mContentTextView.setText(tweet.getText());
            mCommentsTextView.setText(String.valueOf(tweet.getReplyCount()));
            mRetweetsTextView.setText(String.valueOf(tweet.getRetweetCount()));
            mLikesTextView.setText(String.valueOf(tweet.getFavouriteCount()));
        }
    }
}
