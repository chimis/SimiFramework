package io.simi.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 15/12/25 23:26
 * Updated time: 15/12/25 23:26
 */
public class SimiAdapter<V extends ViewDataBinding> extends RecyclerView.Adapter<SimiAdapter<V>.ViewHolder> {

    private Context context;
    private List<?> models;
    private int layoutResId;
    private OnBindViewHolder<V> onBindViewHolder;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public SimiAdapter(Context context, int layoutResId, List<?> models, OnBindViewHolder<V> onBindViewHolder) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.models = models;
        this.onBindViewHolder = onBindViewHolder;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false));
    }

    @Override
    public void onBindViewHolder(SimiAdapter<V>.ViewHolder holder, final int position) {
        onBindViewHolder.onBindViewHolder(holder.binding, position);
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public V binding;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(view, getAdapterPosition());
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
