package com.oyelabs.marvel.universe.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.oyelabs.marvel.universe.Adapter.CharactersAdapter;
import com.oyelabs.marvel.universe.Connection.Api;
import com.oyelabs.marvel.universe.Connection.ApiConstant;
import com.oyelabs.marvel.universe.Connection.GetHashAndTs;
import com.oyelabs.marvel.universe.Connection.MD5;
import com.oyelabs.marvel.universe.Connection.RetrofitClient;
import com.oyelabs.marvel.universe.R;
import com.oyelabs.marvel.universe.Response.CharacterResponse.BasicResponse;
import com.oyelabs.marvel.universe.Response.CharacterResponse.ResultsItem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class CharacterListActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<ResultsItem> arrayList=new ArrayList<>();
    CharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);

        getdata(ApiConstant.time,ApiConstant.hashkey);

        SearchingData();

    }

    public void SearchingData()
    {

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter != null) {
                    adapter.filterForSearch(query.trim());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    adapter.filterForSearch(newText.trim());
                }
                return false;
            }
        });

    }



    public void getdata(String timestamp,String hash)
    {
        ProgressDialog progressDialog=new ProgressDialog(CharacterListActivity.this);
        progressDialog.show();
        Api api= RetrofitClient.getRetrofit().create(Api.class);
        api.getdata(timestamp,ApiConstant.PUBLIC_KEY,hash)
                .enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                        if (response.isSuccessful())
                        {
                            Toast.makeText(CharacterListActivity.this, "HELLO", Toast.LENGTH_SHORT).show();
                            arrayList.addAll(response.body().getData().getResults());
                            GridLayoutManager gridLayoutManager=new GridLayoutManager(CharacterListActivity.this,2);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            adapter=new CharactersAdapter(CharacterListActivity.this,arrayList);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {


                    }
                });
    }

}