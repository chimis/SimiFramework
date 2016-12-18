package io.simi.app;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.simi.utils.Message;
import io.simi.utils.Utils;

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
        onCreateBefore();
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
    protected void onCreateBefore() {
    }


    /**
     *
     */
    protected void onCreateViewBefore() {
    }


    protected void showMessage(String message) {
        showMessage(message, Snackbar.LENGTH_SHORT);
    }

    protected void showErrorMessage(String message) {
        showMessage(message, -1, 0xFFF3456D);
    }

    protected void showSuccessMessage(String message) {
        showMessage(message, -1, 0xFF00B58A);
    }

    protected void showNormalMessage(String message) {
        showMessage(message, -1, 0xFF009EFC);
    }

    protected void showLongMessage(String message) {
        showMessage(message, Snackbar.LENGTH_LONG);
    }

    /**
     * 展示消息
     *
     * @param message 消息体
     * @param type    时间
     */
    public void showMessage(String message, int type) {
        showMessage(message, type, -1);
    }

    /**
     * 展示消息
     *
     * @param message 消息体
     * @param type    时间
     * @param color   颜色
     */
    public void showMessage(String message, int type, int color) {
        Message.show(binding.getRoot(), message, type, color);
    }

}
