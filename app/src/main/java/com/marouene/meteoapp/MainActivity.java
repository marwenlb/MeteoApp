package com.marouene.meteoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_view);
        TextView res = findViewById(R.id.text_view);
        List<String> list = new ArrayList<>();
        list.add("Tunis");
        list.add("Ariana");
        list.add("Mednine");
        list.add("Sfax");
        list.add("Sousse");
        list.add("Beja");
        list.add("Bizerte");
        list.add("Tataouine");
        list.add("Gafsa");
        list.add("Monastir");
        list.add("Jendouba");
        list.add("SidiBouzid");
        list.add("Gabes");
        list.add("Kairouan");
        list.add("kef");
        list.add("Mannouba");
        list.add("Mehdia");
        list.add("Nabeul");
        list.add("Séliana");
        list.add("Tozeur");
        list.add("Zeghouan");
        list.add("kebili");
        list.add("Djerba 25");


        int duration = Toast.LENGTH_SHORT;


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                //create instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://goweather.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //call service
                Watherapi service = retrofit.create(Watherapi.class);
                Call<Meteo> call = service.getWeather(selectedItem.trim());

                call.enqueue(new Callback<Meteo>() {
                    @Override
                    public void onResponse(Call<Meteo> call, Response<Meteo> response) {
                        Toast.makeText(getApplicationContext(), response.body().getTemperature() +" "+ selectedItem, duration).show();

                    }

                    @Override
                    public void onFailure(Call<Meteo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage()+ selectedItem, duration).show();

                    }
                });


            }
        });



    }
}