package ca.bcit.googlemap;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import ca.bcit.googlemap.data.Location;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @NonNull
    private List<Double> x;
    private List<Double> y;

    @NonNull
    private ArrayAdapter<String> namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        downloadData("http://opendata.newwestcity.ca/downloads/election-voting-sites-school-board-bi-election-201/2016_ELECTION_LOCATIONS.json");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void addPoint (final double latitude, final double longitude, final String title)
    {
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location).title(title));
        float zoomLevel = 12.5f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));
    }

    private void downloadData(@NonNull final String url)
    {
        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(final Exception  ex,
                                            final JsonObject json)
                    {
                        if(ex != null)
                        {

                        }

                        if(json != null)
                        {
                            parseJSON(json);
                        }
                    }
                });
    }

    private void parseJSON(final JsonObject json)
    {
        final Gson gson;
        final Location location;

        gson = new Gson();
        location = gson.fromJson(json, Location.class);
       // x.clear();
       // y.clear();

        for(Location.Feature feature : location.getFeatures())
        {
            final Location.Feature.Geometry geo;
            final Location.Feature.Properties properties;
            final String     name;

            geo =  feature.getGeometry();
            properties = feature.getProperties();
            double[] a = geo.getFirstCoordinates();
            String name_ = properties.getFacilityName();

            double X = a[1];

            addPoint( a[1],a[0],name_);
        }


    }

}
