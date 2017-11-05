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

import com.squareup.picasso.Picasso;

import misiulia.alex.dev.andrtwitter.entity.Tweet;
import misiulia.alex.dev.andrtwitter.utils.DateFormatter;

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
        private ImageView mUserImageView;
        private TextView mNameTextView;
        private TextView mNickTextView;
        private TextView mCreationDateTextView;
        private TextView mContentTextView;
        private TextView mRetweetsTextView;
        private TextView mLikesTextView;

        public TweetViewHolder(View itemView) {
            super(itemView);
            mUserImageView = itemView.findViewById(R.id.profile_image_view);
            mNameTextView = itemView.findViewById(R.id.author_name_text_view);
            mNickTextView = itemView.findViewById(R.id.author_nick_text_view);
            mCreationDateTextView = itemView.findViewById(R.id.creation_date_text_view);
            mContentTextView = itemView.findViewById(R.id.tweet_content_text_view);
            mRetweetsTextView = itemView.findViewById(R.id.retweets_text_view);
            mLikesTextView = itemView.findViewById(R.id.likes_text_view);
        }

        public void bind(Tweet tweet) {
            mNameTextView.setText(tweet.getUser().getName());
            mNickTextView.setText(tweet.getUser().getNickNameFormatted());
            String createdAt = tweet.getCreatedAt();
            String formattedDate = DateFormatter.format(createdAt, DateFormatter.MONTH_DAY_FORMAT);
            mCreationDateTextView.setText(formattedDate);
            mContentTextView.setText(tweet.getText());
            mRetweetsTextView.setText(String.valueOf(tweet.getRetweetCount()));
            mLikesTextView.setText(String.valueOf(tweet.getFavouriteCount()));

            Picasso.with(itemView.getContext()).load(tweet.getUser().getProfileImageUrl()).into(mUserImageView);
        }
    }
}
