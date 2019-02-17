package com.android7.Sem7.CurrencyFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android7.Sem7.Converters;
import com.android7.Sem7.CurrencyData;
import com.android7.Sem7.CurrencyService;
import com.android7.Sem7.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Currency2ActivityFragment extends Fragment {

    private TextView valueUSDToBTC;
    private TextView valueUSDToETH;

    CurrencyService currencyService = new CurrencyService();

    private ImageView iconBTC;
    private ImageView iconETH;

    private GraphView graphHistoryBTC;
    private GraphView graphHistoryETH;

    public Currency2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency2, container, false);

        currencyService.getAllCurrencyValues();

        iconBTC = view.findViewById(R.id.iconBTCUSD);
        iconBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphHistoryETH.setVisibility(View.INVISIBLE);
                graphHistoryBTC.setVisibility(View.VISIBLE);
            }
        });

        iconETH = view.findViewById(R.id.iconETHUSD);
        iconETH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphHistoryBTC.setVisibility(View.INVISIBLE);
                graphHistoryETH.setVisibility(View.VISIBLE);
            }
        });

        valueUSDToBTC = (TextView) view.findViewById(R.id.valueUSDToBTC);
        valueUSDToBTC.setText(CurrencyData.USD_BTC.toString());
        valueUSDToETH = (TextView) view.findViewById(R.id.valueUSDToETH);
        valueUSDToETH.setText(CurrencyData.USD_ETH.toString());

        graphHistoryBTC = view.findViewById(R.id.graphBTCUSD);
        graphHistoryETH = view.findViewById(R.id.graphETHUSD);

        LineGraphSeries<DataPoint> seriesPLNBTC = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> seriesPLNETH = new LineGraphSeries<>();

        for (int i = 0; i < CurrencyData.USD_BTC_HST.size(); i += 2) {
            seriesPLNBTC.appendData(new DataPoint(Integer.valueOf(Converters.timestampToString(CurrencyData.USD_BTC_HST.get(i))), CurrencyData.USD_BTC_HST.get(i + 1)), true, CurrencyData.EUR_BTC_HST.size() / 2);
        }

        for (int i = 0; i < CurrencyData.USD_ETH_HST.size(); i += 2) {
            seriesPLNETH.appendData(new DataPoint(Integer.valueOf(Converters.timestampToString(CurrencyData.USD_ETH_HST.get(i))), CurrencyData.USD_ETH_HST.get(i + 1)), true, CurrencyData.EUR_ETH_HST.size() / 2);
        }

        seriesPLNBTC.setColor(Color.rgb(244, 143, 66));
        seriesPLNETH.setColor(Color.DKGRAY);

        graphHistoryBTC.addSeries(seriesPLNBTC);
        graphHistoryETH.addSeries(seriesPLNETH);

        graphHistoryBTC.getGridLabelRenderer().setNumHorizontalLabels(7);
        graphHistoryETH.getGridLabelRenderer().setNumHorizontalLabels(7);

        return view;
    }
}
