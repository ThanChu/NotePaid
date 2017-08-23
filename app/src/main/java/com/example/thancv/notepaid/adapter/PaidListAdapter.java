package com.example.thancv.notepaid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thancv.notepaid.R;
import com.example.thancv.notepaid.model.PaidModel;
import com.example.thancv.notepaid.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThanCV on 8/22/2017.
 */

public class PaidListAdapter extends RecyclerView.Adapter<PaidListAdapter.PaidViewHolder> {

    private List<PaidModel> paidModels = new ArrayList<>();
    private OnLongClickListener onLongClickListener;

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public PaidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paid_layout, parent, false);
        return new PaidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaidViewHolder holder, int position) {
        final PaidModel model = paidModels.get(position);
        holder.tvNumericalOder.setText(String.valueOf(position + 1));
        holder.tvTitleName.setText(model.getTitleName());
        long moneyPaid = Long.parseLong(model.getMoneyPaid());
        holder.tvMoneyPaid.setText(TextUtils.formatText(moneyPaid));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongClickListener.onLongClick(model);
                return true;
            }
        });
    }

    public void loadData(List<PaidModel> list) {
        paidModels.clear();
        paidModels.addAll(list);
        notifyDataSetChanged();
    }

    public void addNewItem(PaidModel model) {
        paidModels.add(model);
        notifyDataSetChanged();
    }

    public void deleteItem(PaidModel model) {
        paidModels.remove(model);
        notifyDataSetChanged();
    }

    public void updateItem(int index, PaidModel model) {
        paidModels.set(index, model);
    }

    @Override
    public int getItemCount() {
        return paidModels.size();
    }

    class PaidViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumericalOder;

        private TextView tvTitleName;

        private TextView tvMoneyPaid;

        public PaidViewHolder(View itemView) {
            super(itemView);

            tvNumericalOder = itemView.findViewById(R.id.tv_numerical_order);
            tvTitleName = itemView.findViewById(R.id.tv_title_name);
            tvMoneyPaid = itemView.findViewById(R.id.tv_money_paid);
        }
    }

    public interface OnLongClickListener{
        void onLongClick(PaidModel paidModel);
    }
}
