package com.pariportal.seconddemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pariportal.seconddemo.R;
import com.pariportal.seconddemo.adapter.NewsAdapter;
import com.pariportal.seconddemo.api.ApiClient;
import com.pariportal.seconddemo.api.ApiInterface;
import com.pariportal.seconddemo.model.DataModel;
import com.pariportal.seconddemo.model.Source;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnselecteditems;
    private List<Source> sourceList;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private final static String API_KEY = "43df2d0533eb495681006a2773d86da5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setID();
        setOnClickListeners();
        loadNewsData();
    }

    // set the findViewByIds
    private void setID() {

        btnselecteditems = (Button)findViewById(R.id.btnselecteditems);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        sourceList=new ArrayList<>();

    }

    //set the Click-Listeners
    private void setOnClickListeners() {

        btnselecteditems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newsname ="";
                List<Source> sources = ((NewsAdapter)newsAdapter).getDataModelList();
                for (int i=0; i<sourceList.size();i++)
                {
                    Source source = sources.get(i);
                    if (source.isSelected() == true)
                    {
                        newsname = newsname + "\n" + source.getName().toString();

                    }
                }

                Toast.makeText(MainActivity.this,"Selected News are: \n" + newsname, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                intent.putExtra("newsname",newsname);
                startActivity(intent);
            }
        });
    }

    //News API Calling
    private void loadNewsData() {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<DataModel> call = api.getNewsData(API_KEY);

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.body().getStatus().equals("ok"))
                {
                    newsAdapter = new NewsAdapter(MainActivity.this,response.body().getSources());
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(newsAdapter);

                    for(int i=0;i<response.body().getSources().size();i++){
                        sourceList.add(i,response.body().getSources().get(i));
                    }

                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }


}
