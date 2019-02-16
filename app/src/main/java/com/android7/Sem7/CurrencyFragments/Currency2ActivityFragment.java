package com.android7.Sem7.CurrencyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android7.Sem7.CurrencyData;
import com.android7.Sem7.CurrencyService;
import com.android7.Sem7.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Currency2ActivityFragment extends Fragment {

    private TextView valueUSDToBTC;
    private TextView valueUSDToETH;

    CurrencyService currencyService = new CurrencyService();

    public Currency2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency2, container, false);

        currencyService.getAllCurrencyValues();

        valueUSDToBTC = (TextView) view.findViewById(R.id.valueUSDToBTC);
        valueUSDToBTC.setText(CurrencyData.USD_BTC.toString());
        valueUSDToETH = (TextView) view.findViewById(R.id.valueUSDToETH);
        valueUSDToETH.setText(CurrencyData.USD_ETH.toString());

        return view;
    }
}
