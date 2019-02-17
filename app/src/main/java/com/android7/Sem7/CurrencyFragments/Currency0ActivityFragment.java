package com.android7.Sem7.CurrencyFragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class Currency0ActivityFragment extends Fragment {

    private TextView valueEURToBTC;
    private TextView valueEURToETH;

    private ImageView iconBTC;
    private ImageView iconETH;

    private GraphView graphHistoryBTC;
    private GraphView graphHistoryETH;

    CurrencyService currencyService = new CurrencyService();

    public Currency0ActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency0, container, false);

        currencyService.getAllCurrencyValues();

        iconBTC = view.findViewById(R.id.iconBTCEUR);
        iconBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphHistoryETH.setVisibility(View.INVISIBLE);
                graphHistoryBTC.setVisibility(View.VISIBLE);
            }
        });

        iconETH = view.findViewById(R.id.iconETHEUR);
        iconETH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphHistoryBTC.setVisibility(View.INVISIBLE);
                graphHistoryETH.setVisibility(View.VISIBLE);
            }
        });

        valueEURToBTC = (TextView) view.findViewById(R.id.valueEURToBTC);
        valueEURToBTC.setText(CurrencyData.EUR_BTC.toString());
        valueEURToETH = (TextView) view.findViewById(R.id.valueEURToETH);
        valueEURToETH.setText(CurrencyData.EUR_ETH.toString());

        graphHistoryBTC = view.findViewById(R.id.graphBTCEUR);
        graphHistoryETH = view.findViewById(R.id.graphETHEUR);

        LineGraphSeries<DataPoint> seriesEURBTC = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> seriesEURETH = new LineGraphSeries<>();

        for (int i = 0; i < CurrencyData.EUR_BTC_HST.size(); i += 2) {
            seriesEURBTC.appendData(new DataPoint(Integer.valueOf(Converters.timestampToString(CurrencyData.EUR_BTC_HST.get(i))), CurrencyData.EUR_BTC_HST.get(i + 1)), true, CurrencyData.EUR_BTC_HST.size() / 2);
        }

        for (int i = 0; i < CurrencyData.EUR_ETH_HST.size(); i += 2) {
            seriesEURETH.appendData(new DataPoint(Integer.valueOf(Converters.timestampToString(CurrencyData.EUR_ETH_HST.get(i))), CurrencyData.EUR_ETH_HST.get(i + 1)), true, CurrencyData.EUR_ETH_HST.size() / 2);
        }

        seriesEURBTC.setColor(Color.rgb(244, 143, 66));
        seriesEURETH.setColor(Color.DKGRAY);

        graphHistoryBTC.addSeries(seriesEURBTC);
        graphHistoryETH.addSeries(seriesEURETH);

        graphHistoryBTC.getGridLabelRenderer().setNumHorizontalLabels(7);
        graphHistoryETH.getGridLabelRenderer().setNumHorizontalLabels(7);

        return view;
    }
}
