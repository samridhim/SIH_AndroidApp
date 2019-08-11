package com.nullstop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nullstop.R;
import com.nullstop.activity.model.Bill;
import com.nullstop.activity.model.Cart;
import com.nullstop.activity.model.Product;
import com.nullstop.activity.model.User;
import com.nullstop.activity.network.RetrofitInstance;
import com.nullstop.activity.network.RetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.OnFragmentInteractionListener, CartFragment.OnFragmentInteractionListener {

    NavigationView navigationView;
    Button addtoCart;
    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    CheckBox check4;
    CheckBox check5;
    CheckBox check6;

    EditText q1;
    EditText q2;
    EditText q3;
    EditText q4;
    EditText q5;
    EditText q6;


    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addtoCart = (Button) findViewById(R.id.AddCart);

        check1 = (CheckBox)findViewById(R.id.check1);
        check2 = (CheckBox)findViewById(R.id.check2);
        check3 = (CheckBox)findViewById(R.id.check3);
        check4 = (CheckBox)findViewById(R.id.check4);
        check5 = (CheckBox)findViewById(R.id.check5);
        check6 = (CheckBox)findViewById(R.id.check6);



        q1 = (EditText) findViewById(R.id.q1);
        q2 = (EditText) findViewById(R.id.q2);
        q3 = (EditText) findViewById(R.id.q3);
        q4 = (EditText) findViewById(R.id.q4);
        q5 = (EditText) findViewById(R.id.q5);
        q6 = (EditText) findViewById(R.id.q6);


        SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
        final Integer user_id = prefs.getInt("id",-1);




        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<Product> products = new ArrayList<>();
                //Retrofit posting to Order Details before this
                if(check1.isChecked()) {

                    Product p1 = new Product(1, "HOME AND FURNITURE", 2699.00, 1);
                    p1.setProduct_quantity(Integer.parseInt(q1.getText().toString()));
                    products.add(p1);
                }
                if(check2.isChecked()){
                    Product p2 = new Product(2, "MEN'S FASHION",  5398.00,1);
                    p2.setProduct_quantity(Integer.parseInt(q2.getText().toString()));
                    products.add(p2);
                }


                if(check3.isChecked()){
                    Product p3 = new Product(3, "COMPUTERS AND ELECTRONICS",  10800.00,1);
                    p3.setProduct_quantity(Integer.parseInt(q3.getText().toString()));
                    products.add(p3);
                }


                if(check4.isChecked()){
                    Product p4 = new Product(4, "WOMEN'S FASHION",  3999.00,1);
                    p4.setProduct_quantity(Integer.parseInt(q4.getText().toString()));
                    products.add(p4);
                }


                if(check5.isChecked()){
                    Product p5 = new Product(5, "JEWELLERY",  42321.00,1);
                    p5.setProduct_quantity(Integer.parseInt(q5.getText().toString()));
                    products.add(p5);

                }

                if(check6.isChecked()){
                    Product p6 = new Product(6, "ACCESSORIES",  201000.00,1);
                    p6.setProduct_quantity(Integer.parseInt(q6.getText().toString()));
                    products.add(p6);
                }

                final String jsonlist = new Gson().toJson(products);
//                Toast.makeText(getApplicationContext(), jsonlist, Toast.LENGTH_SHORT).show();
                //make post call to products to add to order history
                RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);

                Cart cart1  = new Cart(user_id, products);
                Call<Bill> call1 = apiInterface.sendProducts(cart1);
                call1.enqueue(new Callback<Bill>(){

                    @Override
                    public void onResponse(Call<Bill> call, Response<Bill> response) {
                        Bill bill1 = response.body();
                        SharedPreferences.Editor editor = getSharedPreferences("UserDetails",MODE_PRIVATE).edit();
                        editor.putInt("BillAmount", bill1.BillTotal);
                        editor.apply();
                        editor.putString("Products", jsonlist);
                        editor.apply();
//                        Toast.makeText(getApplicationContext(), Integer.toString(bill1.BillTotal), Toast.LENGTH_SHORT).show();
                        Fragment fragment = CartFragment.newInstance();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content_home, fragment)
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override
                    public void onFailure(Call<Bill> call, Throwable t) {

                    }});


            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            //do a post request to logout
            RetrofitInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
            SharedPreferences prefs = getSharedPreferences("UserDetails", MODE_PRIVATE);
            Integer userId = prefs.getInt("id", -1);
            User user  = new User(userId);
            Call<User> call1 = apiInterface.logout(user);
            call1.enqueue(new Callback<User>(){
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplication(), "Error occured!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        navigationView.setCheckedItem(id);
        boolean fragmentFlag = false;

//        if (id == R.id.nav_profile) {
//            fragmentFlag = true;
//            fragment = ProfileFragment.newInstance();
//
//        }
            if (id == R.id.cart) {
            fragmentFlag = true;
            fragment = CartFragment.newInstance();
        }

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        if(fragmentFlag) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_home, fragment)
                    .addToBackStack(null)
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
