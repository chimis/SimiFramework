package io.simi.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import io.simi.utils.Debug;

/**
 * Code: 4.0.3(min-sdk) & 6.0(target-sdk)
 * Creator: chimis
 * Created time: 15/12/25 23:44
 * Updated time: 15/12/25 23:44
 */
public class RecyclerView extends android.support.v7.widget.RecyclerView {

    private OnLackDataListener onLackDataListener;
    private OnScrollListener onScrollListener;

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setLayoutManager(new LinearLayoutManager(getContext()));
        super.setOnScrollListener(onScrollDefaultListener);
    }

    public void setOnLackDataListener(OnLackDataListener onLackDataListener) {
        this.onLackDataListener = onLackDataListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof SimiAdapter)) {
            Debug.w("This adapter isn't a SimiAdapter and maybe no item click events.");
        }
        super.setAdapter(adapter);
    }

    private OnScrollListener onScrollDefaultListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(android.support.v7.widget.RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(recyclerView, newState);
            }
        }

        @Override
        public void onScrolled(android.support.v7.widget.RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (onLackDataListener != null) {
                try {
                    LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();
                    if (manager.findLastVisibleItemPosition() >= manager.getItemCount() - onLackDataListener.minNumber() && dy > 0 && !onLackDataListener.isLoading()) {
                        onLackDataListener.onLackData();
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("this RecyclerView's LayoutManager isn't a LinearLayoutManager.");
                }
            }
            if (onScrollListener != null) {
                onScrollListener.onScrolled(recyclerView, dx, dy);
            }
        }
    };

    public interface OnLackDataListener {
        public void onLackData();

        public boolean isLoading();

        public int minNumber();
    }
}
