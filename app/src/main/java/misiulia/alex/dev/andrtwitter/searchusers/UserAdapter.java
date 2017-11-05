package misiulia.alex.dev.andrtwitter.searchusers;

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

import misiulia.alex.dev.andrtwitter.R;
import misiulia.alex.dev.andrtwitter.entity.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUsers = new ArrayList<>();
    private OnUserClickListener mOnUserClickListener;

    public UserAdapter(OnUserClickListener onUserClickListener) {
        mOnUserClickListener = onUserClickListener;
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.user_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    public void setItems(Collection<User> users) {
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView mUserImageView;
        private TextView mNameTextView;
        private TextView mNickTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnUserClickListener.onUserClick(mUsers.get(getLayoutPosition()));
                }
            });

            mUserImageView = itemView.findViewById(R.id.profile_image_view);
            mNameTextView = itemView.findViewById(R.id.user_name_text_view);
            mNickTextView = itemView.findViewById(R.id.user_nick_text_view);
        }

        public void bind(User user) {
            mNameTextView.setText(user.getName());
            mNickTextView.setText(user.getNickNameFormatted());
            Picasso.with(itemView.getContext()).load(user.getBigImageUrl()).into(mUserImageView);
        }
    }
}
