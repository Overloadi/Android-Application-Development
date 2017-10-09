package fi.jokufirma.loaddatafromjsonanddisplaymarkers;


import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private JSONArray locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
        mMap.setOnInfoWindowClickListener(this);
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute("http://ptm.fi/materials/golfcourses/golf_courses.json");
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    }

    // Create a custom window class so all the info can be displayed when the marker is clicked
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View mWindow;
        private final View mContents;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.info_window,null);
            mContents = getLayoutInflater().inflate(R.layout.info_window,null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker,mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            render(marker,mContents);
            return mContents;
        }

        private void render(Marker marker, View view) {
            String title = marker.getTitle();
            TextView titleView = ((TextView) view.findViewById(R.id.textTitle));
            titleView.setText(title);

            String snippet = marker.getSnippet();
            TextView snippetView = ((TextView) view.findViewById(R.id.textDetails));
            snippetView.setText(snippet);
        }
    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
            try {
                // Get the JSON data from an URL
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                json = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }
            return json;
        }

        protected void onPostExecute(JSONObject json) {
            double lat;
            double lng;
            String courseType;
            float hue;
            try {
                locations = json.getJSONArray("courses");
                for (int i=0;i < locations.length(); i++) {
                    JSONObject locs = locations.getJSONObject(i);
                    // Add markers to the map
                    lat = locs.getDouble("lat");
                    lng = locs.getDouble("lng");
                    courseType = locs.getString("type");
                    // Change the marker color based on type
                    switch (courseType) {
                        case "Kulta":
                            hue = 100;
                            break;
                        case "Etu":
                            hue = 175;
                            break;
                        case "Kulta/Etu":
                            hue = 250;
                            break;
                        case "?":
                            hue = 340;
                            break;
                        default:
                            hue = 10;
                            break;
                    }
                    LatLng coords = new LatLng(lat,lng);
                    mMap.addMarker(new MarkerOptions()
                            .position(coords)
                            .icon(BitmapDescriptorFactory
                                .defaultMarker(hue))
                            .title(locs.getString("course"))
                            .snippet(locs.getString("address") + "\n" + locs.getString("phone")  + "\n" + locs.getString("email")  + "\n" + locs.getString("web"))

                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
