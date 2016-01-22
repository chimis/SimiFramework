package io.simi.app;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.simi.utils.Utils;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/18 18:36
 * Updated time: 16/1/18 18:36
 */
public class SimiFramework {

    private static SimiFramework instance;
    private Context context;

    private SimiFramework(Context context) {
        this.context = context;
    }

    public static void initialize(Context context) {
        if (instance == null) {
            instance = new SimiFramework(context);
        }
        Utils.initialize(instance.context);
        Fresco.initialize(instance.context);
    }

}