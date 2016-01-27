package io.simi.net;

import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Response<T> response) {
        onSuccess(response);
        onCompleted();
    }

    @Override
    public void onFailure(Throwable t) {
        onFailed(t);
        onCompleted();
    }

    public abstract void onSuccess(Response<T> response);

    public abstract void onFailed(Throwable t);

    public abstract void onCompleted();

}