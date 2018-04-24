package com.example.admin.trial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 23/03/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mCtx;
    private List<dataextract> DataList;

    public DataAdapter(Context mCtx, List<dataextract> dataList) {
        this.mCtx = mCtx;
        DataList = dataList;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.document,null);
        DataViewHolder dataViewHolder = new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        dataextract dataextract1 = DataList.get(position);

        holder.disastername.setText(dataextract1.getName().toString());
        holder.injuriesname.setText(dataextract1.getInjuries().toString());
        holder.Areaname.setText(dataextract1.getArea().toString());
        holder.yearname.setText(dataextract1.getYear().toString());
        holder.deathname.setText(dataextract1.getDeathtoll().toString());
        holder.typename.setText(dataextract1.getType().toString());
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView disastername, injuriesname, Areaname, yearname, deathname, typename;
        public DataViewHolder(View itemView) {
            super(itemView);
            disastername  = (TextView) itemView.findViewById(R.id.disastername);
            injuriesname  = (TextView) itemView.findViewById(R.id.injuriesname);
            Areaname  = (TextView) itemView.findViewById(R.id.Areaname);
            yearname  = (TextView) itemView.findViewById(R.id.yearname);
            deathname  = (TextView) itemView.findViewById(R.id.deathname);
            typename  = (TextView) itemView.findViewById(R.id.typename);
        }
    }
}
