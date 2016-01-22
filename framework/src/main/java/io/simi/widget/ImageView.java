package io.simi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import io.simi.R;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 2015/12/25 11:27
 * Updated time: 2015/12/25 11:27
 */
public class ImageView extends SimpleDraweeView {

    private String imageURL;

    public ImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ImageView);
        String imageURL = attributes.getString(R.styleable.ImageView_imageURL);
        if (!TextUtils.isEmpty(imageURL)) {
            setImageURL(imageURL);
        }
        attributes.recycle();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        if (!TextUtils.isEmpty(imageURL)) {
            setImageURI(Uri.parse(imageURL));
        }
    }

}
