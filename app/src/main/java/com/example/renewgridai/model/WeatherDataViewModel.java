package com.example.renewgridai.model;

import android.net.DnsResolver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.renewgridai.helper.DataCaller;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDataViewModel extends ViewModel {
    private MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();
    private DataCaller dataCaller;
    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void fetchWeatherData(double latitude, double longitude) {
        Call<WeatherData> call = (Call<WeatherData>) dataCaller.getWeatherData(latitude, longitude);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherResponse = response.body();
                    if (weatherResponse != null) {
                        weatherData.postValue(weatherResponse); // Update LiveData
                    }
                } else {
                    // Handle response error
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
