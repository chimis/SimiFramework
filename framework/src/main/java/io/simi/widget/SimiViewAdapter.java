package io.simi.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import io.simi.utils.Debug;

/**
 * Code: 4.0.3(min-sdk) & 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/10 11:45
 * Updated time: 16/1/10 11:45
 */
public abstract class SimiViewAdapter extends FragmentPagerAdapter {

    private SparseArray<Object> fragments = new SparseArray<>();

    public SimiViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.get(position) == null) {
            try {
                fragments.put(position, fragments()[position].newInstance());
            } catch (Exception e) {
                Debug.e("getFragments params is error!");
            }
        }
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles()[position];
    }

    public abstract int titleColor();

    public abstract int indicatorColor();

    public abstract int[] titleSelectedColors();

    public abstract int[] icons();

    public abstract int[] selectedIcons();

    public abstract String[] titles();

    public abstract Class[] fragments();
}
