package io.simi.app;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/10 12:02
 * Updated time: 16/1/10 12:02
 */
public abstract class SimiFragment<T extends ViewDataBinding> extends Fragment {

    /**
     * ViewDataBinding实体
     * 用于子类调用设置数据或者获得控件
     */
    protected T binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(getLayoutResId(), container, false);
        binding = DataBindingUtil.bind(mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateView(savedInstanceState);
    }

    /**
     * 展示消息
     * @param message 消息体
     * @param type 时间
     */
    protected void showMessage(String message, int type) {
        Snackbar.make(binding.getRoot(), message, type).show();
    }

    public T getBinding() {
        return binding;
    }

    protected abstract void onCreateView(Bundle savedInstanceState);
    protected abstract int getLayoutResId();
}
