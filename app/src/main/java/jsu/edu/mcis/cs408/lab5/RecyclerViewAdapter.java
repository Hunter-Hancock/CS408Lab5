package jsu.edu.mcis.cs408.lab5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jsu.edu.mcis.cs408.lab5.databinding.MemoItemBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private MemoItemBinding binding;

    private MainActivity activity;
    private List<Memo> data;

    public RecyclerViewAdapter(MainActivity activity, List<Memo> data) {
        super();
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = MemoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.getRoot().setOnClickListener(activity.getItemClick());
        ViewHolder holder = new ViewHolder(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMemo(data.get(position));
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Memo getMemo(int position) {
        return data.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Memo memo;
        private TextView nameLabel;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public Memo getMemo() {
            return memo;
        }

        public void setMemo(Memo memo) {
            this.memo = memo;
        }

        public void bindData() {

            if (nameLabel == null) {
                nameLabel = itemView.findViewById(R.id.nameLabel);
            }
            StringBuilder sb = new StringBuilder("#");
            sb.append(memo.getId()).append(": ").append(memo.getName());
            nameLabel.setText(sb.toString());
        }

    }

}