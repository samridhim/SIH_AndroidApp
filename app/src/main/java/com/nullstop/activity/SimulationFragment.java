package com.nullstop.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nullstop.R;
import com.nullstop.activity.model.CartInfo;
import com.nullstop.activity.model.SimulationOptions;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SimulationFragment extends Fragment {

    public SimulationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SimulationFragment newInstance() {
        SimulationFragment fragment = new SimulationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_simulation, container, false);
        final RadioButton rb_buy = (RadioButton) rootView.findViewById(R.id.buy);
        final RadioButton rb_cancel = (RadioButton) rootView.findViewById(R.id.cancel);
        final RadioButton rb_returned = (RadioButton) rootView.findViewById(R.id.returned);

        Button proceed = (Button) rootView.findViewById(R.id.Proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int simulation_option = 0;
                if(rb_buy.isChecked()){
                    simulation_option = 1;
                }
                if(rb_returned.isChecked())
                {
                    simulation_option = 2;
                }
                if(rb_cancel.isChecked()){
                    simulation_option=3;
                }
                SharedPreferences preferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                Integer userId = preferences.getInt("id", -1);
                RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
                SimulationOptions simulationOptions = new SimulationOptions(userId, simulation_option, 0);
                Call<User> call1 = apiInterface.postSimulation(simulationOptions);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        SharedPreferences preferences = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(getActivity(), "Now you can logout!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(getActivity(), "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        return rootView;


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}