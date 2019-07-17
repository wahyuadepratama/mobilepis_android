package com.timerlearning.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.timerlearning.R;
import com.timerlearning.utils.ApiInterface;
import com.timerlearning.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    private Thread t;

    private TextView TV_2R1M01, TV_2W1W01, TV_2K1M01, TV_2Z1M01;
    private Double total_2R1M01, a_2R1M01, b_2R1M01, c_2R1M01, d_2R1M01, e_2R1M01;
    private Double total_2W1W01, a_2W1W01, b_2W1W01;
    private Double total_2K1M01, a_2K1M01, b_2K1M01;
    private Double total_2Z1M01, a_2Z1M01, b_2Z1M01, c_2Z1M01, d_2Z1M01, e_2Z1M01;

    private TextView TV_3R2M01, TV_3W2W01, TV_3K2M01, TV_3Z2M01;
    private Double total_3R2M01, a_3R2M01, b_3R2M01, c_3R2M01, d_3R2M01, e_3R2M01;
    private Double total_3W2W01, a_3W2W01, b_3W2W01;
    private Double total_3K2M01, a_3K2M01, b_3K2M01;
    private Double total_3Z2M01, a_3Z2M01, b_3Z2M01, c_3Z2M01, d_3Z2M01, e_3Z2M01;

    private TextView TV_4R1M01, TV_4W1W01, TV_4K2M01, TV_4Z1M01, TV_4R2M01, TV_4K3M01, TV_4Z2M01;
    private Double total_4R1M01, a_4R1M01, b_4R1M01, c_4R1M01, d_4R1M01, e_4R1M01;
    private Double total_4W1W01, a_4W1W01, b_4W1W01, c_4W1W01;
    private Double total_4K2M01, a_4K2M01, b_4K2M01;
    private Double total_4Z1M01, a_4Z1M01, b_4Z1M01, c_4Z1M01, d_4Z1M01, e_4Z1M01, f_4Z1M01, g_4Z1M01, h_4Z1M01;
    private Double total_4R2M01, a_4R2M01, b_4R2M01, c_4R2M01, d_4R2M01, e_4R2M01;
    private Double total_4K3M01, a_4K3M01, b_4K3M01;
    private Double total_4Z2M01, a_4Z2M01, b_4Z2M01, c_4Z2M01, d_4Z2M01, e_4Z2M01;

    private TextView TV_5R1M01, TV_5W1W01, TV_5K1M01, TV_5Z1M01, TV_5R2M01, TV_5Z2M01;
    private Double total_5R1M01, a_5R1M01, b_5R1M01, c_5R1M01, d_5R1M01, e_5R1M01;
    private Double total_5W1W01, a_5W1W01, b_5W1W01, c_5W1W01;
    private Double total_5K1M01, a_5K1M01, b_5K1M01;
    private Double total_5Z1M01, a_5Z1M01, b_5Z1M01, c_5Z1M01, d_5Z1M01, e_5Z1M01;
    private Double total_5R2M01, a_5R2M01, b_5R2M01, c_5R2M01, d_5R2M01, e_5R2M01;
    private Double total_5Z2M01, a_5Z2M01, b_5Z2M01, c_5Z2M01, d_5Z2M01, e_5Z2M01;

    private TextView TV_6R1M01, TV_6W1W01, TV_6K1M01, TV_6Z1M01;
    private Double total_6R1M01, a_6R1M01, b_6R1M01, c_6R1M01, d_6R1M01, e_6R1M01;
    private Double total_6W1W01, a_6W1W01, b_6W1W01, c_6W1W01;
    private Double total_6K1M01, a_6K1M01, b_6K1M01;
    private Double total_6Z1M01, a_6Z1M01, b_6Z1M01, c_6Z1M01, d_6Z1M01, e_6Z1M01;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TV_2R1M01 = findViewById(R.id._2R1M01);
        TV_2W1W01 = findViewById(R.id._2W1W01);
        TV_2K1M01 = findViewById(R.id._2K1M01);
        TV_2Z1M01 = findViewById(R.id._2Z1M01);

        TV_3R2M01 = findViewById(R.id._3R2M01);
        TV_3W2W01 = findViewById(R.id._3W2W01);
        TV_3K2M01 = findViewById(R.id._3K2M01);
        TV_3Z2M01 = findViewById(R.id._3Z2M01);

        TV_4R1M01 = findViewById(R.id._4R1M01);
        TV_4W1W01 = findViewById(R.id._4W1W01);
        TV_4K2M01 = findViewById(R.id._4K2M01);
        TV_4Z1M01 = findViewById(R.id._4Z1M01);
        TV_4R2M01 = findViewById(R.id._4R2M01);
        TV_4K3M01 = findViewById(R.id._4K3M01);
        TV_4Z2M01 = findViewById(R.id._4Z2M01);

        TV_5R1M01 = findViewById(R.id._5R1M01);
        TV_5W1W01 = findViewById(R.id._5W1W01);
        TV_5K1M01 = findViewById(R.id._5K1M01);
        TV_5Z1M01 = findViewById(R.id._5Z1M01);
        TV_5R2M01 = findViewById(R.id._5R2M01);
        TV_5Z2M01 = findViewById(R.id._5Z2M01);

        TV_6R1M01 = findViewById(R.id._6R1M01);
        TV_6W1W01 = findViewById(R.id._6W1W01);
        TV_6K1M01 = findViewById(R.id._6K1M01);
        TV_6Z1M01 = findViewById(R.id._6Z1M01);

        getDataFeeding();

        t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getDataFeeding();
                            }
                        });
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };

        t.start();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("DefaultLocale")
    public void tap2R1M01(View view) {
        Button button = findViewById(R.id._2R1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_2R1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "2R1M01", "Feeding \t\t\t\t\t\t: \t"+ total_2R1M01 +
                            "\n\nN11.R1A01F1 \t\t: \t"+ String.format("%.2f", a_2R1M01) +
                            "\nN11.R1E01F1   \t\t: \t"+ String.format("%.2f", b_2R1M01) +
                            "\nN11.R1C07F1   \t\t: \t"+ String.format("%.2f", c_2R1M01) +
                            "\nN11.R1D01F1   \t\t: \t"+ String.format("%.2f", d_2R1M01) +
                            "\nN11.R1M03M1   \t: \t" + String.format("%.2f", e_2R1M01)
                    , e_2R1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap2W1W01(View view) {
        Button button = findViewById(R.id._2W1W01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_2W1W01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "2W1W01", "Feeding \t\t\t\t\t\t\t\t: \t"+ total_2W1W01 +
                            "\n\nN12.W1A07_09F1 \t\t: \t"+ String.format("%.2f", a_2W1W01) +
                            "\nN12.W1W03_05M1   : \t"+ String.format("%.2f", b_2W1W01)
                    , b_2W1W01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap2K1M01(View view) {
        Button button = findViewById(R.id._2K1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_2K1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "2K1M01", "Feeding \t\t\t\t\t\t: \t"+ total_2K1M01 + " %"+
                            "\n\nN12.K1A01V1 \t\t: \t"+ String.format("%.2f", a_2K1M01) +
                            "\nN12.K1M03M1   \t: \t"+ String.format("%.2f", b_2K1M01)
                    , b_2K1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap2Z1M01(View view) {
        Button button = findViewById(R.id._2Z1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_2Z1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "2Z1M01", "Feeding \t\t\t\t\t\t: \t"+ total_2Z1M01 +
                            "\n\nN13.Z1A01F1 \t\t: \t"+ String.format("%.2f", a_2Z1M01) +
                            "\nN13.Z1B01F1   \t\t: \t"+ String.format("%.2f", b_2Z1M01) +
                            "\nN13.Z1A02F1   \t\t: \t"+ String.format("%.2f", c_2Z1M01) +
                            "\nN13.Z1B02F1   \t\t: \t"+ String.format("%.2f", d_2Z1M01) +
                            "\nN13.Z1M03M1   \t: \t"+ String.format("%.2f", e_2Z1M01)
                    , e_2Z1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap3R2M01(View view) {
        Button button = findViewById(R.id._3R2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_3R2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "3R2M01", "Feeding \t\t\t\t\t\t: \t"+ total_3R2M01 +
                            "\n\nN21.R2A01F1 \t\t: \t"+ String.format("%.2f", a_3R2M01) +
                            "\nN21.R2E01F1   \t\t: \t"+ String.format("%.2f", b_3R2M01) +
                            "\nN21.R2C07F1   \t\t: \t"+ String.format("%.2f", c_3R2M01) +
                            "\nN21.R2D01F1   \t\t: \t"+ String.format("%.2f", d_3R2M01)
                    , e_3R2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap3W2W01(View view) {
        Button button = findViewById(R.id._3W2W01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_3W2W01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "3W2W01", "Feeding \t\t\t\t\t\t\t\t\t\t\t\t\t\t: \t"+ total_3W2W01 +
                            "\n\nN22.W2A07_09F1_ORIGINAL \t: \t"+ String.format("%.2f", a_3W2W01) +
                            "\nN22.W2W03_05M1   \t\t\t\t\t\t: \t"+ String.format("%.2f", b_3W2W01)
                    , b_3W2W01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap3K2M01(View view) {
        Button button = findViewById(R.id._3K2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_3K2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "3K2M01", "Feeding \t\t\t\t\t\t: \t"+ total_3K2M01 + " %"+
                            "\n\nN22.K2A01V1 \t\t: \t"+ String.format("%.2f", a_3K2M01) +
                            "\nN22.K2M03M1   \t: \t"+ String.format("%.2f", b_3K2M01)
                    , b_3K2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap3Z2M01(View view) {
        Button button = findViewById(R.id._3Z2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_3Z2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "3Z2M01", "Feeding \t\t\t\t\t\t: \t"+ total_3Z2M01 +
                            "\n\nN23.Z2A01F1 \t\t: \t"+ String.format("%.2f", a_3Z2M01) +
                            "\nN23.Z2B01F1   \t\t: \t"+ String.format("%.2f", b_3Z2M01) +
                            "\nN23.Z2A02F1   \t\t: \t"+ String.format("%.2f", c_3Z2M01) +
                            "\nN23.Z2B02F1   \t\t: \t"+ String.format("%.2f", d_3Z2M01)
                    , e_3Z2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4R1M01(View view) {
        Button button = findViewById(R.id._4R1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4R1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4R1M01", "Feeding \t\t\t\t\t\t: \t"+ total_4R1M01 +
                            "\n\n4R1.M03M1_M2 \t: \t"+ String.format("%.2f", a_4R1M01) +
                            "\n4R1.A01F1   \t\t\t\t: \t"+ String.format("%.2f", b_4R1M01) +
                            "\n4R1.B01F1   \t\t\t\t: \t"+ String.format("%.2f", c_4R1M01) +
                            "\n4R1.C01F1   \t\t\t\t: \t"+ String.format("%.2f", d_4R1M01) +
                            "\n4R1.J06F1_1   \t\t: \t"+ String.format("%.2f", e_4R1M01)
                    , a_4R1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4W1W01(View view) {
        Button button = findViewById(R.id._4W1W01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4W1W01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4W1W01", "Feeding \t\t\t\t\t\t\t\t\t\t: \t"+ total_4W1W01 +
                            "\n\n4W1.A01F2 \t\t\t\t\t\t\t\t: \t"+ String.format("%.2f", a_4W1W01) +
                            "\n4W1.A01F1_ORIGINAL   : \t"+ String.format("%.2f", b_4W1W01) +
                            "\n4W1.W03_W05   \t\t\t\t\t: \t"+ String.format("%.2f", c_4W1W01)
                    , c_4W1W01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4K2M01(View view) {
        Button button = findViewById(R.id._4K2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4K2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4K2M01", "Feeding \t\t\t\t: \t"+ total_4K2M01 +
                            "\n\n4K2.S01S1 \t\t: \t"+ String.format("%.2f", a_4K2M01) +
                            "\n4K2.M03M1   \t: \t"+ String.format("%.2f", b_4K2M01)
                    , b_4K2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4Z1M01(View view) {
        Button button = findViewById(R.id._4Z1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4Z1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4Z1M01", "Feeding \t\t\t\t\t\t: \t"+ total_4Z1M01 +
                            "\n\n4Z1.M03M1_M2 \t: \t"+ String.format("%.2f", a_4Z1M01) +
                            "\n4Z1.M33M1   \t\t\t: \t"+ String.format("%.2f", b_4Z1M01) +
                            "\n4Z1.M33M2   \t\t\t: \t"+ String.format("%.2f", c_4Z1M01) +
                            "\n4Z1.R03F1   \t\t\t\t: \t"+ String.format("%.2f", d_4Z1M01) +
                            "\n4Z1.A01F1   \t\t\t\t: \t"+ String.format("%.2f", e_4Z1M01) +
                            "\n4Z1.B01F1   \t\t\t\t: \t"+ String.format("%.2f", f_4Z1M01) +
                            "\n4Z1.E01F1   \t\t\t\t: \t"+ String.format("%.2f", g_4Z1M01) +
                            "\n4Z1.D01F1   \t\t\t\t: \t"+ String.format("%.2f", h_4Z1M01)
                    , a_4Z1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4R2M01(View view) {
        Button button = findViewById(R.id._4R2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4R2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4R2M01", "Feeding \t\t\t\t\t: \t"+ total_4R2M01 +
                            "\n\n4R2.M03M1 \t\t\t: \t"+ String.format("%.2f", a_4R2M01) +
                            "\n4R2.A01F1   \t\t\t: \t"+ String.format("%.2f", b_4R2M01) +
                            "\n4R2.B01F1   \t\t\t: \t"+ String.format("%.2f", c_4R2M01) +
                            "\n4R2.C01F1   \t\t\t: \t"+ String.format("%.2f", d_4R2M01) +
                            "\n4R2.J06F1_2  \t\t: \t"+ String.format("%.2f", e_4R2M01)
                    , a_4R2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4K3M01(View view) {
        Button button = findViewById(R.id._4K3M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4K3M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4K3M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_4K3M01 +
                            "\n\n4K3.A01S1_FC \t\t\t: \t"+ String.format("%.2f", a_4K3M01) +
                            "\n4K3.M03M1_FC   \t: \t"+ String.format("%.2f", b_4K3M01)
                    , b_4K3M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap4Z2M01(View view) {
        Button button = findViewById(R.id._4Z2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_4Z2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "4Z2M01", "Feeding \t\t\t\t: \t"+ total_4Z2M01 +
                            "\n\n4Z2.M03M1 \t\t: \t"+ String.format("%.2f", a_4Z2M01) +
                            "\n4Z2.A01F1   \t\t: \t"+ String.format("%.2f", b_4Z2M01) +
                            "\n4Z2.B01F1   \t\t: \t"+ String.format("%.2f", c_4Z2M01) +
                            "\n4Z2.E01F1   \t\t: \t"+ String.format("%.2f", d_4Z2M01) +
                            "\n4Z2.D01F1   \t\t: \t"+ String.format("%.2f", e_4Z2M01)
                    , a_4Z2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5R1M01(View view) {
        Button button = findViewById(R.id._5R1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5R1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5R1M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5R1M01 +
                            "\n\n5R1.M03M1 \t\t\t\t\t: \t"+ String.format("%.2f", a_5R1M01) +
                            "\n5R1.A01M1F01   \t\t: \t"+ String.format("%.2f", b_5R1M01) +
                            "\n5R1.C01M1F01   \t\t: \t"+ String.format("%.2f", c_5R1M01) +
                            "\n5R1.D01M1F01   \t\t: \t"+ String.format("%.2f", d_5R1M01) +
                            "\n5R1.E01M1F01   \t\t: \t"+ String.format("%.2f", e_5R1M01)
                    , a_5R1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5W1W01(View view) {
        Button button = findViewById(R.id._5W1W01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5W1W01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5W1W01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5W1W01 +
                            "\n\n5W1.W03M1 \t\t\t\t: \t"+ String.format("%.2f", a_5W1W01) +
                            "\n5W1.A05A1F01   \t\t: \t"+ String.format("%.2f", b_5W1W01) +
                            "\n5W1.B05A1F01   \t\t: \t"+ String.format("%.2f", c_5W1W01)
                    , a_5W1W01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5K1M01(View view) {
        Button button = findViewById(R.id._5K1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5K1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5K1M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5K1M01 +
                            "\n\n5K1.M03M1 \t\t\t\t: \t"+ String.format("%.2f", a_5K1M01) +
                            "\n5K1.A02M1S01   \t\t: \t"+ String.format("%.2f", b_5K1M01)
                    , a_5K1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5Z1M01(View view) {
        Button button = findViewById(R.id._5Z1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5Z1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5Z1M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5Z1M01 +
                            "\n\n5Z1.M03M1 \t\t\t\t\t: \t"+ String.format("%.2f", a_5Z1M01) +
                            "\n5U1.U12A1F01   \t\t: \t"+ String.format("%.2f", b_5Z1M01) +
                            "\n5Z1.B01A1F01   \t\t: \t"+ String.format("%.2f", c_5Z1M01) +
                            "\n5Z1.C01A1F01   \t\t: \t"+ String.format("%.2f", d_5Z1M01) +
                            "\n5Z1.E01A1F01   \t\t: \t"+ String.format("%.2f", e_5Z1M01)
                    , a_5Z1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5R2M01(View view) {
        Button button = findViewById(R.id._5R2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5R2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5R2M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5R2M01 +
                            "\n\n5R2.M03M1 \t\t\t\t\t: \t"+ String.format("%.2f", a_5R2M01) +
                            "\n5R2.A11M1F01   \t\t: \t"+ String.format("%.2f", b_5R2M01) +
                            "\n5R2.C11M1F01   \t\t: \t"+ String.format("%.2f", c_5R2M01) +
                            "\n5R2.D11M1F01   \t\t: \t"+ String.format("%.2f", d_5R2M01) +
                            "\n5R2.E11M1F01   \t\t: \t"+ String.format("%.2f", e_5R2M01)
                    , a_5R2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap5Z2M01(View view) {
        Button button = findViewById(R.id._5Z2M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_5Z2M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView2( "5Z2M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_5Z2M01 +
                            "\n\n5Z2.M03M1 \t\t\t\t\t: \t"+ String.format("%.2f", a_5Z2M01) +
                            "\n5U1.U02A1F01   \t\t: \t"+ String.format("%.2f", b_5Z2M01) +
                            "\n5Z2.B01A1F01   \t\t: \t"+ String.format("%.2f", c_5Z2M01) +
                            "\n5Z2.C01A1F01   \t\t: \t"+ String.format("%.2f", d_5Z2M01) +
                            "\n5Z2.E01A1F01   \t\t: \t"+ String.format("%.2f", e_5Z2M01)
                    , a_5Z2M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap6R1M01(View view) {
        Button button = findViewById(R.id._6R1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_6R1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "6R1M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_6R1M01 +
                            "\n\n6R1.A01A01F01 \t\t: \t"+ String.format("%.2f", a_6R1M01) +
                            "\n6R1.E01A01F01   \t: \t"+ String.format("%.2f", b_6R1M01) +
                            "\n6R1.D01A01F01   \t: \t"+ String.format("%.2f", c_6R1M01) +
                            "\n6R1.C01A01F01   \t: \t"+ String.format("%.2f", d_6R1M01) +
                            "\n6R1.M03M01   \t\t\t: \t" + String.format("%.2f", e_6R1M01)
                    , e_6R1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap6W1W01(View view) {
        Button button = findViewById(R.id._6W1W01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_6W1W01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "6W1W01", "Feeding \t\t\t\t\t\t\t: \t"+ total_6W1W01 +
                            "\n\n6W1.A05A01F01 \t\t: \t"+ String.format("%.2f", a_6W1W01) +
                            "\n6W1.B05A01F01   \t: \t"+ String.format("%.2f", b_6W1W01) +
                            "\n6W1.W04U01   \t\t\t: \t"+ String.format("%.2f", c_6W1W01)
                    , c_6W1W01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap6K1M01(View view) {
        Button button = findViewById(R.id._6K1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_6K1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "6K1M01", "Feeding \t\t\t\t\t\t: \t"+ total_6K1M01 + " %"+
                            "\n\n6K1.A01A01F01 \t: \t"+ String.format("%.2f", a_6K1M01) +
                            "\n6K1.M03M01   \t\t: \t"+ String.format("%.2f", b_6K1M01)
                    , b_6K1M01);
        }
    }

    @SuppressLint("DefaultLocale")
    public void tap6Z1M01(View view) {
        Button button = findViewById(R.id._6Z1M01);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
        if (total_6Z1M01 == null){
            Toast.makeText(this, "Please waiting ..", Toast.LENGTH_SHORT).show();
        }else{
            alertView( "6Z1M01", "Feeding \t\t\t\t\t\t\t: \t"+ total_6Z1M01 +
                            "\n\n6Z1.A01A01F01 \t\t: \t"+ String.format("%.2f", a_6Z1M01) +
                            "\n6Z1.B01A01F01   \t: \t"+ String.format("%.2f", b_6Z1M01) +
                            "\n6Z1.D01A01F01   \t: \t"+ String.format("%.2f", c_6Z1M01) +
                            "\n6Z1.C01A01F01   \t: \t"+ String.format("%.2f", d_6Z1M01) +
                            "\n6Z1.M03M01   \t\t\t: \t"+ String.format("%.2f", e_6Z1M01)
                    , e_6Z1M01);
        }
    }

    private void alertView( String title, String message, Double status ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle( title ).setMessage(message);

        final AlertDialog newDialog = dialog.create();
        if (newDialog.getWindow() != null)
            newDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        if (status > 0)
            newDialog.setIcon(R.drawable.on);
        else
            newDialog.setIcon(R.drawable.off);

        newDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_round_background);
        newDialog.setButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        newDialog.show();
    }

    private void alertView2( String title, String message, Double status ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle( title ).setMessage(message);

        final AlertDialog newDialog = dialog.create();
        if (newDialog.getWindow() != null)
            newDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        if (status == 32768)
            newDialog.setIcon(R.drawable.on);
        else
            newDialog.setIcon(R.drawable.off);

        newDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_round_background);
        newDialog.setButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        newDialog.show();
    }

    public void getDataFeeding(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(Constants.PIS_API)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Retrofit retrofit = builder.client(httpClient.build()).build();
        ApiInterface client = retrofit.create(ApiInterface.class);
        client.getFeeding(
                    "($N11.R1A01F1.CV + $N11.R1E01F1.CV + $N11.R1C07F1.CV + $N11.R1D01F1.CV) * $N11.R1M03M1",
                    "$N12.W1A07_09F1.CV*$N12.W1W03_05M1.CV",
                    "$N12.K1A01V1.CV*$N12.K1M03M1.CV",
                    "($N13.Z1A01F1.CV+$N13.Z1B01F1.CV+$N13.Z1A02F1.CV+$N13.Z1B02F1.CV)*$N13.Z1M03M1.CV",
                    "$N21.R2A01F1.CV+$N21.R2E01F1.CV+$N21.R2C07F1.CV+$N21.R2D01F1.CV+$N21.R2M03M1.CV",
                    "$N22.W2A07_09F1_ORIGINAL.CV*$N22.W2W03_05M1.CV",
                    "$N22.K2A01V1.CV*$N22.K2M03M1.CV",
                    "$N23.Z2A01F1.CV+$N23.Z2B01F1.CV+$N23.Z2A02F1.CV+$N23.Z2B02F1.CV+$N23.Z2M03M1.CV",
                    "IF(GT($T_4R1.M03M1_M2.CV,0),($T_4R1.A01F1.CV*1.02+$T_4R1.B01F1.CV*1.02+$T_4R1.C01F1.CV*1.02+($T_4R1.J06F1_1.CV*1.2-$T_4R1.C01F1.CV)*1.02),0)",
                    "($T_4W1.A01F2.CV+$T_4W1.A01F1_ORIGINAL.CV)*$T_4W1.W03_W05.CV",
                    "$T_4K2.S01S1.CV*$T_4K2.M03M1.CV:CoalMill4K2",
                    "$T_4Z1.M03M1_M2.CV*(IF(OR($T_4Z1.M33M1.CV,$T_4Z1.M33M2.CV),$T_4Z1.R03F1.CV*1,$T_4Z1.A01F1.CV*1))+($T_4Z1.B01F1.CV*1)+($T_4Z1.E01F1.CV*1)+($T_4Z1.D01F1.CV*1)",
                    "IF(GT($T_4R2.M03M1.CV,0),$T_4R2.A01F1.CV*1.08+$T_4R2.B01F1.CV*1.08+$T_4R2.C01F1.CV*1.08+$T_4R2.J06F1_2.CV*1.08,0)",
                    "$T_4K3.A01S1_FC.CV*$T_4K3.M03M1_FC.CV",
                    "$T_4Z2.M03M1.CV*($T_4Z2.A01F1.CV)+($T_4Z2.B01F1.CV*1)+($T_4Z2.E01F1.CV*1)+($T_4Z2.D01F1.CV*1)",
                    "IF(EQ($T_5R1.M03M1.CV,32768),$T_5R1.A01M1F01.CV+$T_5R1.C01M1F01.CV+$T_5R1.D01M1F01.CV+$T_5R1.E01M1F01.CV,0)",
                    "IF(EQ($T_5W1.W03M1.CV,32768),$T_5W1.A05A1F01.CV+$T_5W1.B05A1F01.CV,0)",
                    "IF(EQ($T_5K1.M03M1.CV,32768),$T_5K1.A02M1S01.CV,0)",
                    "IF(EQ($T_5Z1.M03M1.CV,32768),$T_5U1.U12A1F01.CV*1+$T_5Z1.B01A1F01.CV*1+$T_5Z1.C01A1F01.CV*1+$T_5Z1.E01A1F01.CV*1,0)",
                    "IF(EQ($T_5R2.M03M1.CV,32768),$T_5R2.A11M1F01.CV+$T_5R2.C11M1F01.CV+$T_5R2.D11M1F01.CV+$T_5R2.E11M1F01.CV,0)",
                    "IF(EQ($T_5Z2.M03M1.CV,32768),$T_5U1.U02A1F01.CV+$T_5Z2.B01A1F01.CV+$T_5Z2.C01A1F01.CV+$T_5Z2.E01A1F01.CV,0)",
                    "$T_6R1.A01A01F01.CV+$T_6R1.E01A01F01.CV+$T_6R1.D01A01F01.CV+$T_6R1.C01A01F01.CV+$T_6R1.M03M01.CV",
                    "$T_6W1.A05A01F01.CV+$T_6W1.B05A01F01.CV+$T_6W1.W04U01.CV",
                    "$T_6K1.A01A01F01.CV+$T_6K1.M03M01.CV",
                    "$T_6Z1.A01A01F01.CV+$T_6Z1.B01A01F01.CV+$T_6Z1.D01A01F01.CV+$T_6Z1.C01A01F01.CV+$T_6Z1.M03M01.CV"
        ).enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        assert response.body() != null;
                        JSONObject jsonRESULTS = new JSONObject(Objects.requireNonNull(response.body()).string());
                        JSONArray jArray3 = jsonRESULTS.getJSONArray("variables");

                        JSONObject _2R1M01 = jArray3.getJSONObject(0);
                        a_2R1M01 = _2R1M01.getDouble("N11.R1A01F1.CV");
                        b_2R1M01 = _2R1M01.getDouble("N11.R1E01F1.CV");
                        c_2R1M01 = _2R1M01.getDouble("N11.R1C07F1.CV");
                        d_2R1M01 = _2R1M01.getDouble("N11.R1D01F1.CV");
                        e_2R1M01 = _2R1M01.getDouble("N11.R1M03M1");
                        total_2R1M01 = (b_2R1M01 + c_2R1M01 + d_2R1M01 + a_2R1M01) * e_2R1M01;
                        TV_2R1M01.setText("Raw Mill: 2R1M1 \n\nFeeding: "+ String.format("%.2f", total_2R1M01));
                        if(e_2R1M01 == 0)
                            TV_2R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_2R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _2W1W01 = jArray3.getJSONObject(1);
                        a_2W1W01 = _2W1W01.getDouble("N12.W1A07_09F1.CV");
                        b_2W1W01 = _2W1W01.getDouble("N12.W1W03_05M1.CV");
                        total_2W1W01 = a_2W1W01 * b_2W1W01;
                        TV_2W1W01.setText("Kiln: 2W1W01 \n\nFeeding: "+ String.format("%.2f", total_2W1W01));
                        if(b_2W1W01 == 0)
                            TV_2W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_2W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _2K1M01 = jArray3.getJSONObject(2);
                        a_2K1M01 = _2K1M01.getDouble("N12.K1A01V1.CV");
                        b_2K1M01 = _2K1M01.getDouble("N12.K1M03M1.CV");
                        total_2K1M01 = a_2K1M01 * b_2K1M01;
                        TV_2K1M01.setText("Coal Mill: 2K1M01 \n\nFeeding: "+ String.format("%.2f", total_2K1M01) + " %");
                        if(b_2K1M01 == 0)
                            TV_2K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_2K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _2Z1M01 = jArray3.getJSONObject(3);
                        a_2Z1M01 = _2Z1M01.getDouble("N13.Z1A01F1.CV");
                        b_2Z1M01 = _2Z1M01.getDouble("N13.Z1B01F1.CV");
                        c_2Z1M01 = _2Z1M01.getDouble("N13.Z1A02F1.CV");
                        d_2Z1M01 = _2Z1M01.getDouble("N13.Z1B02F1.CV");
                        e_2Z1M01 = _2Z1M01.getDouble("N13.Z1M03M1.CV");
                        total_2Z1M01 = (a_2Z1M01 + b_2Z1M01 + c_2Z1M01 + d_2Z1M01) * e_2Z1M01;
                        TV_2Z1M01.setText("Cement Mill: 2Z1M01 \n\nFeeding: "+ String.format("%.2f", total_2Z1M01));
                        if(e_2Z1M01 == 0)
                            TV_2Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_2Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _3R2M01 = jArray3.getJSONObject(4);
                        a_3R2M01 = _3R2M01.getDouble("N21.R2A01F1.CV");
                        b_3R2M01 = _3R2M01.getDouble("N21.R2E01F1.CV");
                        c_3R2M01 = _3R2M01.getDouble("N21.R2C07F1.CV");
                        d_3R2M01 = _3R2M01.getDouble("N21.R2D01F1.CV");
                        e_3R2M01 = _3R2M01.getDouble("N21.R2M03M1.CV");
                        total_3R2M01 = a_3R2M01 + b_3R2M01 + c_3R2M01 + d_3R2M01;
                        TV_3R2M01.setText("Raw Mill: 3R2M01 \n\nFeeding: "+ String.format("%.2f", total_3R2M01));
                        if(e_3R2M01 == 0)
                            TV_3R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_3R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _3W2W01 = jArray3.getJSONObject(5);
                        a_3W2W01 = _3W2W01.getDouble("N22.W2A07_09F1_ORIGINAL.CV");
                        b_3W2W01 = _3W2W01.getDouble("N22.W2W03_05M1.CV");
                        total_3W2W01 = a_3W2W01 * b_3W2W01;
                        TV_3W2W01.setText("Kiln: 3W2W01 \n\nFeeding: "+ String.format("%.2f", total_3W2W01));
                        if(b_3W2W01 == 0)
                            TV_3W2W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_3W2W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _3K2M01 = jArray3.getJSONObject(6);
                        a_3K2M01 = _3K2M01.getDouble("N22.K2A01V1.CV");
                        b_3K2M01 = _3K2M01.getDouble("N22.K2M03M1.CV");
                        total_3K2M01 = a_3K2M01 * b_3K2M01;
                        TV_3K2M01.setText("Coal Mill: 3K2M01 \n\nFeeding: "+ String.format("%.2f", total_3K2M01) + " %");
                        if(b_3K2M01 == 0)
                            TV_3K2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_3K2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _3Z2M01 = jArray3.getJSONObject(7);
                        a_3Z2M01 = _3Z2M01.getDouble("N23.Z2A01F1.CV");
                        b_3Z2M01 = _3Z2M01.getDouble("N23.Z2B01F1.CV");
                        c_3Z2M01 = _3Z2M01.getDouble("N23.Z2A02F1.CV");
                        d_3Z2M01 = _3Z2M01.getDouble("N23.Z2B02F1.CV");
                        e_3Z2M01 = _3Z2M01.getDouble("N23.Z2M03M1.CV");
                        total_3Z2M01 = a_3Z2M01 + b_3Z2M01 + c_3Z2M01 + d_3Z2M01;
                        TV_3Z2M01.setText("Cement Mill: 3Z2M01 \n\nFeeding: "+ String.format("%.2f", total_3Z2M01));
                        if(e_3Z2M01 == 0)
                            TV_3Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_3Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _4R1M01 = jArray3.getJSONObject(8);
                        a_4R1M01 = _4R1M01.getDouble("T_4R1.M03M1_M2.CV");
                        b_4R1M01 = _4R1M01.getDouble("T_4R1.A01F1.CV");
                        c_4R1M01 = _4R1M01.getDouble("T_4R1.B01F1.CV");
                        d_4R1M01 = _4R1M01.getDouble("T_4R1.C01F1.CV");
                        e_4R1M01 = _4R1M01.getDouble("T_4R1.J06F1_1.CV");
                        if(a_4R1M01 > 0){
                            total_4R1M01 = (b_4R1M01 * 1.02) + (c_4R1M01 * 1.02) + (d_4R1M01 * 1.02) + (((e_4R1M01 * 1.02) - d_4R1M01) * 1.02);
                            TV_4R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_4R1M01 = 0.0;
                            TV_4R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_4R1M01.setText("Raw Mill: 4R1M01 \n\nFeeding: "+ String.format("%.2f", total_4R1M01));

                        JSONObject _4W1W01 = jArray3.getJSONObject(9);
                        a_4W1W01 = _4W1W01.getDouble("T_4W1.A01F2.CV");
                        b_4W1W01 = _4W1W01.getDouble("T_4W1.A01F1_ORIGINAL.CV");
                        c_4W1W01 = _4W1W01.getDouble("T_4W1.W03_W05.CV");
                        total_4W1W01 = (a_4W1W01 + b_4W1W01) * c_4W1W01;
                        TV_4W1W01.setText("Kiln: 4W1W01 \n\nFeeding: "+ String.format("%.2f", total_4W1W01));
                        if(c_4W1W01 == 0)
                            TV_4W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_4W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _4K2M01 = jArray3.getJSONObject(10);
                        a_4K2M01 = _4K2M01.getDouble("T_4K2.S01S1.CV");
                        b_4K2M01 = _4K2M01.getDouble("T_4K2.M03M1.CV");
                        total_4K2M01 = a_4K2M01 * b_4K2M01;
                        TV_4K2M01.setText("Coal Mill: 4K2M01 \n\nFeeding: "+ String.format("%.2f", total_4K2M01) + " %");
                        if(b_4K2M01 == 0)
                            TV_4K2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_4K2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _4Z1M01 = jArray3.getJSONObject(11);
                        a_4Z1M01 = _4Z1M01.getDouble("T_4Z1.M03M1_M2.CV");
                        b_4Z1M01 = _4Z1M01.getDouble("T_4Z1.M33M1.CV");
                        c_4Z1M01 = _4Z1M01.getDouble("T_4Z1.M33M2.CV");
                        d_4Z1M01 = _4Z1M01.getDouble("T_4Z1.R03F1.CV");
                        e_4Z1M01 = _4Z1M01.getDouble("T_4Z1.A01F1.CV");
                        f_4Z1M01 = _4Z1M01.getDouble("T_4Z1.B01F1.CV");
                        g_4Z1M01 = _4Z1M01.getDouble("T_4Z1.E01F1.CV");
                        h_4Z1M01 = _4Z1M01.getDouble("T_4Z1.D01F1.CV");
                        Double result;
                        if(b_4Z1M01 == 1 || c_4Z1M01 == 1)
                            result = d_4Z1M01 * 1;
                        else
                            result = e_4Z1M01 * 1;

                        total_4Z1M01 = (a_4Z1M01 * result) + (f_4Z1M01 * 1) + (g_4Z1M01 * 1) + (h_4Z1M01 * 1);
                        TV_4Z1M01.setText("Cement Mill: 4Z1M01 \n\nFeeding: "+ String.format("%.2f", total_4Z1M01));
                        if(a_4Z1M01 == 0)
                            TV_4Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_4Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _4R2M01 = jArray3.getJSONObject(12);
                        a_4R2M01 = _4R2M01.getDouble("T_4R2.M03M1.CV");
                        b_4R2M01 = _4R2M01.getDouble("T_4R2.A01F1.CV");
                        c_4R2M01 = _4R2M01.getDouble("T_4R2.B01F1.CV");
                        d_4R2M01 = _4R2M01.getDouble("T_4R2.C01F1.CV");
                        e_4R2M01 = _4R2M01.getDouble("T_4R2.J06F1_2.CV");
                        if(a_4R2M01 > 0){
                            total_4R2M01 = (b_4R2M01 * 1.08) + (c_4R2M01 * 1.08) + (d_4R2M01 * 1.08) + (e_4R2M01 * 1.08);
                            TV_4R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_4R2M01 = 0.0;
                            TV_4R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_4R2M01.setText("Raw Mill: 4R2M01 \n\nFeeding: "+ String.format("%.2f", total_4R2M01));

                        JSONObject _4K3M01 = jArray3.getJSONObject(13);
                        a_4K3M01 = _4K3M01.getDouble("T_4K3.A01S1_FC.CV");
                        b_4K3M01 = _4K3M01.getDouble("T_4K3.M03M1_FC.CV");
                        total_4K3M01 = a_4K3M01 * b_4K3M01;
                        TV_4K3M01.setText("Coal Mill: 4K3M01 \n\nFeeding: "+ String.format("%.2f", total_4K3M01) + " %");
                        if(b_4K3M01 == 0)
                            TV_4K3M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_4K3M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _4Z2M01 = jArray3.getJSONObject(14);
                        a_4Z2M01 = _4Z2M01.getDouble("T_4Z2.M03M1.CV");
                        b_4Z2M01 = _4Z2M01.getDouble("T_4Z2.A01F1.CV");
                        c_4Z2M01 = _4Z2M01.getDouble("T_4Z2.B01F1.CV");
                        d_4Z2M01 = _4Z2M01.getDouble("T_4Z2.E01F1.CV");
                        e_4Z2M01 = _4Z2M01.getDouble("T_4Z2.D01F1.CV");
                        total_4Z2M01 = a_4Z2M01 * (b_4Z2M01 + (c_4Z2M01 * 1) + (d_4Z2M01 * 1) + (e_4Z2M01 * 1));
                        TV_4Z2M01.setText("Cement Mill: 4Z2M01 \n\nFeeding: "+ String.format("%.2f", total_4Z2M01));
                        if(a_4Z2M01 == 0)
                            TV_4Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_4Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _5R1M01 = jArray3.getJSONObject(15);
                        a_5R1M01 = _5R1M01.getDouble("T_5R1.M03M1.CV");
                        b_5R1M01 = _5R1M01.getDouble("T_5R1.A01M1F01.CV");
                        c_5R1M01 = _5R1M01.getDouble("T_5R1.C01M1F01.CV");
                        d_5R1M01 = _5R1M01.getDouble("T_5R1.D01M1F01.CV");
                        e_5R1M01 = _5R1M01.getDouble("T_5R1.E01M1F01.CV");
                        if(a_5R1M01 == 32768){
                            total_5R1M01 = b_5R1M01 + c_5R1M01 + d_5R1M01 + e_5R1M01;
                            TV_5R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5R1M01 = 0.0;
                            TV_5R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5R1M01.setText("Raw Mill: 5R1M01 \n\nFeeding: "+ String.format("%.2f", total_5R1M01));


                        JSONObject _5W1W01 = jArray3.getJSONObject(16);
                        a_5W1W01 = _5W1W01.getDouble("T_5W1.W03M1.CV");
                        b_5W1W01 = _5W1W01.getDouble("T_5W1.A05A1F01.CV");
                        c_5W1W01 = _5W1W01.getDouble("T_5W1.B05A1F01.CV");
                        if(a_5W1W01 == 32768){
                            total_5W1W01 = b_5W1W01 + c_5W1W01;
                            TV_5W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5W1W01 = 0.0;
                            TV_5W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5W1W01.setText("Kiln: 5W1W01 \n\nFeeding: "+ String.format("%.2f", total_5W1W01));

                        JSONObject _5K1M01 = jArray3.getJSONObject(17);
                        a_5K1M01 = _5K1M01.getDouble("T_5K1.M03M1.CV");
                        b_5K1M01 = _5K1M01.getDouble("T_5K1.A02M1S01.CV");
                        if(a_5K1M01 == 32768){
                            total_5K1M01 = b_5K1M01;
                            TV_5K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5W1W01 = 0.0;
                            TV_5K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5K1M01.setText("Coal Mill: 5K1M01 \n\nFeeding: "+ String.format("%.2f", total_5K1M01) + " %");

                        JSONObject _5Z1M01 = jArray3.getJSONObject(18);
                        a_5Z1M01 = _5Z1M01.getDouble("T_5Z1.M03M1.CV");
                        b_5Z1M01 = _5Z1M01.getDouble("T_5U1.U12A1F01.CV");
                        c_5Z1M01 = _5Z1M01.getDouble("T_5Z1.B01A1F01.CV");
                        d_5Z1M01 = _5Z1M01.getDouble("T_5Z1.C01A1F01.CV");
                        e_5Z1M01 = _5Z1M01.getDouble("T_5Z1.E01A1F01.CV");
                        if(a_5Z1M01 == 32768){
                            total_5Z1M01 = (b_5Z1M01 * 1) + (c_5Z1M01 * 1) + (d_5Z1M01 * 1) + (e_5Z1M01 * 1);
                            TV_5Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5Z1M01 = 0.0;
                            TV_5Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5Z1M01.setText("Cement Mill: 5Z1M01 \n\nFeeding: "+ String.format("%.2f", total_5Z1M01));

                        JSONObject _5R2M01 = jArray3.getJSONObject(19);
                        a_5R2M01 = _5R2M01.getDouble("T_5R2.M03M1.CV");
                        b_5R2M01 = _5R2M01.getDouble("T_5R2.A11M1F01.CV");
                        c_5R2M01 = _5R2M01.getDouble("T_5R2.C11M1F01.CV");
                        d_5R2M01 = _5R2M01.getDouble("T_5R2.D11M1F01.CV");
                        e_5R2M01 = _5R2M01.getDouble("T_5R2.E11M1F01.CV");
                        if(a_5R2M01 == 32768){
                            total_5R2M01 = b_5R2M01 + c_5R2M01 + d_5R2M01 + e_5R2M01;
                            TV_5R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5R2M01 = 0.0;
                            TV_5R2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5R2M01.setText("Raw Mill: 5R2M01 \n\nFeeding: "+ String.format("%.2f", total_5R2M01));

                        JSONObject _5Z2M01 = jArray3.getJSONObject(20);
                        a_5Z2M01 = _5Z2M01.getDouble("T_5Z2.M03M1.CV");
                        b_5Z2M01 = _5Z2M01.getDouble("T_5U1.U02A1F01.CV");
                        c_5Z2M01 = _5Z2M01.getDouble("T_5Z2.B01A1F01.CV");
                        d_5Z2M01 = _5Z2M01.getDouble("T_5Z2.C01A1F01.CV");
                        e_5Z2M01 = _5Z2M01.getDouble("T_5Z2.E01A1F01.CV");
                        if(a_5Z2M01 == 32768){
                            total_5Z2M01 = (b_5Z2M01) + (c_5Z2M01) + (d_5Z2M01) + (e_5Z2M01);
                            TV_5Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);
                        } else{
                            total_5Z2M01 = 0.0;
                            TV_5Z2M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        }
                        TV_5Z2M01.setText("Cement Mill: 5Z2M01 \n\nFeeding: "+ String.format("%.2f", total_5Z2M01));

                        JSONObject _6R1M01 = jArray3.getJSONObject(21);
                        a_6R1M01 = _6R1M01.getDouble("T_6R1.A01A01F01.CV");
                        b_6R1M01 = _6R1M01.getDouble("T_6R1.E01A01F01.CV");
                        c_6R1M01 = _6R1M01.getDouble("T_6R1.D01A01F01.CV");
                        d_6R1M01 = _6R1M01.getDouble("T_6R1.C01A01F01.CV");
                        e_6R1M01 = _6R1M01.getDouble("T_6R1.M03M01.CV");
                        total_6R1M01 =  a_6R1M01 + b_6R1M01 + c_6R1M01 + d_6R1M01;
                        TV_6R1M01.setText("Raw Mill: 6R1M1 \n\nFeeding: "+ String.format("%.2f", total_6R1M01));
                        if(e_6R1M01 == 0)
                            TV_6R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_6R1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _6W1W01 = jArray3.getJSONObject(22);
                        a_6W1W01 = _6W1W01.getDouble("T_6W1.A05A01F01.CV");
                        b_6W1W01 = _6W1W01.getDouble("T_6W1.B05A01F01.CV");
                        c_6W1W01 = _6W1W01.getDouble("T_6W1.W04U01.CV");
                        total_6W1W01 = a_6W1W01 + b_6W1W01;
                        TV_6W1W01.setText("Kiln: 6W1W01 \n\nFeeding: "+ String.format("%.2f", total_6W1W01));
                        if(c_6W1W01 == 0)
                            TV_6W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_6W1W01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _6K1M01 = jArray3.getJSONObject(23);
                        a_6K1M01 = _6K1M01.getDouble("T_6K1.A01A01F01.CV");
                        b_6K1M01 = _6K1M01.getDouble("T_6K1.M03M01.CV");
                        total_6K1M01 = a_6K1M01;
                        TV_6K1M01.setText("Coal Mill: 6K1M01 \n\nFeeding: "+ String.format("%.2f", total_6K1M01) + " %");
                        if(b_6K1M01 == 0)
                            TV_6K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_6K1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);

                        JSONObject _6Z1M01 = jArray3.getJSONObject(24);
                        a_6Z1M01 = _6Z1M01.getDouble("T_6Z1.A01A01F01.CV");
                        b_6Z1M01 = _6Z1M01.getDouble("T_6Z1.B01A01F01.CV");
                        c_6Z1M01 = _6Z1M01.getDouble("T_6Z1.D01A01F01.CV");
                        d_6Z1M01 = _6Z1M01.getDouble("T_6Z1.C01A01F01.CV");
                        e_6Z1M01 = _6Z1M01.getDouble("T_6Z1.M03M01.CV");
                        total_6Z1M01 = a_6Z1M01 + b_6Z1M01 + c_6Z1M01 + d_6Z1M01;
                        TV_6Z1M01.setText("Cement Mill: 2Z1M01 \n\nFeeding: "+ String.format("%.2f", total_6Z1M01));
                        if(e_6Z1M01 == 0)
                            TV_6Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.off, 0);
                        else
                            TV_6Z1M01.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.on, 0);


                    } catch (JSONException e) {
                        Toast.makeText(DashboardActivity.this, "Response Data Error! " + response.message(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.d("tag", "onResponse: error...............");
                        e.printStackTrace();
                    }
                }
            }

            @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(DashboardActivity.this, "Load data failed cause connection problem to server..", Toast.LENGTH_LONG).show();
                }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        t.interrupt();
    }

}
