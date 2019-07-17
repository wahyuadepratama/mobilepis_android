package com.timerlearning.activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timerlearning.R;
import com.timerlearning.adapter.AdapterHistory;
import com.timerlearning.model.History;
import com.timerlearning.model.HistoryList;
import com.timerlearning.utils.ApiInterface;
import com.timerlearning.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity implements AdapterHistory.OnHistoryItemClicked{

    private TextView loading, dateInfo;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private AdapterHistory historyAdapter;
    private ApiInterface client;
    ArrayList<History> history = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_history);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("History");
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv_category);
        historyAdapter = new AdapterHistory(history);

        historyAdapter.setHistory(history);
        historyAdapter.setClickHandler(this);
        recyclerView.setAdapter(historyAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        dateInfo = findViewById(R.id.dateInfo);

        checkSharedPreferences();
    }

    @SuppressLint("SetTextI18n")
    public void checkSharedPreferences(){
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        int mode = preferences.getInt("status", 1);
        Log.d("tag", "checkSharedPreferences: ------------- " + mode);

        if(mode == 1){
            dateInfo.setText("Today's History");
            fetchDayHistory();
        }else if(mode == 2){
            dateInfo.setText("This Week's History");
            fetchWeekHistory();
        }else if(mode == 3){
            dateInfo.setText("This Month's History");
            fetchMonthHistory();
        }else if(mode == 4){
            dateInfo.setText("All History");
            fetchHistory();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                historyAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                historyAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int back = item.getItemId();
        if (back == android.R.id.home) {
            onBackPressed();  return true;
        }

        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        switch (item.getItemId()){
            case R.id.searchDay:
                dateInfo.setText("Today's History");

                editor.putInt("status", 1);
                editor.apply();

                fetchDayHistory();
                break;
            case R.id.searchWeek:
                dateInfo.setText("This Week's History");

                editor.putInt("status", 2);
                editor.commit();

                fetchWeekHistory();
                break;
            case R.id.searchMonth:
                dateInfo.setText("This Month's History");

                editor.putInt("status", 3);
                editor.commit();

                fetchMonthHistory();
                break;
            case R.id.reload:
                dateInfo.setText("All History");

                editor.putInt("status", 4);
                editor.commit();

                fetchHistory();
                break;
            case R.id.action_search:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchHistory(){

        // --------------   Show loading
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);

        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        // --------------- End Show Loading

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_API)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.client(httpClient.build()).build();

        client =  retrofit.create(ApiInterface.class);

        Call<HistoryList> call = client.getAllMessage();

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                HistoryList historyList = response.body();
                assert historyList != null;
                List<History> listHistory = historyList.result;
                historyAdapter.setHistory(new ArrayList<>(listHistory));

                // ----------- Unshow loading
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                // ------------ End Unshow loading
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Please check your connection..", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void fetchDayHistory(){

        // --------------   Show loading
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);

        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        // --------------- End Show Loading

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_API)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.client(httpClient.build()).build();

        client =  retrofit.create(ApiInterface.class);

        Call<HistoryList> call = client.getDayMessage("true");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                HistoryList historyList = response.body();
                assert historyList != null;
                List<History> listHistory = historyList.result;
                historyAdapter.setHistory(new ArrayList<>(listHistory));

                // ----------- Unshow loading
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                // ------------ End Unshow loading
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Please check your connection..", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void fetchWeekHistory(){

        // --------------   Show loading
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);

        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        // --------------- End Show Loading

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_API)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.client(httpClient.build()).build();

        client =  retrofit.create(ApiInterface.class);

        Call<HistoryList> call = client.getWeekMessage("true");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                HistoryList historyList = response.body();
                assert historyList != null;
                List<History> listHistory = historyList.result;
                historyAdapter.setHistory(new ArrayList<>(listHistory));

                // ----------- Unshow loading
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                // ------------ End Unshow loading
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Please check your connection..", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void fetchMonthHistory(){

        // --------------   Show loading
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);

        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        // --------------- End Show Loading

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constants.BASE_API)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit = builder.client(httpClient.build()).build();

        client =  retrofit.create(ApiInterface.class);

        Call<HistoryList> call = client.getMonthMessage("true");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                HistoryList historyList = response.body();
                assert historyList != null;
                List<History> listHistory = historyList.result;
                historyAdapter.setHistory(new ArrayList<>(listHistory));

                // ----------- Unshow loading
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                // ------------ End Unshow loading
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Please check your connection..", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void historyItemClicked(History m) {
        Intent detailActivityIntent = new Intent(this, HistoryDetailActivity.class);
        detailActivityIntent.putExtra("data", m);
        startActivity(detailActivityIntent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
