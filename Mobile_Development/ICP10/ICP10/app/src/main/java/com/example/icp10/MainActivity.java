package com.example.icp10;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiCollection apiCollection = retrofit.create(ApiCollection.class);


        Call<List<User>> usersCall = apiCollection.getData();


        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {


                List<User> users = response.body();


                for (User user: users){
                    String data = "";
                    data += "ID: " + user.getId() + "\n";
                    data += "UserName: " + user.getUserName() + " \n";


                    textView.append(data);
                }
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
