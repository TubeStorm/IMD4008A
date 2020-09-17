package com.example.favourdiokpo.tutorial4;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DashboardFragment extends Fragment {

    TextView counter;
    Button increment;
    int count;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final DataContainer dc2 = ViewModelProviders.of(this).get(DataContainer.class);
        view = inflater.inflate(R.layout.dashboard_fragment, null);
        counter = view.findViewById(R.id.dashboard_counter);
        increment = view.findViewById(R.id.dashboard_button);


        count = ViewModelProviders.of(getActivity()).get(DataContainer.class).dashboardCounter;
        counter.setText(Integer.toString(count));


        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                counter.setText(Integer.toString(count));
                ViewModelProviders.of(getActivity()).get(DataContainer.class).dashboardCounter = count;
            }
        });



        return view;

    }
}
