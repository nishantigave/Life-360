package com.example.admin.trial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 25/03/2018.
 */

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder> {

    private Context mCtx;
    private List<Responseextract> DataList;

    public ResponseAdapter(Context mCtx, List<Responseextract> dataList) {
        this.mCtx = mCtx;
        DataList = dataList;
    }

    @Override
    public ResponseAdapter.ResponseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.documentresponse,null);
        ResponseAdapter.ResponseViewHolder responseViewHolder = new ResponseAdapter.ResponseViewHolder(view);
        return responseViewHolder;
    }

    @Override
    public void onBindViewHolder(ResponseAdapter.ResponseViewHolder holder, int position) {
        Responseextract RE = DataList.get(position);
        holder.disastername.setText(RE.getName().toString());
        holder.response.setText(RE.getResponse().toString());
        holder.responsers.setText(RE.getResponser().toString());

    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ResponseViewHolder extends RecyclerView.ViewHolder {

        TextView disastername , response , responsers;
        public ResponseViewHolder(View itemView) {
            super(itemView);
        disastername = (TextView) itemView.findViewById(R.id.disastername);
        response = (TextView) itemView.findViewById(R.id.response);
        responsers = (TextView)itemView.findViewById(R.id.responsers);
        }


    }
}
