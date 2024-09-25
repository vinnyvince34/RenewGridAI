package com.example.renewgridai.helper;

import com.example.renewgridai.model.WeatherData;

public interface WeatherCallback {
    void onSuccess(WeatherData weatherData);
    void onFailure(String errorMessage);
}