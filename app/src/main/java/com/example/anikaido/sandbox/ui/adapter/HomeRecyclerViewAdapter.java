package com.example.anikaido.sandbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anikaido.sandbox.R;
import com.like.LikeButton;

import java.util.ArrayList;

/**
 * Created by anikaido on 2017/01/08.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public ArrayList<String> mData;
    private OnRecyclerListener mListener;

    public interface OnRecyclerListener {
        void onRecyclerClicked(View v, int position);
    }

    public HomeRecyclerViewAdapter(Context context, ArrayList<String> data, OnRecyclerListener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mData != null && mData.size() > 0 && mData.get(position) != null) {
            holder.mTextView.setText(mData.get(position));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRecyclerClicked(view, position);
            }
        });

        final LikeButton likeButton1 = (LikeButton) holder.itemView.findViewById(R.id.star_button1);
        final LikeButton likeButton2 = (LikeButton) holder.itemView.findViewById(R.id.star_button2);
        final LikeButton likeButton3 = (LikeButton) holder.itemView.findViewById(R.id.star_button3);
        final LikeButton likeButton4 = (LikeButton) holder.itemView.findViewById(R.id.star_button4);
        final LikeButton likeButton5 = (LikeButton) holder.itemView.findViewById(R.id.star_button5);

        likeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLikeButton(likeButton1, likeButton2, likeButton3, likeButton4, likeButton5);
                likeButton1.onClick(likeButton1);
            }
        });

        likeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLikeButton(likeButton1, likeButton2, likeButton3, likeButton4, likeButton5);
                likeButton1.onClick(likeButton1);
                likeButton2.onClick(likeButton2);
            }
        });

        likeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLikeButton(likeButton1, likeButton2, likeButton3, likeButton4, likeButton5);
                likeButton1.onClick(likeButton1);
                likeButton2.onClick(likeButton2);
                likeButton3.onClick(likeButton3);
            }
        });

        likeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLikeButton(likeButton1, likeButton2, likeButton3, likeButton4, likeButton5);
                likeButton1.onClick(likeButton1);
                likeButton2.onClick(likeButton2);
                likeButton3.onClick(likeButton3);
                likeButton4.onClick(likeButton4);
            }
        });

        likeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLikeButton(likeButton1, likeButton2, likeButton3, likeButton4, likeButton5);
                likeButton1.onClick(likeButton1);
                likeButton2.onClick(likeButton2);
                likeButton3.onClick(likeButton3);
                likeButton4.onClick(likeButton4);
                likeButton5.onClick(likeButton5);
            }
        });
    }

    private void clearLikeButton(LikeButton l1, LikeButton l2, LikeButton l3, LikeButton l4, LikeButton l5) {
        l1.setLiked(false);
        l2.setLiked(false);
        l3.setLiked(false);
        l4.setLiked(false);
        l5.setLiked(false);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }

        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_home_text);
        }
    }
}
