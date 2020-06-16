package com.muslim.taskmadarsoft.Java.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.muslim.taskmadarsoft.Java.data.Infoo;
import com.muslim.taskmadarsoft.R;

public class Infoodapter extends ListAdapter <Infoo, Infoodapter.NoteHolder> {
    private OnItemClickListener listener;

    public
    Infoodapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Infoo> DIFF_CALLBACK = new DiffUtil.ItemCallback <Infoo>( ) {
        @Override
        public
        boolean areItemsTheSame(@NonNull Infoo oldItem, @NonNull Infoo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public
        boolean areContentsTheSame(@NonNull Infoo oldItem, @NonNull Infoo newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getJobtitle().equals(newItem.getJobtitle()) &&
                    oldItem.getAge() == newItem.getAge();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.item_info, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Infoo currentInfoo = getItem( position);
        holder.name.setText( currentInfoo.getName());
        holder.age.setText( currentInfoo.getAge());
        holder.job.setText(String.valueOf( currentInfoo.getJobtitle()));
        holder.gg.setText(String.valueOf( currentInfoo.getGender()));

    }

    public
    Infoo getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView name , age , job , gg;


        public NoteHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            job = itemView.findViewById(R.id.job);
            gg = itemView.findViewById(R.id.gender);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Infoo infoo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}