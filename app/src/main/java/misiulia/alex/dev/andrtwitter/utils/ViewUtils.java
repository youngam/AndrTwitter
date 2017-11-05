package misiulia.alex.dev.andrtwitter.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import misiulia.alex.dev.andrtwitter.R;

public class ViewUtils {

    public static RecyclerView.ItemDecoration getRvItemDecoration(Context context, int orientation) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(context, orientation);
        itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_line));
        return itemDecoration;
    }
}
