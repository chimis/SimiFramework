package io.simi.net;

import android.text.TextUtils;

import io.simi.utils.Debug;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 2015/12/24 17:22
 * Updated time: 2015/12/24 17:22
 */
public class ApiService {

    private static Retrofit retrofit;
    private static Object api;
    private static String baseUrl;

    private ApiService() {
    }

    /**
     * 初始化方法
     *
     * @param host 主机地址 示例：http://simi.io
     */
    public static void initialize(String host) {
        initialize(host, null);
    }

    /**
     * 初始化方法
     *
     * @param host    主机地址 示例：http://simi.io
     * @param factory 数据转换工厂 默认使用Gson
     */
    public static void initialize(String host, Converter.Factory factory) {
        if (TextUtils.isEmpty(host)) {
            Debug.w("ApiServiceException: HOST NULL");
            return;
        }
        if (factory == null && host.equals(baseUrl)) {
            Debug.w("ApiServiceException: IS EXIST");
            return;
        }
        if (factory == null) {
            factory = GsonConverterFactory.create();
        }
        baseUrl = host;
        retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(factory).build();
    }

    /**
     * Api访问接口实例化
     *
     * @param tClass 自定义的Api接口 示例：MyApi.class
     * @param <T>    Api接口实例
     * @return Api接口实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T api(Class<T> tClass) {
        if (api == null) {
            api = (T) retrofit.create(tClass);
        }
        return (T) api;
    }
}
