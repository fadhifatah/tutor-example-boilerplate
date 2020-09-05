package com.example.ui.main.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.data.model.Reminder;
import com.example.databinding.ItemReminderBinding;
import com.example.injection.ApplicationContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.ViewHolder> {

    private List<Reminder> dataList;

    @Inject
    public ReminderListAdapter(@ApplicationContext Context context) {
        dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemReminderBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Reminder reminder = dataList.get(position);

        holder.mBinding.tvDate.setText(reminder.getCreatedAt());
        holder.mBinding.tvTitle.setText(reminder.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemReminderBinding mBinding;

        public ViewHolder(ItemReminderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public void setDataList(final List<Reminder> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
}
