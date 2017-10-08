package misiulia.alex.dev.andrtwitter;

import static android.view.LayoutInflater.from;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public void setItems(List<Tweet> tweets) {
        mTweetList.addAll(tweets);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTweetList.size();
    }

    class TweetViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNameTextView;
        private TextView mNickTextView;
        private TextView mContentTextView;
        private TextView mCommentsTextView;
        private TextView mRepostsTextView;
        private TextView mLikesTextView;

        public TweetViewHolder(View itemView) {
            super(itemView);

        }

        public void bind(Tweet tweet) {

        }
    }
}
