package com.example.renewgridai;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.renewgridai.databinding.DashboardBinding;
import com.example.renewgridai.helper.DataCaller;
import com.example.renewgridai.helper.WeatherCallback;
import com.example.renewgridai.model.WeatherData;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

public class DashboardFragment extends Fragment {
    private DashboardBinding binding;
    private MapView map = null;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude = 0;
    private double longitude = 0;
    private IMapController mapController = null;
    private  boolean hasInitialized = false;
    private boolean isFetchingWeatherData = false;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = DashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mapInitializer();

        Button showBottomSheetButton = view.findViewById(R.id.analytic_button);
        showBottomSheetButton.setOnClickListener(v -> {
            MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog();
            bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
        });
    }

    private void mapInitializer() {
        locationManager = (LocationManager) ContextCompat.getSystemService(this.getContext(), LocationManager.class);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (hasInitialized == false) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    mapController.setCenter(new GeoPoint(latitude, longitude));
                    hasInitialized = true;
                }
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Toast.makeText(getContext(), "GPS is disabled", Toast.LENGTH_SHORT).show();
            }
        };

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
        }

        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = (MapView) binding.map;
        map.setTileSource(TileSourceFactory.MAPNIK);
        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
        mRotationGestureOverlay.setEnabled(true);
        map.setMultiTouchControls(true);
        map.getOverlays().add(mRotationGestureOverlay);

        mapController = map.getController();
        mapController.setZoom(15.5);
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapController.setCenter(startPoint);

        map.addMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                if (isFetchingWeatherData) {
                    return false;
                }

                final WeatherData[] response = {new WeatherData()};
                DataCaller dataCaller = new DataCaller();
                dataCaller.getWeatherData(latitude, longitude, new WeatherCallback() {
                    @Override
                    public void onSuccess(WeatherData weatherData) {
                        Log.d(TAG, "onSuccess: " + weatherData);
                        weatherDataBinding(weatherData);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.e(TAG, "onFailure: " + errorMessage );
                    }
                });
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                return false;
            }
        });
    }

    private void weatherDataBinding(WeatherData weatherData) {
        TextView coordinateTv = (TextView) binding.countryData;
        coordinateTv.setText(latitude + ", " +longitude);

        if (weatherData != null &&
                weatherData.getHourly() != null &&
                weatherData.getDaily() != null)
        {
            TextView humidityTv = (TextView) binding.humidityData;
            TextView solarIrradianceTv = (TextView) binding.solarRadianceData;
            TextView windDirectionTv = (TextView) binding.windDirectionData;
            TextView windSpeedTv = (TextView) binding.windSpeedData;
            TextView sunPathTv = (TextView) binding.sunpathData;
            TextView cloudCoverageTv = (TextView) binding.cloudCoverageData;
            TextView daylightHourTv = (TextView) binding.daylightHourData;
            TextView temperatureTv = (TextView) binding.temperatureData;

            humidityTv.setText(weatherData.getHourly().getRelativeHumidity2m().get(0).toString());
            solarIrradianceTv.setText(weatherData.getDaily().getUvIndexMax().get(0).toString());
            windDirectionTv.setText((weatherData.getHourly().getWindDirection180m().get(0)));
            windSpeedTv.setText(weatherData.getHourly().getWindSpeed180m().get(0).toString());
            sunPathTv.setText(weatherData.getDaily().getUvIndexClearSkyMax().get(0).toString());
            cloudCoverageTv.setText(weatherData.getHourly().getVisibility().get(0).toString());
            daylightHourTv.setText(weatherData.getDaily().getDaylightDuration().get(0).toString());
            temperatureTv.setText(weatherData.getHourly().getTemperature180m().get(0).toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
