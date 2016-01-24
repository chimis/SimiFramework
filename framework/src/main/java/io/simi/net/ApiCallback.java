package io.simi.net;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        onSuccess(response, retrofit);
        onCompleted();
    }

    @Override
    public void onFailure(Throwable t) {
        onFailed(t);
        onCompleted();
    }

    public abstract void onSuccess(Response<T> response, Retrofit retrofit);

    public abstract void onFailed(Throwable t);

    public abstract void onCompleted();

}