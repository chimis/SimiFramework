package io.simi.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chimis on 2016-16/8/16 15:07.
 * Email: chimis@foxmail.com selly.ge@outlook.com
 * Description: 消息提示
 */

public final class Message {

    public static final int LENGTH_SHORT = Snackbar.LENGTH_SHORT;
    public static final int LENGTH_LONG = Snackbar.LENGTH_LONG;
    public static final int LENGTH_INDEFINITE = Snackbar.LENGTH_INDEFINITE;

    /**
     * 普通消息
     * @param parent 根视图
     * @param message 消息
     * @return 句柄
     */
    public static Snackbar show(View parent, String message) {
        return show(parent, message, -1, -1);
    }

    /**
     * 提示消息
     * @param parent 根视图
     * @param message 消息
     * @return 句柄
     */
    public static Snackbar notice(View parent, String message) {
        return show(parent, message, -1, 0xFF009EFC);
    }

    /**
     * 成功消息
     * @param parent 根视图
     * @param message 消息
     * @return 句柄
     */
    public static Snackbar success(View parent, String message) {
        return show(parent, message, -1, 0xFF00B58A);
    }

    /**
     * 失败消息
     * @param parent 根视图
     * @param message 消息
     * @return 句柄
     */
    public static Snackbar error(View parent, String message) {
        return show(parent, message, -1, 0xFFFF3366);
    }

    /**
     * 自定义消息
     * @param parent 根视图
     * @param message 消息
     * @param type 显示时间类型
     * @param color 颜色
     * @return 句柄
     */
    public static Snackbar show(View parent, String message, int type, int color) {
        Snackbar snackbar = Snackbar.make(parent, message, type == -1 ? LENGTH_SHORT : type);
        Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) snackbar.getView();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = Utils.dp2px(54);
        view.setLayoutParams(params);
        if (color != -1) {
            view.setBackgroundColor(color);
        }
        snackbar.show();
        return snackbar;
    }

}
