package com.example.admin.trial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 31/03/2018.
 */

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context mCtx;
    private List<Reviewextract> DataList;

    public ReviewAdapter(Context mCtx, List<Reviewextract> dataList) {
        this.mCtx = mCtx;
        DataList = dataList;
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.reviewdocument,null);
        ReviewAdapter.ReviewViewHolder reviewViewHolder = new ReviewAdapter.ReviewViewHolder(view);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        Reviewextract reviewextract = DataList.get(position);
        holder.username.setText(reviewextract.getUsername().toString());
        holder.review.setText(reviewextract.getReview().toString());
    }



    @Override
    public int getItemCount() {
        return DataList.size();
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView username,review;
        public ReviewViewHolder(View view) {
            super(view);

            username = (TextView) view.findViewById(R.id.username);
            review = (TextView)view.findViewById(R.id.review);
        }
    }
}
