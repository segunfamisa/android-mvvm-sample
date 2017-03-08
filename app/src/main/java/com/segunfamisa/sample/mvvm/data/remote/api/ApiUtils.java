package com.segunfamisa.sample.mvvm.data.remote.api;

import com.segunfamisa.sample.mvvm.BuildConfig;

public class ApiUtils {


    public static String getBaseUrl() {
        return "https://api.themoviedb.org/3/";
    }

    public static String getApiKey() {
        return BuildConfig.API_KEY;
    }
}
