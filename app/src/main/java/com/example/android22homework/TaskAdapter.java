package com.example.android22homework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TaskAdapter extends RecyclerView.Adapter <TaskAdapter.TaskViewHolder> {

    ArrayList<TaskModel> list = new ArrayList<>();
    private Timer timer;
    private ArrayList<TaskModel> notesSource;
    TaskModel model;
    private ItemOnClickListener listener;

    public void setListener(ItemOnClickListener listener){
        this.listener = listener;
    }

    public void addTask(TaskModel model){
        list.add(model);
        notesSource = list;
        notifyDataSetChanged();
    }



    @NonNull
    @NotNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent,false);
       return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        TextView txtDate;
        public TaskViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.item_txt_title);
            txtDescription = itemView.findViewById(R.id.item_txt_description);
            txtDate = itemView.findViewById(R.id.item_txt_date);
        }

        public void bind(TaskModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setItemOnClickListener(getAdapterPosition(), list);
                }
            });
            txtTitle.setText(model.getTitle());
            txtDescription.setText(model.getDescription());
            txtDate.setText(model.getDate());
        }
    }

    public void  searchNotes(final String searchKeyWord){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (searchKeyWord.trim().isEmpty()){
                    list = notesSource;
                }else {
                    ArrayList<TaskModel> temp = new ArrayList<>();
                    for (TaskModel note : notesSource){
                        if (note.getTitle().toLowerCase().contains(searchKeyWord.toLowerCase())){
                            temp.add(note);
                        }
                    }
                    list = temp;
                }
            }
        },500);

    }
    public  void  cancelTimer(){
        if (timer != null){
            timer.cancel();
        }
    }
}

