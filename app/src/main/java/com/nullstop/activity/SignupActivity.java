package com.nullstop.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nullstop.R;
import com.nullstop.activity.model.Signup;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    RetrofitInterface apiInterface;
    Button signup;
    EditText email;
    EditText fname;
    EditText lname;
    EditText number;
    EditText gender;
    EditText password;
    EditText city;
    EditText age;
    EditText pincode;
    EditText state;
    EditText address;
    EditText phonetype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        signup = (Button) findViewById(R.id.Signup);
        email = (EditText) findViewById(R.id.Email);
        fname = (EditText) findViewById(R.id.FirstName);
        lname = (EditText) findViewById(R.id.LastName);
        number = (EditText) findViewById(R.id.ContactNumber);
        gender = (EditText) findViewById(R.id.Gender);
        password = (EditText) findViewById(R.id.Password);
        city = (EditText) findViewById(R.id.City);
        age = (EditText) findViewById(R.id.Age);
        pincode = (EditText) findViewById(R.id.Pincode);
        address = (EditText) findViewById(R.id.Address);
        state = (EditText) findViewById(R.id.State);
        phonetype = (EditText) findViewById(R.id.PhoneType);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String fname1 = fname.getText().toString();
                String lname1 = lname.getText().toString();
                Long number1 = Long.parseLong(number.getText().toString());
                String gender1  = gender.getText().toString();
                String password1 = password.getText().toString();
                String city1 = city.getText().toString();
                Integer age1 = Integer.parseInt(age.getText().toString());
                Integer pincode1 = Integer.parseInt(pincode.getText().toString());
                String state1 = state.getText().toString();
                String address1 = address.getText().toString();
                String phonetype1 = phonetype.getText().toString();
                final Signup signup1 = new Signup(email1,password1, fname1, lname1, age1, city1,pincode1,number1, gender1,state1, address1,phonetype1);
                // make an object of SignupActivity, call via constructer;
                //insert in same order as class declarations?
                Call<User> call1 = apiInterface.createUser(signup1);
                call1.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        Toast.makeText(getApplicationContext(), Integer.toString(user.getUser_id()), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences("UserDetails",MODE_PRIVATE).edit();
                        editor.putInt("id", user.getUser_id());
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "OnFailureCalled", Toast.LENGTH_SHORT).show();

                    }
                });

                //Get all entries from the form and make a post retrofit call

            }
        });
    }
}
