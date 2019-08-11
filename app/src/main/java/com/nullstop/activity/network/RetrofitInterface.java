package com.nullstop.activity.network;

import com.nullstop.activity.model.Bill;
import com.nullstop.activity.model.Cart;
import com.nullstop.activity.model.CartInfo;
import com.nullstop.activity.model.EmiOptions;
import com.nullstop.activity.model.Login;
import com.nullstop.activity.model.NumberInstallments;
import com.nullstop.activity.model.PaymentOptions;
import com.nullstop.activity.model.Signup;
import com.nullstop.activity.model.SimulationOptions;
import com.nullstop.activity.model.User;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;


public interface RetrofitInterface {

    @POST("/signup/")
    Call<User> createUser(@Body Signup signup);  //1 for success signup, 0 for not success
//    public void insertUser(
//            @Field("ticker") String ticker,
//            @Field("open_value") Double open,
//            @Field("close_value") Double close,
//            @Field("volume") Integer volume,
//            Callback<LoginResponse> callback);


    @POST("/login/")
    Call<User> loginUser(@Body Login login);

    @POST("/catalogue/")
    Call<Bill> sendProducts(@Body Cart cart);

    @POST("/cart/")
    Call<CartInfo> getInfo(@Body User user);

    @POST("/pay/")
    Call<EmiOptions> getEmiInfo(@Body PaymentOptions paymentOptions);

    @POST("/emi/")
    Call<User> postInstallments(@Body NumberInstallments numberInstallments);

    @POST("/simulation/")
    Call<User> postSimulation(@Body SimulationOptions simulationOptions);

    @POST("/logout/")
    Call<User> logout(@Body User user);


}