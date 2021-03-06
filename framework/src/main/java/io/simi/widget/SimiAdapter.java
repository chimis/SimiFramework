package io.simi.widget;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chimis@foxmail.com (CHIMIS 葛相池)
 */

public class SimiAdapter<V extends ViewDataBinding> extends android.support.v7.widget.RecyclerView.Adapter<RecyclerViewHolder> {

    private List<?> data = new ArrayList<>();
    private int layoutResId;
    private Context context;
    private OnBindViewHolder<V> onBindViewHolder;

    private RecyclerView.OnItemClickListener onItemClickListener;
    private RecyclerView.OnItemLongClickListener onItemLongClickListener;

    public SimiAdapter(Context context, int layoutResId, List<?> data, OnBindViewHolder<V> onBindViewHolder) {
        this.context = context;
        this.data = data;
        this.layoutResId = layoutResId;
        this.onBindViewHolder = onBindViewHolder;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = new RecyclerViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false));
        holder.setOnItemClickListener(onItemClickListener);
        holder.setOnItemLongClickListener(onItemLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        onBindViewHolder.onBindViewHolder((V)holder.getBinding(), position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(RecyclerView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RecyclerView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
