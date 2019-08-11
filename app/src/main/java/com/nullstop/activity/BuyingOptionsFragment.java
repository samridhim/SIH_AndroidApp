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
import com.nullstop.activity.model.EmiOptions;
import com.nullstop.activity.model.PaymentOptions;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class BuyingOptionsFragment extends Fragment {

    public BuyingOptionsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BuyingOptionsFragment newInstance() {
        BuyingOptionsFragment fragment = new BuyingOptionsFragment();
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
        View rootView =  inflater.inflate(R.layout.fragment_buying_options, container, false);
        final RadioButton cc = (RadioButton) rootView.findViewById(R.id.cc);
        final RadioButton dc = (RadioButton) rootView.findViewById(R.id.dc);
        final RadioButton cod = (RadioButton) rootView.findViewById(R.id.cod);
        final RadioButton emi = (RadioButton) rootView.findViewById(R.id.emi);
        final RadioButton nb = (RadioButton) rootView.findViewById(R.id.nb);
        final RadioButton mw = (RadioButton) rootView.findViewById(R.id.mw);
        final RadioButton post = (RadioButton) rootView.findViewById(R.id.post);

        SharedPreferences prefs = getActivity().getSharedPreferences("UserDetails", MODE_PRIVATE);
        Integer transactions = prefs.getInt("transactions", -1);
        Float credit_score = prefs.getFloat("credit_score", -1);
        Float buyers_score = prefs.getFloat("buyers_score", -1);
        Integer quantity = prefs.getInt("quantity", -1);
        final Integer user_id = prefs.getInt("id",-1);


        Toast.makeText(getActivity(),"Buyers score: " + Float.toString(buyers_score), Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), "Credit Score: " + Float.toString(credit_score), Toast.LENGTH_LONG).show();

        if(quantity!=1){
                emi.setEnabled(false);
        }

        if(credit_score < 40){
            emi.setEnabled(false);
        }

        if(buyers_score < 40){
            cod.setEnabled(false);
        }

        if(buyers_score >= 40 && buyers_score<60){
            Toast.makeText(getContext(), "You have availed 30% discount on delivery charge!", Toast.LENGTH_SHORT).show();
        }
        if(buyers_score >= 60 && buyers_score<90){
                            Toast.makeText(getContext(), "You have availed 60% discount on delivery charge!", Toast.LENGTH_SHORT).show();
            }
            else if(buyers_score >=90 && buyers_score<100)
            {
                            Toast.makeText(getContext(), "You have availed full discount on delivery charge!", Toast.LENGTH_SHORT).show();
            }

        Button submit = (Button) rootView.findViewById(R.id.Proceed);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer payment_option_id = 0;
                if(cc.isChecked()){
                    payment_option_id = 1;
                }
                if(dc.isChecked()){
                    payment_option_id = 2;
                }
                if(cod.isChecked()){
                    payment_option_id = 3;
                }
                if(emi.isChecked()){
                    payment_option_id = 4;
                }
                if(nb.isChecked()){
                    payment_option_id = 5;
                }
                if(mw.isChecked()){
                    payment_option_id = 6;
                }
                if(post.isChecked()){
                    payment_option_id = 12;
                }



                PaymentOptions paymentOptions = new PaymentOptions(user_id, payment_option_id);
                RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
                Call<EmiOptions> call1 = apiInterface.getEmiInfo(paymentOptions);
                call1.enqueue(new Callback<EmiOptions>() {
                                  @Override
                                  public void onResponse(Call<EmiOptions> call, Response<EmiOptions> response) {
                                      EmiOptions options = response.body();
//                                      Toast.makeText(getActivity(), "months_3" + Integer.toString(options.getMonths_3()), Toast.LENGTH_SHORT).show();
//                                      Toast.makeText(getActivity(), "months_6" + Integer.toString(options.getMonths_6()), Toast.LENGTH_SHORT).show();
//                                      Toast.makeText(getActivity(), "months_12" + Integer.toString(options.getMonths_12()), Toast.LENGTH_SHORT).show();
                                      SharedPreferences.Editor editor = getActivity().getSharedPreferences("UserDetails", MODE_PRIVATE).edit();
                                      editor.putInt("months_3", options.months_3);
                                      editor.apply();

                                      editor.putInt("months_6", options.months_6);
                                      editor.apply();

                                      editor.putInt("months_12", options.months_12);
                                      editor.apply();

                                      if (emi.isChecked()){
                                          Fragment fragment = EmiOptionsFragment.newInstance();
                                          getActivity().getSupportFragmentManager()
                                                  .beginTransaction()
                                                  .replace(R.id.content_home, fragment)
                                                  .addToBackStack(null)
                                                  .commit();
                                      }
                                      else
                                      {
                                          Fragment fragment = SimulationFragment.newInstance();
                                          getActivity().getSupportFragmentManager()
                                                  .beginTransaction()
                                                  .replace(R.id.content_home, fragment)
                                                  .addToBackStack(null)
                                                  .commit();
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<EmiOptions> call, Throwable t) {

                                      Toast.makeText(getActivity(), "Oops! Something went wrong", Toast.LENGTH_SHORT).show();

                                  }
                              }
                );
                //send the checked emi option and move to the EMI simulation page if checked EMI option is emi, else move to the buy / return / cancel simulation
            }
        });
        return rootView;


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}