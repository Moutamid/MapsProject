package com.moutamid.mapsproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private View rootView;
    private Utils utils = new Utils();

//    private LinearLayout liveLocationLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView nameTextView = rootView.findViewById(R.id.name_text_view_home);
        nameTextView.setText(utils.getStoredString(getActivity(), "usernameStr"));

        rootView.findViewById(R.id.live_location_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class).putExtra("live", true));
            }
        });

        rootView.findViewById(R.id.reports_layout_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SubmitReportsActivity.class));
            }
        });

        rootView.findViewById(R.id.view_reports_layout_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));

            }
        });

        return rootView;
    }


}