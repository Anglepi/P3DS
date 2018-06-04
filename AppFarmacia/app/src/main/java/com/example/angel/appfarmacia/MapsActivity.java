package com.example.angel.appfarmacia;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle b = this.getIntent().getExtras();
        usuario = (Usuario) b.getSerializable("USUARIO");
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

        LatLng etsiit = new LatLng(37.197057, -3.624555);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(etsiit));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));


        getFarmacias();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, FarmaciaActivity.class);
                Bundle b = new Bundle();
                b.putString("NOMBRE", marker.getTitle());
                b.putSerializable("USUARIO", usuario);
                intent.putExtras(b);
                startActivity(intent);
                return true;
            }
        });

    }

    private void getFarmacias(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip_servidor) + getString(R.string.url_farmacias);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("OBTENIDO DE LAS FARMACIAS: ");
                System.out.println(response.toString());
                try {
                    JSONArray farmacias = response.getJSONArray("farmacias");
                    setMarkers(farmacias);
                } catch (Exception e){
                    System.out.println("Error al coger las farmacias del JSON");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("HA FALLADO LA OBTENCION DE MAPS");
            }
        });
        queue.add(jsonobj);
    }

    private void setMarkers(JSONArray farmacias){
        for(int i=0; i<farmacias.length() ; i++){
            try {
                mMap.addMarker(new MarkerOptions().position(new LatLng(farmacias.getJSONObject(i).getDouble("latitud"), farmacias.getJSONObject(i).getDouble("longitud"))).title(farmacias.getJSONObject(i).getString("Nombre")));
            } catch (Exception e){
                System.out.println("HA FALLADO LA OBTENCION DE DATOS DE MAPS->MARKER FARMACIA");
            }
        }
    }
}
