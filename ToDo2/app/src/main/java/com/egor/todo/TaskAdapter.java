package com.egor.todo;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private String[] titles;
    private String[] descriptions;
    private int[] _id;
    private int[] done;

    public TaskAdapter(String[] titles, String[] descriptions, int[] _id, int[] done){
        this.titles = titles;
        this.descriptions = descriptions;
        this._id = _id;
        this.done = done;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_task, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView title = (TextView) cardView.findViewById(R.id.title_text);
        title.setText(titles[position]);
        TextView description = (TextView) cardView.findViewById(R.id.description_text);
        description.setText(descriptions[position]);
        ImageButton id_task = (ImageButton) cardView.findViewById(R.id.btnEdit);
        id_task.setContentDescription(Integer.toString(_id[position]));
        CheckBox id_task2 = (CheckBox) cardView.findViewById(R.id.chBox);
        if(done[position] == 1){
            id_task2.setChecked(true);
        }
        id_task2.setContentDescription(Integer.toString(_id[position]));
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }

    }
}
