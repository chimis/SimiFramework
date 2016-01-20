package io.simi.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.simi.R;
import io.simi.utils.Utils;

/**
 * Code: 4.0.3(min-sdk) & 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/10 12:22
 * Updated time: 16/1/10 12:22
 */
public class ViewController extends RelativeLayout {

    protected ViewPager mViewPager;
    protected TabLayout mTabLayout;
    protected View mTabShadowLineView;
    protected View mTabColorLineView;
    protected SimiViewAdapter mViewAdapter;

    private int index = 0;

    public ViewController(Context context) {
        super(context);
        initView(context, null);
    }

    public ViewController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ViewController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ViewController(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        Utils.initialize(getContext());
        int mTabHeight = Utils.dp2px(52);
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ViewController);
            mTabHeight = attributes.getDimensionPixelOffset(R.styleable.ViewController_tabHeight, 52);
            attributes.recycle();
        }
        LayoutParams params = new LayoutParams(-1, mTabHeight);
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        mTabLayout = new TabLayout(getContext());
        mTabLayout.setId(Utils.generateViewId());
        mTabLayout.setLayoutParams(params);
        mTabLayout.setSelectedTabIndicatorColor(0x00FFFFFF);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        addView(mTabLayout);
        params = new LayoutParams(-1, -1);
        params.addRule(ABOVE, mTabLayout.getId());
        mViewPager = new ViewPager(getContext());
        mViewPager.setId(Utils.generateViewId());
        mViewPager.setLayoutParams(params);
        mViewPager.setFlexible(false);
        addView(mViewPager);
        params = new LayoutParams(-1, Utils.dp2px(0.6F));
        params.addRule(ABOVE, mTabLayout.getId());
        mTabColorLineView = new View(getContext());
        mTabColorLineView.setLayoutParams(params);
        mTabColorLineView.setBackgroundColor(0x44CCCCCC);
        addView(mTabColorLineView);
        params = new LayoutParams(-1, Utils.dp2px(4F));
        params.addRule(ABOVE, mTabLayout.getId());
        mTabShadowLineView = new View(getContext());
        mTabShadowLineView.setLayoutParams(params);
        mTabShadowLineView.setBackgroundResource(R.drawable.shadow);
        addView(mTabShadowLineView);
    }

    public void setAdapter(SimiViewAdapter adapter) {
        this.mViewAdapter = adapter;
        update();
    }

    private void update() {
        mViewPager.setAdapter(mViewAdapter);
        mViewPager.setOffscreenPageLimit(mViewAdapter.getCount());
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (index == position) {
                    return;
                }
                TabLayout.Tab tab = mTabLayout.getTabAt(position);
                if (tab != null && tab.getCustomView() != null) {
                    TabView tabView = (TabView) tab.getCustomView();
                    tabView.setIcon(mViewAdapter.selectedIcons()[position]);
                    tabView.setTextColor(mViewAdapter.titleSelectedColors()[position]);
                }
                tab = mTabLayout.getTabAt(index);
                if (tab != null && tab.getCustomView() != null) {
                    TabView tabView = (TabView) tab.getCustomView();
                    tabView.setIcon(mViewAdapter.icons()[index]);
                    tabView.setTextColor(mViewAdapter.titleColor());
                }
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                TabView tabView = new TabView(getContext(), i == 0 ? mViewAdapter.selectedIcons()[i] : mViewAdapter.icons()[i], mViewAdapter.getPageTitle(i).toString());
                if (i == 0) {
                    tabView.setTextColor(mViewAdapter.titleSelectedColors()[i]);
                }
                tab.setCustomView(tabView);
            }
        }
        mViewPager.setCurrentItem(index);
    }

    private class TabView extends RelativeLayout {

        private ImageView imageView;
        private TextView textView;

        public TabView(Context context, int resId, String text) {
            super(context);
            setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            LayoutParams params = new LayoutParams(-2, -2);
            params.addRule(CENTER_HORIZONTAL, TRUE);
            params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
            params.setMargins(0, 0, 0, Utils.dp2px(2));
            textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setId(Utils.generateViewId());
            textView.setText(text);
            textView.setTextColor(mViewAdapter.titleColor());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            addView(textView);
            params = new LayoutParams(-2, -2);
            params.addRule(CENTER_HORIZONTAL, TRUE);
            params.addRule(ABOVE, textView.getId());
            params.setMargins(0, Utils.dp2px(4), 0, 0);
            imageView = new ImageView(context);
            imageView.setLayoutParams(params);
            imageView.setImageResource(resId);
            imageView.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            addView(imageView);
        }

        public void setIcon(int resId) {
            imageView.setImageResource(resId);
        }

        public void setTextColor(int color) {
            textView.setTextColor(color);
        }
    }
}
