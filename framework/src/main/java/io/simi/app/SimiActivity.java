package io.simi.app;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 2015/12/24 16:49
 * Updated time: 2015/12/24 16:49
 */
public abstract class SimiActivity<T extends ViewDataBinding> extends AppCompatActivity {

    /**
     * ViewDataBinding实体
     * 用于子类调用设置数据或者获得控件
     */
    protected T binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimiFramework.initialize(this);
        onCreateViewBefore();
        onCreateView(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    public T getBinding() {
        return binding;
    }

    /**
     * #必须实现# 子类以此方法替代onCreate(Bundle savedInstanceState)
     */
    protected abstract void onCreateView(Bundle savedInstanceState);


    /**
     *
     */
    protected void onCreateViewBefore() {}

    /**
     * 展示消息
     * @param message 消息体
     * @param type 时间
     */
    protected void showMessage(String message, int type) {
        Snackbar.make(binding.getRoot(), message, type).show();
    }

}
