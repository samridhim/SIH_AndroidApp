package com.nullstop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nullstop.R;
import com.nullstop.activity.model.Login;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText email;
    EditText password;
    RetrofitInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.Login);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                Login login1 = new Login(email1, password1);
                Call<User> call1 = apiInterface.loginUser(login1);
                call1.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        Toast.makeText(getApplicationContext(), "User ID" + user.user_id, Toast.LENGTH_SHORT).show();
                        if(user.getUser_id()==-1){
                            Toast.makeText(getApplicationContext(), "User not found. Please try again", Toast.LENGTH_SHORT).show();
                        }
                        else {
//                            Toast.makeText(getApplicationContext(), "Welcome " + Integer.toString(user.getUser_id()), Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = getSharedPreferences("UserDetails", MODE_PRIVATE).edit();
                            editor.putInt("id", user.getUser_id());
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "OnFailure Called", Toast.LENGTH_SHORT).show();
                    }
                });



                //make a post call here, send the user id ahead to main activity in shared preferences
            }
        });

        signup = (Button) findViewById(R.id.Signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);


            }
        });
    }
}
