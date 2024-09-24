package com.example.renewgridai.helper;

import android.util.Log;

import com.example.renewgridai.model.WeatherData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataCaller {
    String TAG = "DATA";

    public void PrintTest(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherData> call = service.getWeatherForecast(
                -37.814,
                144.9633,
                "relative_humidity_2m,precipitation,visibility,temperature_180m,wind_speed_180m,wind_direction_180m",
                "uv_index_max,uv_index_clear_sky_max",
                "Australia/Sydney"
        );

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherResponse = response.body();
                    if (weatherResponse != null) {
                        Log.d(TAG, "onResponse: " + weatherResponse.toString());
                    }
                } else {
                    Log.e("API_CALL", "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.e("API_CALL", "Network failure: " + t.getMessage());

            }
        });
    }
}
