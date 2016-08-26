package io.simi.widget;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chimis@foxmail.com (CHIMIS 葛相池)
 */

public class SimiGroupAdapter<V extends ViewDataBinding> extends android.support.v7.widget.RecyclerView.Adapter<RecyclerViewHolder> {

    private List<?> data = new ArrayList<>();
    private SparseIntArray layoutResIds = new SparseIntArray();
    private ViewTypeArray viewTypeArray;
    private Context context;
    private OnBindGroupViewHolder<V> onBindViewHolder;

    private RecyclerView.OnItemClickListener onItemClickListener;
    private RecyclerView.OnItemLongClickListener onItemLongClickListener;

    public SimiGroupAdapter(Context context, List<?> data, ViewTypeArray viewTypeArray, OnBindGroupViewHolder<V> onBindViewHolder) {
        this.context = context;
        this.viewTypeArray = viewTypeArray;
        this.data = data;
        this.onBindViewHolder = onBindViewHolder;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = new RecyclerViewHolder(LayoutInflater.from(context).inflate(layoutResIds.get(viewType), parent, false));
        holder.setOnItemClickListener(onItemClickListener);
        holder.setOnItemLongClickListener(onItemLongClickListener);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        ViewType viewType = viewTypeArray.getViewType(position);
        int viewTypeCode = 0;
        switch (viewType.getType()) {
            case SINGLE:
                viewTypeCode = 10000 + viewType.getPosition();
                break;
            case LOOP:
                viewTypeCode = 20000 + viewType.getPosition();
                break;
        }
        layoutResIds.put(viewTypeCode, viewType.getLayoutResId());
        return viewTypeCode;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        onBindViewHolder.onBindGroupViewHolder((V) holder.getBinding(), position, viewTypeArray.getViewType(position).getLayoutResId());
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