package com.example.mapa;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public CustomInfoWindowAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.customwindow, null);

        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);

        switch (marker.getId()) {
            case "m0":
                img.setImageResource(R.drawable.m0);
                break;
            case "m1":
                img.setImageResource(R.drawable.m1);
                break;
            case "m2":
                img.setImageResource(R.drawable.m2);
                break;
        }

        tvTitle.setText(marker.getTitle());
        tvSubTitle.setText(marker.getSnippet());

        return view;
    }
}