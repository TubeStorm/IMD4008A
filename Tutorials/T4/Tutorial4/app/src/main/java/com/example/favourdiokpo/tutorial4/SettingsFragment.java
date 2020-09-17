package com.example.favourdiokpo.tutorial4;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    TextView counter, dash, notif, home;
    Button increment;
    int count = 0;
    View view;
    public int notifCount = 0;
    public int homeCount = 0;
    public int dashCount = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final DataContainer dc4 = ViewModelProviders.of(this).get(DataContainer.class);
        view = inflater.inflate(R.layout.settings_fragment, null);
        counter = view.findViewById(R.id.settings_counter);
        increment = view.findViewById(R.id.settings_button);

        dash = view.findViewById(R.id.dashboard_counter2);
        notif = view.findViewById(R.id.notifi_counter2);
        home = view.findViewById(R.id.home_counter2);

        notifCount = ViewModelProviders.of(getActivity()).get(DataContainer.class).notificationsCounter;
        notif.setText(Integer.toString(notifCount));

        homeCount = ViewModelProviders.of(getActivity()).get(DataContainer.class).homeCounter;
        home.setText(Integer.toString(homeCount));

        dashCount = ViewModelProviders.of(getActivity()).get(DataContainer.class).dashboardCounter;
        dash.setText(Integer.toString(dashCount));


        count = ViewModelProviders.of(getActivity()).get(DataContainer.class).settingsCounter;
        counter.setText(Integer.toString(count));


        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                counter.setText(Integer.toString(count));
                ViewModelProviders.of(getActivity()).get(DataContainer.class).settingsCounter = count;

                notif.setText(Integer.toString(notifCount));
                ViewModelProviders.of(getActivity()).get(DataContainer.class).notificationsCounter = notifCount;

                home.setText(Integer.toString(homeCount));
                ViewModelProviders.of(getActivity()).get(DataContainer.class).homeCounter = homeCount;

                dash.setText(Integer.toString(dashCount));
                ViewModelProviders.of(getActivity()).get(DataContainer.class).dashboardCounter = dashCount;


            }
        });

        return view;

    }
}


