package com.app.capstone.xcampus.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.capstone.xcampus.R;
import com.app.capstone.xcampus.dataObjects.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavle on 2017-04-18.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolderEntry> {

    private Context context;
    private List<Entry> data= new ArrayList<>();

    public EntryAdapter(Context context, List<Entry> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolderEntry onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_entry, parent, false);

        ViewHolderEntry viewHolderEntry = new ViewHolderEntry(view);

        return viewHolderEntry;
    }

    @Override
    public void onBindViewHolder(ViewHolderEntry holder, int position) {

        

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolderEntry extends RecyclerView.ViewHolder {

        private TextView title, auther, desc , timestamp;
        private Button view_more;


        public ViewHolderEntry(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.label_post_title);
            auther = (TextView)itemView.findViewById(R.id.label_post_auther);
            desc = (TextView)itemView.findViewById(R.id.label_post_desc);

            view_more = (Button)itemView.findViewById(R.id.button_view_post);

        }
    }


}
