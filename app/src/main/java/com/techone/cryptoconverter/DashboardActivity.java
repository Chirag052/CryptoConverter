package com.techone.cryptoconverter;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class DashboardActivity extends AppCompatActivity {
    int valuePicker1,valuePicker2;
    TextView resultText,convertText;
    FrameLayout res;
    String convert_url= "https://rest.coinapi.io/v1/exchangerate/";
    NumberPicker numberPickerCrypto,numberPickerCurr;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        if (Build.VERSION.SDK_INT >= 21) {
            //getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorlightblue)); //status bar or the time bar at the top
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        res = findViewById(R.id.res);
        resultText = findViewById(R.id.resultText);
        convertText = findViewById(R.id.convertText);
        numberPickerCrypto = findViewById(R.id.number_picker_crypto);
        numberPickerCurr = findViewById(R.id.number_picker_curr);


//        new ShowcaseView.Builder(this)
//                .setTarget(new ViewTarget(R.id.number_picker_crypto, this))
//                .setContentTitle("ShowcaseView")
//                .setContentText("This is highlighting the Home button")
//                .hideOnTouchOutside()
//                .singleShot(42)
//
//                .build();

//        new ShowcaseView.Builder(this)
//                .setTarget(new ViewTarget(R.id.number_picker_curr, this))
//                .setContentTitle("ShowcaseView")
//                .setContentText("This is highlighting the Home button")
//                .hideOnTouchOutside()
//                .singleShot(42)
//                .build();

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "1");

        sequence.setConfig(config);

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                .setTarget(numberPickerCrypto)
                .setContentText("Scroll here for selecting crypto currency.")
                .setDismissOnTouch(true)
                .build());


        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(numberPickerCurr)
                        .setContentText("Scroll here for selecting currency.")
                        .setDismissOnTouch(true)
                        .build());

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(res)
                        .setContentText("Here you will get your conversion.")
                        .setDismissOnTouch(true)
                        .build());

        sequence.start();

        final String top_crypto[] = {"BTC", "ETH", "BNB", "XRP", "USDT", "ADA", "DOGE", "DOT", "UNI", "LTC ", "BCH", "LINK", "SOL", "XLM"};
        final String top_curr[] = {"INR", "USD", "EUR", "KWD", "OMR", "GBP", "GIP", "KYD", "CHF", "CAD", "LYD ", "BND", "SGD", "AUD", "NZD"};
        numberPickerCrypto.setMaxValue(top_crypto.length - 1);
        numberPickerCrypto.setMinValue(0);
        numberPickerCrypto.setDisplayedValues(top_crypto);
//        numberPickerCrypto.setSelectionDividerHeight(0);
//        numberPickerCurr.setSelectionDividerHeight(0);
        numberPickerCurr.setMaxValue(top_curr.length - 1);
        numberPickerCurr.setMinValue(0);
//        numberPickerCrypto.setTextColor(Color.WHITE);
  //      numberPickerCurr.setTextColor(Color.WHITE);
//        numberPickerCrypto.setTextSize(75);
  //      numberPickerCurr.setTextSize(75);
        numberPickerCurr.setDisplayedValues(top_curr);
        Retrofit converter_retrofit=new Retrofit.Builder()
                .baseUrl(convert_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AssetsApi api = converter_retrofit.create(AssetsApi.class);
        Call<ConverterModel> call = api.getmodels(top_crypto[0],top_curr[0]);
        convertText.setVisibility(View.VISIBLE);
        convertText.setText(top_crypto[0]+" TO "+top_curr[0]);
        call.enqueue(new Callback<ConverterModel>() {
            @Override
            public void onResponse(Call<ConverterModel> call, Response<ConverterModel> response) {
                ConverterModel data1 = response.body();
                String s = String.format("%.4f", data1.getRate());
                resultText.setText(s);
            }

            @Override
            public void onFailure(Call<ConverterModel> call, Throwable t) {

            }
        });

        numberPickerCrypto.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                valuePicker1 = numberPickerCrypto.getValue();
                Retrofit converter_retrofit=new Retrofit.Builder()
                        .baseUrl(convert_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AssetsApi api = converter_retrofit.create(AssetsApi.class);
                Call<ConverterModel> call = api.getmodels(top_crypto[valuePicker1],top_curr[valuePicker2]);
                convertText.setVisibility(View.VISIBLE);
                convertText.setText(top_crypto[valuePicker1]+" TO "+top_curr[valuePicker2]);
                call.enqueue(new Callback<ConverterModel>() {
                    @Override
                    public void onResponse(Call<ConverterModel> call, Response<ConverterModel> response) {
                        ConverterModel data1 = response.body();
                        String s = String.format("%.4f", data1.getRate());
                        resultText.setText(s);
                    }

                    @Override
                    public void onFailure(Call<ConverterModel> call, Throwable t) {

                    }
                });

            }
        });

        numberPickerCurr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                valuePicker2 = numberPickerCurr.getValue();

                Retrofit converter_retrofit=new Retrofit.Builder()
                        .baseUrl(convert_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AssetsApi api = converter_retrofit.create(AssetsApi.class);
                Call<ConverterModel> call = api.getmodels(top_crypto[valuePicker1],top_curr[valuePicker2]);
                convertText.setVisibility(View.VISIBLE);
                convertText.setText(top_crypto[valuePicker1]+" TO "+top_curr[valuePicker2]);
                call.enqueue(new Callback<ConverterModel>() {
                    @Override
                    public void onResponse(Call<ConverterModel> call, Response<ConverterModel> response) {
                        ConverterModel data1 = response.body();
                        if(data1==null){
                            resultText.setText("...");
                        }else {
                            String s = String.format("%.4f", data1.getRate());
                            resultText.setText(s);
                        }
                    }

                    @Override
                    public void onFailure(Call<ConverterModel> call, Throwable t) {

                    }
                });

            }
        });


    }

}
