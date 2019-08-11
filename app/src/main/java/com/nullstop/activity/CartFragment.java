package com.nullstop.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nullstop.R;
import com.nullstop.activity.model.CartInfo;
import com.nullstop.activity.model.Product;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        SharedPreferences userID = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        final Integer user_id = userID.getInt("id",-1);

        SharedPreferences prefs = getActivity().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        final Integer productBill = prefs.getInt("BillAmount",-1);
        final String arrayProducts = prefs.getString("Products", null);
        Product[] products_callback= new Gson().fromJson(arrayProducts, Product[].class);
        CustomAdaptor customAdaptor = new CustomAdaptor(getActivity(),products_callback);
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        TextView billamount = rootView.findViewById(R.id.BillAmount);
        billamount.setText(billamount.getText().toString() + Integer.toString(productBill));
        Button b1 = (Button) rootView.findViewById(R.id.getCredit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //post request to /cart
                RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
                User user = new User(user_id);
                Call<CartInfo> call1 = apiInterface.getInfo(user);
                call1.enqueue(new Callback<CartInfo>(){

                    @Override
                    public void onResponse(Call<CartInfo> call, Response<CartInfo> response) {
                        CartInfo info = response.body();
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("UserDetails",Context.MODE_PRIVATE).edit();
                        editor.putInt("transactions", info.getTransactions());
                        editor.apply();
                        editor.putFloat("credit_score", info.getCredit_score());
                        editor.apply();
                        editor.putFloat("buyers_score", info.getBuyers_score());
                        editor.apply();
                        editor.putInt("quantity", info.getQuantity());
                        editor.apply();
//                        Toast.makeText(getActivity(), "Buyers score"+Float.toString(info.getBuyers_score()), Toast.LENGTH_SHORT).show();
                        Fragment fragment = BuyingOptionsFragment.newInstance();
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_home, fragment)
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override
                    public void onFailure(Call<CartInfo> call, Throwable t) {

                    }
                });
            }
        });
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.customRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 4. set adapter
        recyclerView.setAdapter(customAdaptor);
        // 5. set item animator to DefaultAnimator
        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
