package io.simi.widget;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

public class RecyclerViewHolder<V extends ViewDataBinding> extends android.support.v7.widget.RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private V binding;
    private RecyclerView.OnItemClickListener onItemClickListener;
    private RecyclerView.OnItemLongClickListener onItemLongClickListener;

    RecyclerViewHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.binding = DataBindingUtil.bind(itemView);
    }

    V getBinding() {
        return binding;
    }

    void setOnItemClickListener(RecyclerView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    void setOnItemLongClickListener(RecyclerView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(binding, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(binding, getAdapterPosition());
            return true;
        }
        return false;
    }
}
