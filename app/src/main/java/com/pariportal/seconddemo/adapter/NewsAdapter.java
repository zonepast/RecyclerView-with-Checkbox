package com.pariportal.seconddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.pariportal.seconddemo.R;
import com.pariportal.seconddemo.model.Source;
import java.util.List;

/**
 * Created by Android System on 11/17/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<Source> sourceList;

    public NewsAdapter(Context context, List<Source> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsname,newsdesc;
        Button btnselectednewsitems;
        public CheckBox chkSelected;
        ViewHolder(View itemView) {
            super(itemView);
            newsname = itemView.findViewById(R.id.newsname);
            newsdesc = itemView.findViewById(R.id.newsdesc);
            chkSelected = itemView.findViewById(R.id.chkselected);
            btnselectednewsitems = itemView.findViewById(R.id.btnselecteditems);
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        final int pos = position;
        holder.newsname.setText(sourceList.get(position).getName());
        holder.newsdesc.setText(sourceList.get(position).getDescription());
        holder.chkSelected.setChecked(sourceList.get(pos).isSelected());
        holder.chkSelected.setTag(sourceList.get(pos));
        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Source source = (Source) cb.getTag();
                source.setSelected(cb.isChecked());
                sourceList.get(pos).setSelected(cb.isChecked());
                Toast.makeText(view.getContext(),"Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public List<Source> getDataModelList()
    {
        return  sourceList;
    }

}
