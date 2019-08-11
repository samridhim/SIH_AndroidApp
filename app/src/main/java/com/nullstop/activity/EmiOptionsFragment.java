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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nullstop.R;
import com.nullstop.activity.model.CartInfo;
import com.nullstop.activity.model.NumberInstallments;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class EmiOptionsFragment extends Fragment {

    public EmiOptionsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EmiOptionsFragment newInstance() {
        EmiOptionsFragment fragment = new EmiOptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences prefs = getActivity().getSharedPreferences("UserDetails", MODE_PRIVATE);
        final int months_3 = prefs.getInt("months_3", -1);
        final int months_6 = prefs.getInt("months_6", -1);
        final int months_12 = prefs.getInt("months_12", -1);
        final Integer user_id = prefs.getInt("id", -1);
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_emi_options, container, false);
        Button b1 = (Button) rootView.findViewById(R.id.Proceed);
        final RadioButton rb_months3 = rootView.findViewById(R.id.months_3);
        final RadioButton rb_months6 = rootView.findViewById(R.id.months_6);
        final RadioButton rb_months12 = rootView.findViewById(R.id.months_12);
//        Toast.makeText(getActivity(), "Months 6 "+Integer.toString(months_6), Toast.LENGTH_SHORT ).show();
//        Toast.makeText(getActivity(), "Months 12" +Integer.toString(months_12), Toast.LENGTH_SHORT ).show();
        if(months_3==0){
            rb_months3.setEnabled(false);
        }
        if(months_6==0){
            rb_months6.setEnabled(false);
        }
        if(months_12==0){
            rb_months12.setEnabled(false);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num_installments =0;
                if(rb_months3.isChecked()){
                    num_installments = 3;
                }

                if(rb_months6.isChecked()){
                    num_installments= 6;
                }

                if(rb_months12.isChecked()){
                    num_installments = 12;
                }
                RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
                NumberInstallments numberInstallments = new NumberInstallments(user_id, num_installments);
                Call<User> call1 = apiInterface.postInstallments(numberInstallments);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Fragment fragment = SimulationFragment.newInstance();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_home, fragment)
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getActivity(), "Oops! Something went wrong", Toast.LENGTH_SHORT).show();

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