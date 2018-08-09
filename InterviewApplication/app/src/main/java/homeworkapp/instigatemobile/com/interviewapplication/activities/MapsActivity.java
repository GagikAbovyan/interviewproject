package homeworkapp.instigatemobile.com.interviewapplication.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import homeworkapp.instigatemobile.com.interviewapplication.R;
import homeworkapp.instigatemobile.com.interviewapplication.providers.DataProvider;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getIntent() != null) {
            final int data = getIntent().getIntExtra(DataProvider.getMyKey(), 0);
            latitude = Double.parseDouble(DataProvider.getList().get(data).getLocation().getCoordinates().getLatitude());
            longitude = Double.parseDouble(DataProvider.getList().get(data).getLocation().getCoordinates().getLongitude());
            name = DataProvider.getList().get(data).getName().getFirst();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng latlng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latlng).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }
}
