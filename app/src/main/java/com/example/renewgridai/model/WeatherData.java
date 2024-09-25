package com.example.renewgridai.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {
    private double latitude;
    private double longitude;
    private double generationtimeMs;
    private int utcOffsetSeconds;
    private String timezone;
    private String timezoneAbbreviation;
    private double elevation;
    private HourlyUnits hourlyUnits;
    private Hourly hourly;
    private DailyUnits dailyUnits;
    private Daily daily;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtimeMs() {
        return generationtimeMs;
    }

    public void setGenerationtimeMs(double generationtimeMs) {
        this.generationtimeMs = generationtimeMs;
    }

    public int getUtcOffsetSeconds() {
        return utcOffsetSeconds;
    }

    public void setUtcOffsetSeconds(int utcOffsetSeconds) {
        this.utcOffsetSeconds = utcOffsetSeconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public DailyUnits getDailyUnits() {
        return dailyUnits;
    }

    public void setDailyUnits(DailyUnits dailyUnits) {
        this.dailyUnits = dailyUnits;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public class HourlyUnits {
        private String time;
        @SerializedName("relative_humidity_2m")
        private String relativeHumidity2m;
        private String precipitation;
        private String visibility;
        @SerializedName("temperature_180m")
        private String temperature180m;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRelativeHumidity2m() {
            return relativeHumidity2m;
        }

        public void setRelativeHumidity2m(String relativeHumidity2m) {
            this.relativeHumidity2m = relativeHumidity2m;
        }

        public String getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getTemperature180m() {
            return temperature180m;
        }

        public void setTemperature180m(String temperature180m) {
            this.temperature180m = temperature180m;
        }
    }

    public class Hourly {
        private List<String> time;
        @SerializedName("relative_humidity_2m")
        private List<Double> relativeHumidity2m;
        private List<Double> precipitation;
        private List<Double> visibility;
        @SerializedName("temperature_180m")
        private List<Double> temperature180m;
        @SerializedName("wind_speed_180m")
        private List<Double> windSpeed180m;
        @SerializedName("wind_direction_180m")
        private List<String> windDirection180m;

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Double> getRelativeHumidity2m() {
            return relativeHumidity2m;
        }

        public void setRelativeHumidity2m(List<Double> relativeHumidity2m) {
            this.relativeHumidity2m = relativeHumidity2m;
        }

        public List<Double> getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(List<Double> precipitation) {
            this.precipitation = precipitation;
        }

        public List<Double> getVisibility() {
            return visibility;
        }

        public void setVisibility(List<Double> visibility) {
            this.visibility = visibility;
        }

        public List<Double> getTemperature180m() {
            return temperature180m;
        }

        public void setTemperature180m(List<Double> temperature180m) {
            this.temperature180m = temperature180m;
        }

        public List<Double> getWindSpeed180m() {
            return windSpeed180m;
        }

        public void setWindSpeed180m(List<Double> windSpeed180m) {
            this.windSpeed180m = windSpeed180m;
        }

        public List<String> getWindDirection180m() {
            return windDirection180m;
        }

        public void setWindDirection180m(List<String> windDirection180m) {
            this.windDirection180m = windDirection180m;
        }
    }

    public class DailyUnits {
        private String time;
        @SerializedName("uv_index_max")
        private String uvIndexMax;
        @SerializedName("uv_index_clear_sky_max")
        private String uvIndexClearSkyMax;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUvIndexMax() {
            return uvIndexMax;
        }

        public void setUvIndexMax(String uvIndexMax) {
            this.uvIndexMax = uvIndexMax;
        }

        public String getUvIndexClearSkyMax() {
            return uvIndexClearSkyMax;
        }

        public void setUvIndexClearSkyMax(String uvIndexClearSkyMax) {
            this.uvIndexClearSkyMax = uvIndexClearSkyMax;
        }
    }

    public class Daily {
        private List<String> time;
        @SerializedName("uv_index_max")
        private List<Double> uvIndexMax;
        @SerializedName("uv_index_clear_sky_max")
        private List<Double> uvIndexClearSkyMax;

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Double> getUvIndexMax() {
            return uvIndexMax;
        }

        public void setUvIndexMax(List<Double> uvIndexMax) {
            this.uvIndexMax = uvIndexMax;
        }

        public List<Double> getUvIndexClearSkyMax() {
            return uvIndexClearSkyMax;
        }

        public void setUvIndexClearSkyMax(List<Double> uvIndexClearSkyMax) {
            this.uvIndexClearSkyMax = uvIndexClearSkyMax;
        }
    }
}