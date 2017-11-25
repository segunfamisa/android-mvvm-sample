package com.segunfamisa.sample.mvvm.ui.movies;


import android.content.Context;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.utils.ErrorResolver;

import java.io.IOException;

import retrofit2.HttpException;

public class MoviesErrorResolver implements ErrorResolver {

    private final Context context;

    public MoviesErrorResolver(Context context) {
        this.context = context;
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            // We had non-2XX http error
            return context.getString(R.string.error_msg_server);
        }
        if (throwable instanceof IOException) {
            // A network or conversion error happened
            return context.getString(R.string.error_msg_network);
        } else {
            // Generic error handling
            return context.getString(R.string.error_msg_network_generic);
        }
    }
}
