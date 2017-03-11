package com.example.restdemotwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String API="https://api.github.com/";//BASE URL

        String mUserName="square";
        String mRepo="retrofit";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API)
                .build();
        Githubapi repo=retrofit.create(Githubapi.class);
        Call<ResponseBody> call=repo.getcall(mUserName,mRepo);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Gson gson=new Gson();
                    ArrayList<Contributor> cList=gson.fromJson(response.body().string(),new TypeToken<List<Contributor>>(){}.getType());
                    for(Contributor contributor:cList){
                        Log.d("login",contributor.getLogin());
                        Log.d("contributions",contributor.getContributions()+"");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("MainActivity","request loading fail...");
            }
        });

    }
}
