package com.android7.Sem7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android7.constrainlayoutexample.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private TextView valueEURToBTC;
    private TextView valueEURToETH;
    private TextView valuePLNToBTC;
    private TextView valuePLNToETH;
    private TextView valueUSDToBTC;
    private TextView valueUSDToETH;
    private TextView valueJPYToBTC;
    private TextView valueJPYToETH;

    private CurrencyService currencyService = new CurrencyService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPager = (ViewPager)findViewById(R.id.viewpager);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);

        FloatingActionButton refreshButton = (FloatingActionButton) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currencyService.getAllCurrencyValues();
                updateTextViews();

                Snackbar.make(view, "Kursy walut pobrane", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void updateTextViews() {

        try {
            valueEURToBTC = (TextView) findViewById(R.id.valueEURToBTC);
            valueEURToBTC.setText(CurrencyData.EUR_BTC.toString());
            valueEURToETH = (TextView) findViewById(R.id.valueEURToETH);
            valueEURToETH.setText(CurrencyData.EUR_ETH.toString());
        } catch (NullPointerException e) {

        }

        try {
            valuePLNToBTC = (TextView) findViewById(R.id.valuePLNToBTC);
            valuePLNToBTC.setText(CurrencyData.PLN_BTC.toString());
            valuePLNToETH = (TextView) findViewById(R.id.valuePLNToETH);
            valuePLNToETH.setText(CurrencyData.PLN_ETH.toString());
        } catch (NullPointerException e) {

        }

        try {
            valueUSDToBTC = (TextView) findViewById(R.id.valueUSDToBTC);
            valueUSDToBTC.setText(CurrencyData.USD_BTC.toString());
            valueUSDToETH = (TextView) findViewById(R.id.valueUSDToETH);
            valueUSDToETH.setText(CurrencyData.USD_ETH.toString());
        } catch (NullPointerException e) {

        }

        try {
            valueJPYToBTC = (TextView) findViewById(R.id.valueJPYToBTC);
            valueJPYToBTC.setText(CurrencyData.JPY_BTC.toString());
            valueJPYToETH = (TextView) findViewById(R.id.valueJPYToETH);
            valueJPYToETH.setText(CurrencyData.JPY_ETH.toString());
        } catch (NullPointerException e) {
            
        }
    }
}
