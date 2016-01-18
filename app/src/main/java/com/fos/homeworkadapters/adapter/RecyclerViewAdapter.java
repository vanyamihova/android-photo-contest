package com.fos.homeworkadapters.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fos.homeworkadapters.CommentsActivity;
import com.fos.homeworkadapters.MainActivity;
import com.fos.homeworkadapters.R;
import com.fos.homeworkadapters.model.ArticleModel;

import java.util.List;

/**
 * Created by Vanya Mihova on 9.11.2015 г..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {

    List<ArticleModel> allData;
    Context mContext;

    public RecyclerViewAdapter(List<ArticleModel> allData) {
        this.allData = allData;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        final ArticleModel model = allData.get(position);

        holder.title.setText(model.getTitle());
        holder.counters.setText(generateCountString(model.getCountPointers(), model.getCountComments()));
        holder.btnVoteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.btnVoteDown.isPressed()) {
                    model.increasePoints();
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnVoteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.btnVoteUp.isPressed()) {
                    model.decreasePoints();
                    notifyDataSetChanged();
                }
            }
        });

        Glide.with(mContext)
                .load(model.getImageResource())
                .centerCrop()
                .placeholder(R.drawable.progress)
                .crossFade()
                .into(holder.imageView);

        holder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentsActivity.class);
                intent.putExtra("itemPosition", position);
                ((Activity) mContext).startActivityForResult(intent, 5000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }


    private String generateCountString(int pointers, int comments) {
        return pointers + " Points * " + comments + " Comments";
    }




    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        TextView counters;
        ImageView btnVoteUp;
        ImageView btnVoteDown;
        ImageView btnComments;

        public CardViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text_title);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_preview);
            counters = (TextView) itemView.findViewById(R.id.text_counters);
            btnVoteUp = (ImageView) itemView.findViewById(R.id.image_view_arrow_up);
            btnVoteDown = (ImageView) itemView.findViewById(R.id.image_view_arrow_down);
            btnComments = (ImageView) itemView.findViewById(R.id.image_view_comments);
        }
    }

}
