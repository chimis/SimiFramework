package io.simi.widget;

import android.databinding.ViewDataBinding;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 15/12/25 23:25
 * Updated time: 15/12/25 23:25
 */
public interface OnBindViewHolder<T extends ViewDataBinding> {
    void onBindViewHolder(T binding, int position);
}
