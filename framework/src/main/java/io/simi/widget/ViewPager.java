package io.simi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;

import io.simi.R;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 2015/12/25 11:27
 * Updated time: 2015/12/25 11:27
 */
public class ViewPager extends android.support.v4.view.ViewPager {

    private boolean isFlexible = true;

    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ViewPager);
        isFlexible = attributes.getBoolean(R.styleable.ViewPager_flexible, true);
        attributes.recycle();
    }

    public boolean isFlexible() {
        return isFlexible;
    }

    public void setFlexible(boolean isFlexible) {
        this.isFlexible = isFlexible;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isFlexible && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isFlexible && super.onInterceptTouchEvent(ev);
    }
}