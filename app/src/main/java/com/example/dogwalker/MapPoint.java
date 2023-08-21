package com.example.dogwalker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naver.maps.map.overlay.InfoWindow;

public class MapPoint extends InfoWindow.DefaultViewAdapter {

    private final Context context;
    private final ViewGroup viewGroup;

    public MapPoint(@NonNull Context context, ViewGroup viewGroup) {
        super(context);
        this.context = context;
        this.viewGroup = viewGroup;
    }


    @NonNull
    @Override
    protected View getContentView(@NonNull InfoWindow infoWindow) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_map_point, viewGroup, false);
        TextView txtProfile = (TextView) view.findViewById(R.id.txtProfile);
        ImageView imagePoint = (ImageView) view.findViewById(R.id.imagePoint);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtAge = (TextView) view.findViewById(R.id.txtAge);
        TextView txtType = (TextView) view.findViewById(R.id.txtType);

        txtProfile.setText("반려인 프로필");
        imagePoint.setImageResource(R.drawable.dog);
        txtName.setText("몽이");
        txtAge.setText("7세");
        txtType.setText("리트리버");

        return view;
    }
}