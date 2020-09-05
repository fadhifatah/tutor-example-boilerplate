package com.example.data.remote;

import com.example.data.request.LoginRequest;
import com.example.data.response.LoginResponse;
import com.example.data.response.ReminderResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icapps.niddler.core.AndroidNiddler;
import com.icapps.niddler.interceptor.okhttp.NiddlerOkHttpInterceptor;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SimpleService {

    String ENDPOINT = "https://6b2b992c-377b-4fd2-a721-e9d66eb98bed.mock.pstmn.io/";

    @POST("auth/login")
    Observable<LoginResponse> doLogin(@Body LoginRequest request);

    @GET("data/list")
    Observable<ReminderResponse> getDataList(@Query("type") String type);

    class Creator {

        public static SimpleService newSimpleService(AndroidNiddler androidNiddler) {
            NiddlerOkHttpInterceptor interceptor = new NiddlerOkHttpInterceptor(androidNiddler,
                    "Android Boilerplate");

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(SimpleService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(SimpleService.class);
        }

        public static SimpleService newSimpleService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SimpleService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(SimpleService.class);
        }
    }
}
