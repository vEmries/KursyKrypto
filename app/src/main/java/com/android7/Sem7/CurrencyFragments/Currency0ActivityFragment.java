package com.android7.Sem7.CurrencyFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android7.Sem7.CurrencyData;
import com.android7.Sem7.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Currency0ActivityFragment extends Fragment {

    private TextView valueEURToBTC;
    private TextView valueEURToETH;

    public Currency0ActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency0, container, false);

        valueEURToBTC = (TextView) view.findViewById(R.id.valueEURToBTC);
        valueEURToBTC.setText(CurrencyData.EUR_BTC.toString());
        valueEURToETH = (TextView) view.findViewById(R.id.valueEURToETH);
        valueEURToETH.setText(CurrencyData.EUR_ETH.toString());

        return view;
    }
}
