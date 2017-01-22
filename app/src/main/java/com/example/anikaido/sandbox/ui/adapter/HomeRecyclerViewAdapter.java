package com.example.anikaido.sandbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anikaido.sandbox.R;

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
