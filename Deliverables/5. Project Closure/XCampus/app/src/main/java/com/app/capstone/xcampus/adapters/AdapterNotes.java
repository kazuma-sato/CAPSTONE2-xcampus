package com.app.capstone.xcampus.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.capstone.xcampus.dataObjects.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavle on 2017-04-20.
 */

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.ViewHolderNote> {

    private List<Note> data = new ArrayList<>();
    private Context context;

    public AdapterNotes(Context newContext, List<Note> newData) {
        data = newData;
        context = newContext;
    }

    @Override
    public AdapterNotes.ViewHolderNote onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdapterNotes.ViewHolderNote holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolderNote extends RecyclerView.ViewHolder {



        public ViewHolderNote(View itemView) {
            super(itemView);
        }


    }

}
